package com.crud.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public void createBoard(String boardTitle, String boardIntro) {
    Board board = new Board();
    board.setBoardTitle(boardTitle);
    board.setBoardIntroduce(boardIntro);
    boardRepository.save(board);
  }

  public void updateBoard(Long boardId,String boardTitle, String boardIntro){
    Board board = new Board();
    board.setBoardId(boardId);
    board.setBoardTitle(boardTitle);
    board.setBoardIntroduce(boardIntro);
    boardRepository.save(board);
  }

}
