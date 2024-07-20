package com.crud.first.comment;

import com.crud.first.member.CustomUser;
import com.crud.first.post.Post;
import com.crud.first.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final PostService postService;

  public void createComment(Long postId, String commentContents, Authentication auth) {
    Comment comment = new Comment(commentContents);

    CustomUser customUser = (CustomUser) auth.getPrincipal();
    comment.setWriter(customUser.getUsername());

    Post post = postService.findPostById(postId);
    comment.setPost(post);
    post.addComment(comment);
    commentRepository.save(comment);
  }

  public void updateComment(Long id,Long postId, String commentContents, Authentication auth) {
    Comment comment = new Comment(id,commentContents);
    CustomUser customUser = (CustomUser) auth.getPrincipal();
    comment.setWriter(customUser.getUsername());
    Post post = postService.findPostById(postId);
    comment.setPost(post);
    commentRepository.save(comment);
  }

  public Optional<Comment> getCommentsById(Long commentId) {
    return commentRepository.findById(commentId);
  }




  public List<Comment> getCommentsByPostId(Long postId) {
    return commentRepository.findByPostId(postId);
  }

}
