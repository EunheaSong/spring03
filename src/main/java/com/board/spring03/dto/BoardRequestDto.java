package com.board.spring03.dto;

import com.board.spring03.model.Reply;
import com.board.spring03.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BoardRequestDto {

    private String title;

    private String content;

    private User userId;

    private List<Reply> reply;


}
