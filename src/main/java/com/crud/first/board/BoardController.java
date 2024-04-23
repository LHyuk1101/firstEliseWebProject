package com.crud.first.board;

import lombok.RequiredArgsConstructor;
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
public class BoardController {

  private final BoardRepository boardRepository;

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
      model.addAttribute("selectBoard",idNum);
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
    Board board = new Board();
    board.setBoardTitle(boardTitle);
    board.setBoardIntroduce(boardIntro);
    boardRepository.save(board);
    return "redirect:/board";
  }


}
