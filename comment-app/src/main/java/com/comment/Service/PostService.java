package com.comment.Service;

import com.comment.Model.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post, String username);
    public Post getPostById(Long id);
    public Post DeletePostById(Long id);
    public List<Post> getAllPostsByUserName(String name);
}
