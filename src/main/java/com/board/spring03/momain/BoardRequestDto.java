package com.board.spring03.momain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequestDto {

    private String name;

    private String title;

    private String content;

    public BoardRequestDto (String name, String title, String content){
        this.name = name;
        this.title = title;
        this.content = content;
    }

}
