package com.crud.first.comment;

import com.crud.first.board.BoardService;
import com.crud.first.member.CustomUser;
import com.crud.first.member.MemberService;
import com.crud.first.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

  private final CommentRepository commentRepository;
  private final CommentService commentService;
  private final MemberService memberService;
  private final PostService postService;
  private final BoardService boardService;


  @PostMapping("/createComment")
  public String comment(@RequestParam Long postId,@RequestParam String commentContents, Authentication auth) {
    commentService.createComment(postId,commentContents,auth);
    return "redirect:/post/"+postId;
  }



  @GetMapping("/update/comment/{id}")
  public String updateCommentPage(Model model, @PathVariable Long id){
    model.addAttribute("boards",boardService.findAll());
    if (commentService.getCommentsById(id).isPresent()){
      model.addAttribute("updateComments",commentService.getCommentsById(id).get());
      return "comment/updateComment.html";
    }else {
      return "redirect:/";
    }
  }


  @PostMapping("/updateComment")
  public String updateComment(@RequestParam Long id,@RequestParam Long postId, @RequestParam String commentContents, Authentication auth) {
    commentService.updateComment(id,postId,commentContents,auth);
    return "redirect:/post/"+postId;
  }

  @DeleteMapping("/deleteComment")
  public ResponseEntity<String> deleteComment(@RequestParam Long id){
    commentRepository.deleteById(id);
    return ResponseEntity.status(200).body("삭제완료");
  }

}
