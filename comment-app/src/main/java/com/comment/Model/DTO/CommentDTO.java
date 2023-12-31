package com.comment.Model.DTO;

import com.comment.Model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Long commentId;
    @NotNull
    private String message;
    private LocalDateTime commentDatetime;
    private String username;

    public CommentDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.message = comment.getMessage();
        this.commentDatetime = comment.getCommentDatetime();
        this.username = comment.getUser().getUsername();
    }

}