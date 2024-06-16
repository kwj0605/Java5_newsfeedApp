package com.sparta.newsfeedapp.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentCreateRequestDto {
    private String content;
}
