package com.board.spring03.momain;


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
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public BoardWrite (String name, String title, String content){
        this.name = name;
        this.title = title;
        this.content = content;
    }
    public BoardWrite(BoardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}


