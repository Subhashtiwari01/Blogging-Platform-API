package com.Project.Blogging.Platform.API.Service;

import com.Project.Blogging.Platform.API.Model.Follow;
import com.Project.Blogging.Platform.API.Model.User;
import com.Project.Blogging.Platform.API.Reposetori.IFollowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {
    @Autowired
    IFollowerRepo followerRepo;
    public boolean isFollowAllowed(User followTargetUser, User follower) {
        List<Follow> followList=followerRepo.findByCurrentUserAndCurrentUserFollower(followTargetUser,follower);
        return followList!=null && followList.isEmpty() && !followTargetUser.equals(follower);

    }

    public void startFollowing(Follow follow, User follower) {
        follow.setCurrentUserFollower(follower);
        followerRepo.save(follow);

    }
}
