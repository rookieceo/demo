package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.PostEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class PostControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
			.addFilters(new CharacterEncodingFilter(StandardCharsets.UTF_8.toString(), true)) // 필터 추가
			.alwaysDo(print())
			.build();
	}

	@Test
	final void testList() throws Exception {
		// /api/posts?page=2&size=15&direction=DESC&sortBy=createdAt
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/posts")
			.param("page", "2")
			.param("size", "15")
			.param("sortBy", "createdAt")
			.param("direction", "DESC"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	final void testCreate() throws Exception {
		// POST /api/posts
		PostDTO.CreateDTO dto = new PostDTO.CreateDTO();
		dto.setTitle("POST 테스트1 제목");
		dto.setContent("POST 테스트1 내용");
		dto.setCreatedBy("POST 테스트1 작성자");

		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
			.contentType(MediaType.APPLICATION_JSON)
			.content(this.objectMapper.writeValueAsString(dto)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();

		PostEntity postEntity = this.objectMapper.readValue(result.getResponse().getContentAsString(), PostEntity.class);

		assertThat(dto.getTitle()).isEqualTo(postEntity.getTitle());
	}

	@Test
	final void testSelect() throws Exception {
		// GET /api/posts/15
		long postId = 15;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/" + postId))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	final void testUpdate() throws Exception {
		// PUT /api/posts/15
		long postId = 15;
		PostDTO.UpdateDTO dto = new PostDTO.UpdateDTO();
		dto.setPostId(postId);
		dto.setTitle("PUT 테스트1 제목 수정");
		dto.setContent("PUT 테스트1 내용");
		dto.setCreatedBy("PUT 테스트1 작성자");

		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/api/posts/" + dto.getPostId())
			.contentType(MediaType.APPLICATION_JSON)
			.content(this.objectMapper.writeValueAsString(dto)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();

		PostEntity postEntity = this.objectMapper.readValue(result.getResponse().getContentAsString(), PostEntity.class);

		assertThat(dto.getTitle()).isEqualTo(postEntity.getTitle());

	}

//
	@Test
	final void testDelete() throws Exception {
		// DELETE /api/posts/29
		long postId = 29;

		// 삭제처리
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/posts/" + postId)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());

		// 삭제가 완료되었는지 확인
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/" + postId))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
