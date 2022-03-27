package com.board.spring03.model;


import com.board.spring03.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoardWrite extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;


    public BoardWrite(BoardRequestDto requestDto, Long userId) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = userId;
    }
}


