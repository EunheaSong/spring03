package com.board.spring03.service;

import com.board.spring03.dto.ReplyRequestDto;
import com.board.spring03.model.BoardWrite;
import com.board.spring03.model.Reply;
import com.board.spring03.repository.BoardRepository;
import com.board.spring03.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardService boardService;

    @Transactional
    public void replyDelete(Long id){

        replyRepository.deleteById(id);
    }

    @Transactional
    public Long replyUpdate (Long id, ReplyRequestDto replyRequestDto){
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다."));
        String newcomment = replyRequestDto.getComment();
        reply.setComment(newcomment);

        return id;
    }

//    public List<Reply> replies (Long id){
//        BoardWrite board = boardService.boardViewer(id);
//        return board.getReply();
//    }
}
