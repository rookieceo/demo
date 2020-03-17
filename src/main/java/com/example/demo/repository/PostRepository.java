package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.PostDTO.ListDTO;
import com.example.demo.entity.PostEntity;
import com.example.demo.entity.QPostEntity;
import com.querydsl.core.types.Projections;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>, CustomPostRepository {

}

interface CustomPostRepository {
	public List<PostDTO.ListDTO> postsUsingQuerydsl();
}

class CustomPostRepositoryImpl extends QuerydslRepositorySupport implements CustomPostRepository {

	public CustomPostRepositoryImpl() {
		super(PostEntity.class);
	}

	@Override
	public List<ListDTO> postsUsingQuerydsl() {
		QPostEntity postEntity = QPostEntity.postEntity;
		return from(postEntity).select(Projections.bean(ListDTO.class, postEntity.postId, postEntity.title,
				postEntity.content, postEntity.createdBy, postEntity.createdAt, postEntity.modifiedAt)).fetch();
	}

}