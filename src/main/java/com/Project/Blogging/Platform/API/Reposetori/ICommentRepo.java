package com.Project.Blogging.Platform.API.Reposetori;

import com.Project.Blogging.Platform.API.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {
}
