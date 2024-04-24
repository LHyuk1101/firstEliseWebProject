package com.crud.first.board;

import com.crud.first.post.Post;
import com.crud.first.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

  private final BoardRepository boardRepository;
  private final BoardService boardService;
  private final PostService postService;

  @GetMapping("/board")
  public String board(Model model) {
    model.addAttribute("boards",boardService.findAll());
    return "board/board.html";
  }

  @GetMapping("/board/{id}")
  public String selectBoard(Model model, @PathVariable Long id){

    model.addAttribute("boards",boardService.findAll());

    Optional<Board> idNum = boardRepository.findById(id);
    if (idNum.isPresent()){
      model.addAttribute("selectBoard",idNum.get());
//      List<Post> postsByBoardId = postService.getPostsByBoardId(id);
      model.addAttribute("posts",postService.getPostsByBoardId(id));

      return "board/selectBoard.html";
    }
    return "redirect:/board";
  }


  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("boards",boardService.findAll());
    return "board/createBoard.html";
  }

  @PostMapping("/createBoard")
  public String createBoard(@RequestParam String boardTitle,@RequestParam String boardIntro) {
    boardService.createBoard(boardTitle,boardIntro);
    return "redirect:/board/"+boardService.findAll().getLast().getBoardId();
  }

  @GetMapping("/update/{id}")
  public String update( Model model, @PathVariable Long id) {
    model.addAttribute("boards",boardService.findAll());
    Optional<Board> result = boardRepository.findById(id);
    if (result.isPresent()){
      model.addAttribute("updateBoard",result.get());
      return "board/updateBoard.html";
    }else {
      return "redirect:/board/"+boardService.findAll().getFirst().getBoardId();
    }
  }

  @PostMapping("/update")
  public String updateBoard(@RequestParam Long boardId,@RequestParam String boardTitle, @RequestParam String boardIntro) {
    boardService.updateBoard(boardId,boardTitle,boardIntro);
    return "redirect:/board/"+boardId;
  }

  @DeleteMapping("/deleteBoard")
  public ResponseEntity<String> deleteBoard(@RequestParam Long id) {
    boardRepository.deleteById(id);
    return ResponseEntity.status(200).body("삭제완료");
  }


}
