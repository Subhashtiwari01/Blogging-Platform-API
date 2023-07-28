package com.Project.Blogging.Platform.API.Service;

import com.Project.Blogging.Platform.API.Model.AuthenticationToken;
import com.Project.Blogging.Platform.API.Reposetori.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;
    public void save(AuthenticationToken authenticationToken) {
        authenticationRepo.save(authenticationToken);

    }

    public boolean authenticate(String email, String token) {
        AuthenticationToken authenticationToken=authenticationRepo.findFirstByTokenValue(token);
        if(authenticationToken==null){
            return false;
        }
        String tokenConnectedEmail=authenticationToken.getUser().getUserEmail();
        return tokenConnectedEmail.equals(email);

    }
}
