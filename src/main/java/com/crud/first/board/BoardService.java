package com.crud.first.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public void createBoard(String boardTitle, String boardIntro) {
    Board board = new Board(boardTitle,boardIntro);
    boardRepository.save(board);
  }

  public void updateBoard(Long boardId,String boardTitle, String boardIntro){
    Board board = new Board(boardId,boardTitle,boardIntro);
    boardRepository.save(board);
  }

  public Board findBoardById(Long id){
    return boardRepository.findById(id).orElse(null);
  }

  public Board saveBoard(Board board){
    return boardRepository.save(board);
  }

}
