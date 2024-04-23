package com.crud.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
