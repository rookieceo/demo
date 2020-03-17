package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.common.dto.CustomPageRequest;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.PostEntity;
import com.example.demo.repository.PostRepository;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/posts")
	public Page<PostDTO.ListDTO> list(CustomPageRequest pageable) {
		return this.postRepository.findAll(pageable.of()).map(entity -> this.mapper.map(entity, PostDTO.ListDTO.class));
	}

	@GetMapping("/posts/{postId}")
	public PostDTO.SelectDTO select(@PathVariable Long postId) {
		PostEntity entity = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return this.mapper.map(entity, PostDTO.SelectDTO.class);
	}

	@PostMapping("/posts")
	@Transactional
	public PostEntity create(@RequestBody @Valid PostDTO.CreateDTO dto) {
		return this.postRepository.save(this.mapper.map(dto, PostEntity.class));
	}

	@PutMapping("/posts/{postId}")
	@Transactional
	public PostEntity update(@RequestBody @Valid PostDTO.UpdateDTO dto) {
		return this.postRepository.save(this.mapper.map(dto, PostEntity.class));
	}

	@DeleteMapping("/posts/{postId}")
	@Transactional
	public void delete(@PathVariable Long postId) {
		this.postRepository.deleteById(postId);
	}

	@GetMapping("/posts-querydsl")
	public List<PostDTO.ListDTO> listUsingQuerydsl() {
		return this.postRepository.postsUsingQuerydsl();
	}

}
