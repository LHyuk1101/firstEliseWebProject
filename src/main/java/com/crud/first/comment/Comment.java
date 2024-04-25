package com.crud.first.comment;

import com.crud.first.board.Board;
import com.crud.first.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String writer;

  @Column(nullable = false, length = 100)
  private String commentContents;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Post post;

  public Comment(String commentContents) {
    this.commentContents = commentContents;
  }

  public Comment(Long id, String commentContents) {
    this.id = id;
    this.commentContents = commentContents;
  }

}
