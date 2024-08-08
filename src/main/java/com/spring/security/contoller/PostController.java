package com.spring.security.contoller;

import com.spring.security.dto.PostDto;
import com.spring.security.entity.Post;
import com.spring.security.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
         List<PostDto> post = postService.getAllPosts();
         return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("{Id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long Id) {
            PostDto postDto = postService.getPostById(Id);
            return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

}
