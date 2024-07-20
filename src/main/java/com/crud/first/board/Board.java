package com.crud.first.board;

import com.crud.first.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @Column(length = 30, nullable = false, unique = true)
  private String boardTitle;

  @Column(length = 100, nullable = false)
  private String boardIntroduce;

  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
  List<Post> posts = new ArrayList<>();

  public Board(String boardTitle, String boardIntroduce){
    this.boardTitle = boardTitle;
    this.boardIntroduce = boardIntroduce;
  }
  public Board(Long boardId,String boardTitle, String boardIntroduce){
    this.boardId = boardId;
    this.boardTitle = boardTitle;
    this.boardIntroduce = boardIntroduce;
  }

  public void addPost(Post post){
    posts.add(post);
    post.setBoard(this);
  }

  public void removePost(Post post){
    posts.remove(post);
    post.setBoard(null);
  }

}
