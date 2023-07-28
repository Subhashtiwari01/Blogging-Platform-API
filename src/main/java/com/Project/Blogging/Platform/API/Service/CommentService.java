package com.Project.Blogging.Platform.API.Service;

import com.Project.Blogging.Platform.API.Model.Comment;
import com.Project.Blogging.Platform.API.Reposetori.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;
    public String addComment(Comment comment) {
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment has been added";

    }

    public Comment findComment(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public void removeComment(Comment comment) {
        commentRepo.delete(comment);

    }


    public String updateComment(Comment comment) {
        commentRepo.save(comment);
        return "comment Updated";

    }
}
