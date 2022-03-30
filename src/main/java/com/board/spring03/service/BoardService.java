package com.board.spring03.service;

import com.board.spring03.dto.BoardRequestDto;
import com.board.spring03.dto.ReplyRequestDto;
import com.board.spring03.dto.UserRequestDto;
import com.board.spring03.model.Reply;
import com.board.spring03.model.User;
import com.board.spring03.repository.BoardRepository;
import com.board.spring03.model.BoardWrite;

import com.board.spring03.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public List<BoardWrite> boardList (){
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public BoardWrite boardViewer(Long id){
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
    }

    @Transactional
    public void replySave(User user, Long boardId, ReplyRequestDto replyRequestDto){
        BoardWrite boardWrite = boardRepository.findById(boardId).orElseThrow(() ->{
            return new IllegalArgumentException("댓글 등록 실패 : 게시글을 찾을 수 없습니다.");});
        Reply reply = new Reply();
        reply.setComment(replyRequestDto.getComment());
        reply.setUserId(user);
        reply.setBoardId(boardWrite);
        replyRepository.save(reply);
    }


}
