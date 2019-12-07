package com.example.demo.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostDTO {

	@Getter
	@Setter
	@NoArgsConstructor
	public static class ListDTO {
		private Long postId;
		private String title;
		private String content;
		private String createdBy;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SelectDTO {
		private Long postId;
		private String title;
		private String content;
		private String createdBy;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class CreateDTO {
		@NotEmpty
		private String title;
		@NotEmpty
		private String content;
		@NotEmpty
		private String createdBy;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class UpdateDTO {
		@NotNull
		private Long postId;

		@NotEmpty
		private String title;

		@NotEmpty
		private String content;

		@NotEmpty
		private String createdBy;
	}
}
