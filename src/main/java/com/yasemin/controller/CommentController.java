package com.yasemin.controller;

import com.yasemin.dto.request.CommentRequestDto;
import com.yasemin.dto.request.CommentUpdateRequestDto;

import com.yasemin.entity.Comments;
import com.yasemin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yasemin.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
public class CommentController {
    private final CommentService commentService;
    @PostMapping(YORUM_YAP)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createComment(@RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean>  updateComment(@RequestBody CommentUpdateRequestDto dto) {
        return ResponseEntity.ok(commentService.updateComment(dto));
    }
    @DeleteMapping(DELETE_BY_ID)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteComment(String commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
    @GetMapping("/find-by-userId")
    public ResponseEntity<List<Comments>> findCommentByUserId(String userId) {
        return ResponseEntity.ok(commentService.findCommentByUserId(userId));
    }
    @GetMapping("/find-by-hotelId")
    public ResponseEntity<List<Comments>> findCommentByHotelId(String hotelId) {
        return ResponseEntity.ok(commentService.findCommentByHotelId(hotelId));
    }

}
