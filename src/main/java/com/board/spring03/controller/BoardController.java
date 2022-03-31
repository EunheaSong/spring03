package com.board.spring03.controller;

import com.board.spring03.dto.BoardRequestDto;
import com.board.spring03.dto.ReplyRequestDto;
import com.board.spring03.model.BoardWrite;
import com.board.spring03.model.Reply;
import com.board.spring03.model.User;
import com.board.spring03.repository.BoardRepository;
import com.board.spring03.repository.ReplyRepository;
import com.board.spring03.security.UserDetailsImpl;
import com.board.spring03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;



    @GetMapping("/")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "board";
    }

    @GetMapping("/board/write")
    public String boardWrite () {

        return "boardWrite";
    }

    //게시물 등록시 유저 정보 같이 등록.
    @PostMapping("/board/write")
    public String boardWritePro(@RequestBody BoardRequestDto requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        BoardWrite boardWrite = new BoardWrite(requestDto);
        boardWrite.setUserId(userDetails.getUser());
        boardRepository.save(boardWrite);
        return "redirect:/";
    }

//    @GetMapping("/board/view/{id}")
//    public String boardView(Model model,@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        model.addAttribute("boardDetail",boardService.boardViewer(id));
//        model.addAttribute("nowUser",userDetails.getUser());
//        return "boardView";
//    }
    @GetMapping("/board/view/{id}")
    public String boardView(Model model, @PathVariable Long id, Principal principal) {
        try {
            model.addAttribute("nowUser",principal.getName());
            model.addAttribute("boardDetail", boardService.boardViewer(id));
        } catch (NullPointerException e){
            model.addAttribute("boardDetail", boardService.boardViewer(id));
        }
        return "boardView";
    }


    @PostMapping("/board/view/{id}")
    public String reply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(requestDto.getComment());
        boardService.replySave(userDetails.getUser(),id,requestDto);
        System.out.println(requestDto.getComment());
        return "redirect:/board/view/{id}";
    }



}
