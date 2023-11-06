package com.comment.Model.DTO;

import com.comment.Model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private List<PostDTO> posts;

    public UserDTO(Long userId, String username, String email, List<PostDTO> posts) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.posts = posts;
    }

    // Getters and setters (or Lombok annotations) for the fields

    public UserDTO(User user, List<PostDTO> posts) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.posts = posts;
    }
}

