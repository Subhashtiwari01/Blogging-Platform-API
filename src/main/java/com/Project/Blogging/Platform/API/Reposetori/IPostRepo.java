package com.Project.Blogging.Platform.API.Reposetori;

import com.Project.Blogging.Platform.API.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post,Long> {
}
