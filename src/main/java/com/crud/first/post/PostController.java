package com.crud.first.post;

import com.crud.first.board.Board;
import com.crud.first.board.BoardRepository;
import com.crud.first.board.BoardService;
import com.crud.first.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;
  private final BoardRepository boardRepository;
  private final PostService postService;
  private final BoardService boardService;

  @GetMapping("/writePost")
  public String writePost(Model model) {
    List<Board> result = boardRepository.findAll();
    model.addAttribute("boards",result);
    return "post/writePost.html";
  }

  @PostMapping("/createPost")
  public String post(@RequestParam("boards") Long boardId,@RequestParam String postTitle,@RequestParam String postContents, Authentication auth) {
    Post post = new Post(postTitle, postContents);
    CustomUser customUser = (CustomUser) auth.getPrincipal();
    post.setWriter(customUser.getUsername());
    Board board = boardService.findBoardById(boardId);
    post.setBoard(board);
    board.addPost(post);
    postRepository.save(post);
    return "redirect:/board";
  }


}
