package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.PostEntity;
import com.example.demo.repository.PostRepository;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i < 30; i++) {
			this.postRepository.save(new PostEntity("제목" + i, "내용" + i, "홍길동" + i));
		}
		this.postRepository.flush();
	}

}
