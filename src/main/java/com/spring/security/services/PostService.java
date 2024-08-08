package com.spring.security.services;

import com.spring.security.dto.PostDto;
import com.spring.security.entity.Post;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long id);
}
