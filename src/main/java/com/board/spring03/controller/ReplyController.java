package com.board.spring03.controller;

import com.board.spring03.dto.ReplyRequestDto;
import com.board.spring03.model.BoardWrite;
import com.board.spring03.model.Reply;
import com.board.spring03.model.User;
import com.board.spring03.security.UserDetailsImpl;
import com.board.spring03.service.BoardService;
import com.board.spring03.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class ReplyController {

    private final ReplyService replyService;
    private final BoardService boardService;


    @GetMapping("/replies/{id}")
    public List<Reply> replies (@PathVariable Long id){
        BoardWrite board = boardService.boardViewer(id);
        return board.getReply();
    }


    @PutMapping("/reply/up/{id}")
    public Long updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto){
        replyService.replyUpdate(id,replyRequestDto);
        return id;
    }

    @DeleteMapping("/reply/delete/{id}")
    public void deleteRelpy(@PathVariable Long id){

        replyService.replyDelete(id);
    }


}
