package com.spring.security.services;

import com.spring.security.dto.PostDto;
import com.spring.security.entity.Post;
import com.spring.security.exceptions.ResourceNotFoundException;
import com.spring.security.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return modelMapper.map(postRepository.save(post),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post with this id does not exist::"+id));
        return modelMapper.map(post,PostDto.class);
    }
}
