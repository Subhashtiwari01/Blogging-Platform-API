package com.Project.Blogging.Platform.API.Reposetori;

import com.Project.Blogging.Platform.API.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByuserEmail(String userEmail);
}
