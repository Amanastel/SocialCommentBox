package com.comment.Model.DTO;

import com.comment.Model.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostWithCommentsDTO {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private List<CommentDTO> comments;

    public PostWithCommentsDTO(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();

        // Map the comments using CommentDTO.
        this.comments = post.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    // Getters and setters for the fields.
}
