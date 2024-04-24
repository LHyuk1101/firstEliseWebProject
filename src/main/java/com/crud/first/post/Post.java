package com.crud.first.post;


import com.crud.first.board.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String writer;

  @Column(nullable = false, length = 30)
  private String postTitle;

  @Column(nullable = false, length = 1000)
  private String postContents;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="board_id",
  foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Board board;

  public Post(String postTitle,String postContents){
    this.postTitle = postTitle;
    this.postContents = postContents;
  }
  public Post(Long id, String postTitle, String postContents){
    this.id = id;
    this.postTitle = postTitle;
    this.postContents = postContents;
  }



}
