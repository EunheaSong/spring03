package com.board.spring03.model;

import com.board.spring03.dto.ReplyRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class Reply extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String comment;

//    @Column(nullable = false)
//    private Long userId;
//
//    @Column(nullable = false)
//    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardWrite boardId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public Reply (ReplyRequestDto requestDto){
        this.comment = getComment();
    }

}
