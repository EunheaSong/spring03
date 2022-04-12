package com.board.spring03.dto;


import com.board.spring03.model.BoardWrite;
import com.board.spring03.model.Timestamped;
import com.board.spring03.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class ReplyRequestDto {

    private String comment;

    private BoardWrite boardId;

    private User userId;


}
