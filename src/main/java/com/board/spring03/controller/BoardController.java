package com.board.spring03.controller;

import com.board.spring03.dto.BoardRequestDto;
import com.board.spring03.model.BoardWrite;
import com.board.spring03.repository.BoardRepository;
import com.board.spring03.security.UserDetailsImpl;
import com.board.spring03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;


    @GetMapping("/")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "board";
    }

    @GetMapping("/board/write")
    public String boardWrite () {

        return "boardWrite";
    }

    @PostMapping("/board/write")
    public String boardWritePro(@RequestBody BoardRequestDto requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        BoardWrite boardWrite = new BoardWrite(requestDto, userId);
        boardRepository.save(boardWrite);

        return "redirect:/";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Long id){
        model.addAttribute("boardDetail",boardService.boardViewer(id));
        return "boardView";
    }
//덧글 겟 포스트 구현


//    @PostMapping("/board/reply")
//    public void reply(@RequestParam ){
//
//    }


}
