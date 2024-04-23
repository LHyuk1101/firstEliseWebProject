package com.crud.first.post;


import com.crud.first.board.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 30)
  private String postTitle;

  @Column(nullable = false, length = 1000)
  private String postContents;

  @ManyToOne
  @JoinColumn(name="boardId")
  private Board board;

}
