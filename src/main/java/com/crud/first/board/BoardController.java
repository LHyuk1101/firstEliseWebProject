package com.crud.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/board")
  public String board(Model model) {
    List<Board> result = boardRepository.findAll();
    model.addAttribute("boards",result);
    return "board/board.html";
  }

  @GetMapping("/board/{id}")
  public String selectBoard(Model model, @PathVariable Long id){
    List<Board> result = boardRepository.findAll();
    model.addAttribute("boards",result);
    Optional<Board> idNum = boardRepository.findById(id);

    if (idNum.isPresent()){
      model.addAttribute("selectBoard",idNum.get());
      return "board/selectBoard.html";
    }
    return "redirect:/board";
  }


  @GetMapping("/create")
  public String create() {
    return "board/createBoard.html";
  }

  @PostMapping("/createBoard")
  public String createBoard(@RequestParam String boardTitle,@RequestParam String boardIntro) {
    boardService.createBoard(boardTitle,boardIntro);
    return "redirect:/board";
  }

  @GetMapping("/update/{id}")
  public String update( Model model, @PathVariable Long id) {
    Optional<Board> result = boardRepository.findById(id);
    if (result.isPresent()){
      model.addAttribute("updateBoard",result.get());
      return "board/updateBoard.html";
    }else {
      return "redirect:/board";
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
