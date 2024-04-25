package com.crud.first.post;

import com.crud.first.board.Board;
import com.crud.first.board.BoardRepository;
import com.crud.first.board.BoardService;
import com.crud.first.comment.CommentService;
import com.crud.first.member.CustomUser;
import com.crud.first.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;
  private final MemberService memberService;
  private final PostService postService;
  private final BoardService boardService;
  private final CommentService commentService;

  @GetMapping("/writePost")
  public String writePost(Model model) {
    model.addAttribute("boards",boardService.findAll());
    return "post/writePost.html";
  }

  @PostMapping("/createPost")
  public String post(@RequestParam("boards") Long boardId,@RequestParam String postTitle,@RequestParam String postContents, Authentication auth) {
    postService.createPost(boardId,postTitle,postContents,auth);
    return "redirect:/board/"+boardService.findBoardById(boardId).getBoardId();
  }

  @GetMapping("/update/post/{id}")
  public String updatedPost( Model model, @PathVariable Long id) {
    model.addAttribute("boards",boardService.findAll());

    if (postService.getPostsById(id).isPresent()){
      model.addAttribute("updatePost",postService.getPostsById(id).get());
      return "post/updatePost.html";
    }else {
      return "redirect:/post/"+postService.getPostsById(id).get().getId();
    }
  }

  @PostMapping("/updatePost")
  public String updatePost(@RequestParam Long id,@RequestParam Long boardId,@RequestParam String postTitle,@RequestParam String postContents, Authentication auth) {
    postService.updatePost(id,boardId,postTitle,postContents,auth);
    return "redirect:/post/"+id;
  }



  @GetMapping("/post/{id}")
  public String postDetail( Model model, @PathVariable Long id) {
    model.addAttribute("boards",boardService.findAll());
    if (postService.getPostsById(id).isPresent()){
      model.addAttribute("posts",postService.getPostsById(id).get());
      model.addAttribute("comments",commentService.getCommentsByPostId(id));
    }
    return "post/postDetail.html";
  }

  @DeleteMapping("/deletePost")
  public ResponseEntity<String> deletePost(@RequestParam Long id) {
    postRepository.deleteById(id);
    return ResponseEntity.status(200).body("삭제완료");
  }




}
