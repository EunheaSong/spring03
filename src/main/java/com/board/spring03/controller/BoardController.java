package com.board.spring03.controller;

import com.board.spring03.momain.BoardRequestDto;
import com.board.spring03.momain.BoardWrite;
import com.board.spring03.repository.BoardRepository;
import com.board.spring03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
    public String boardWritePro(@RequestBody BoardRequestDto requestDto){
        BoardWrite boardWrite = new BoardWrite(requestDto);
        boardRepository.save(boardWrite);

        return "redirect:/";
    }

    @GetMapping("board/view/")
    public String boardView(Model model, Integer id){
        model.addAttribute("boardDetail",boardService.boardViewer(id));
        return "boardView";
    }
//    @GetMapping("/board/view/{id}")
//    public Integer boardView(@PathVariable Integer id){
//        boardService.boardViewer(id);
//        return id;
//    }


}
