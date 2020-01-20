package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.common.dto.CustomPageRequest;
import com.example.demo.entity.PostEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void getPosts() {

		final int page = 2;
		final int size = 15;

		CustomPageRequest pageable = new CustomPageRequest();
		pageable.setDirection(Direction.DESC);
		pageable.setSortBy("createdAt");
		pageable.setPage(page);
		pageable.setSize(size);

		Page<PostEntity> posts = this.postRepository.findAll(pageable.of());

		posts.forEach(entity -> {
			log.info("제목 : {}, 내용 : {}, 작성자 : {}, 작성일 : {}",
				entity.getTitle(),
				entity.getContent(),
				entity.getCreatedBy(),
				entity.getCreatedAt());
		});

		assertThat(posts.getPageable().getPageNumber() + 1).isEqualTo(page);
		assertThat(posts.getPageable().getPageSize()).isEqualTo(size);
	}
}
