package com.board.spring03.service;

import com.board.spring03.repository.BoardRepository;
import com.board.spring03.model.BoardWrite;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;


    public List<BoardWrite> boardList (){

        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public BoardWrite boardViewer(Long id){
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
    }


}
