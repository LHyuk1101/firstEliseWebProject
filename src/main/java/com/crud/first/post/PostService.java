package com.crud.first.post;

import com.crud.first.board.Board;
import com.crud.first.board.BoardService;
import com.crud.first.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final BoardService boardService;

  public void createPost(Long boardId,String postTitle, String postContents, Authentication auth){
    Post post = new Post(postTitle, postContents);
    CustomUser customUser = (CustomUser) auth.getPrincipal();
    post.setWriter(customUser.getUsername());
    Board board = boardService.findBoardById(boardId);
    post.setBoard(board);
    board.addPost(post);
    postRepository.save(post);
  }

  public void updatePost(Long id,Long boardId,String postTitle, String postContents, Authentication auth){
    Post post = new Post(id,postTitle, postContents);
    CustomUser customUser = (CustomUser) auth.getPrincipal();
    post.setWriter(customUser.getUsername());
    Board board = boardService.findBoardById(boardId);
    post.setBoard(board);
    postRepository.save(post);
  }


  public Post findPostById(Long id){
    return postRepository.findById(id).orElse(null);
  }

  public void deletePost(Post post) {
    postRepository.delete(post);
  }


  public List<Post> findAll(){
    return postRepository.findAll();
  }

  public List<Post> getPostsByBoardId(Long boardId) {
    return postRepository.findByBoard_BoardId(boardId);
  }

  public Optional<Post> getPostsById(Long postId) {
    return postRepository.findById(postId);
  }
}
