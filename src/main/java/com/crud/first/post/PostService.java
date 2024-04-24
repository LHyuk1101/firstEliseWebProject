package com.crud.first.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public Post findPostById(Long id){
    return postRepository.findById(id).orElse(null);
  }

  public void deletePost(Post post) {
    postRepository.delete(post);
  }

}
