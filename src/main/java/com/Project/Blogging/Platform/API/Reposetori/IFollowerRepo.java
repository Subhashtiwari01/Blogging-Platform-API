package com.Project.Blogging.Platform.API.Reposetori;

import com.Project.Blogging.Platform.API.Model.Follow;
import com.Project.Blogging.Platform.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface IFollowerRepo extends JpaRepository<Follow,Long> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User followTargetUser, User follower);
}
