package com.comment.Service;

import com.comment.Model.DTO.PostDTO;
import com.comment.Model.DTO.PostWithCommentsDTO;
import com.comment.Model.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post, String username);
    public PostDTO getPostById(Long id);
    public Post DeletePostById(Long id);
    public List<PostWithCommentsDTO> getAllPostsByUserName(String name);
}
