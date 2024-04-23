package com.crud.first.board;

import com.crud.first.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @Column(length = 30, nullable = false, unique = true)
  private String boardTitle;

  @Column(length = 100, nullable = false)
  private String boardIntroduce;

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
  List<Post> post = new ArrayList<>();

}
