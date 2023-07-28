package com.Project.Blogging.Platform.API.Service;

import com.Project.Blogging.Platform.API.Model.Post;
import com.Project.Blogging.Platform.API.Model.User;
import com.Project.Blogging.Platform.API.Reposetori.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;
    public String savePost(Post post) {
        post.setPostCreatedTime(LocalDateTime.now());
        postRepo.save(post);
        return "Post uploaded";

    }

    public String removePost(Long postId, User user) {
        Post post=postRepo.findById(postId).orElse(null);
        if(post!=null && post.getUser().equals(user)){
            postRepo.deleteById(postId);
            return "Remove SuccessFully";
        } else if (post==null) {
            return "post to be deleted doesNot Exist";

        }
        else {
            return "Un-Authorised delete detected....Not Allowed";
        }
    }


    public boolean validPost(Post post) {
        return (post!=null && postRepo.existsById(post.getPostId()));
    }

    public String updatePost(Post post) {
         postRepo.save(post);
        return"post updated";
    }
}
