package com.yasemin.service;

import com.yasemin.dto.request.CommentRequestDto;
import com.yasemin.dto.request.CommentUpdateRequestDto;
import com.yasemin.entity.Comments;
import com.yasemin.exception.ErrorType;
import com.yasemin.exception.TatilKiralamaException;
import com.yasemin.repository.CommentRepository;
import com.yasemin.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtTokenManager jwtTokenManager;

    public Boolean createComment(CommentRequestDto dto) {
        Optional<String> userId=jwtTokenManager.validateToken(dto.getToken());
        if (userId.isEmpty()){
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
        commentRepository.save(Comments.builder()
                        .userId(userId.get())
                        .comment(dto.getComment())
                        .hotelId(dto.getHotelId())
                        .point(dto.getPoint())
                .build());
        return true;
    }
    public Boolean updateComment(CommentUpdateRequestDto dto) {
        Optional<String> userId=jwtTokenManager.validateToken(dto.getToken());
        if (userId.isEmpty()){
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
        Optional<Comments> comments=commentRepository.findById(dto.getCommentId());
        if (comments.isEmpty()){
            throw new TatilKiralamaException(ErrorType.COMMENT_NOT_FOUND_ERROR);
        }
        comments.get().setComment(dto.getComment());
        comments.get().setPoint(dto.getPoint());
        commentRepository.save(comments.get());

        return true;
    }

    public Boolean deleteComment(String commentId) {
        Optional<Comments> comments=commentRepository.findById(commentId);
        if (comments.isEmpty()){
            throw new TatilKiralamaException(ErrorType.COMMENT_NOT_FOUND_ERROR);
        }
        commentRepository.delete(comments.get());
        return true;
    }

    public List<Comments> findCommentByUserId(String userId) {
        List<Comments> comments=commentRepository.findAllByUserId(userId);
        if (comments.isEmpty()){
            throw new TatilKiralamaException(ErrorType.COMMENT_NOT_FOUND_ERROR);
        }
        return comments;
    }

    public List<Comments> findCommentByHotelId(String hotelId) {
        List<Comments> comments=commentRepository.findAllByHotelId(hotelId);
        if (comments.isEmpty()){
            throw new TatilKiralamaException(ErrorType.COMMENT_NOT_FOUND_ERROR);
        }
        return comments;
    }

}
