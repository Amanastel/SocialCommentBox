package com.comment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "message", nullable = false, length = 255)
    @NotNull
    private String message;

    @Column(name = "comment_datetime", nullable = false)
    @NotNull
    private LocalDateTime commentDatetime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

}

