package com.Project.Blogging.Platform.API.Service;

import com.Project.Blogging.Platform.API.Model.*;
import com.Project.Blogging.Platform.API.Model.DataObject.SignInInput;
import com.Project.Blogging.Platform.API.Model.DataObject.SignUpOutput;
import com.Project.Blogging.Platform.API.Reposetori.ICommentRepo;
import com.Project.Blogging.Platform.API.Reposetori.IPostRepo;
import com.Project.Blogging.Platform.API.Reposetori.IUserRepo;
import com.Project.Blogging.Platform.API.Service.HashingUtility.EmailHandler;
import com.Project.Blogging.Platform.API.Service.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    FollowerService followerService;
    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    IPostRepo postRepo;
    public SignUpOutput userSignUp(User user) {
        boolean signUpStatus=true;
        String signUpStatusMessage=null;

        User existingUser=userRepo.findFirstByuserEmail(user.getUserEmail());

        if(existingUser!=null){
            signUpStatus=false;
            signUpStatusMessage="User already registered";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        try {
            String encryptedPassword= PasswordEncrypter.encryptionPassword(user.getPassword());

            user.setPassword(encryptedPassword);
            userRepo.save(user);

            signUpStatus=true;
            signUpStatusMessage="User registered Successfully";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);

        } catch (Exception e) {
            signUpStatus=false;
            signUpStatusMessage="Internal Error occurred";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }



    }

    public String userSignIn(SignInInput signInInput) {
        String signInStatusMessage = null;

        User userEmail = userRepo.findFirstByuserEmail(signInInput.getEmail());

        if (userEmail == null) {
            signInStatusMessage = "Email Not registered";
            return signInStatusMessage;
        }
        try {
            String encryptedPassword=PasswordEncrypter.encryptionPassword(signInInput.getPassword());
            if(userEmail.getPassword().equals(encryptedPassword)){
                AuthenticationToken authenticationToken=new AuthenticationToken(userEmail);
                authenticationService.save(authenticationToken);
                String toEmail=userEmail.getUserEmail();
                EmailHandler.sendEmail(toEmail,"Authentication token",authenticationToken.getTokenValue());
                return "Token sent to Email";
            }
            else {
                signInStatusMessage="Invalid credential";
                return signInStatusMessage;
            }
        } catch (Exception e) {
            signInStatusMessage="Internal Error Occurred";
            return signInStatusMessage;
        }


    }

    public String createPost(Post post, String email) {
        User postOwner=userRepo.findFirstByuserEmail(email);
        post.setUser(postOwner);
        return postService.savePost(post);
    }

    public String removePost(Long postId, String email) {
        User user=userRepo.findFirstByuserEmail(email);
        return postService.removePost(postId,user);

    }


    public String addComment(Comment comment, String commenterEmail) {
        boolean postValid=postService.validPost(comment.getPost());
        if(postValid){
            User commenter=userRepo.findFirstByuserEmail(commenterEmail);
            comment.setCommenter(commenter);
            return commentService.addComment(comment);
        }
        else {
            return "Can not comment on invalid post";
        }
    }

    public String removeComment(Long commentId, String email) {
        Comment comment=commentService.findComment(commentId);
        if(comment!=null){
            if(authorizeCommentRemover(email,comment)){
                commentService.removeComment(comment);
                return "Comment deleted successFully";
            }
            else{
                return "Unauthorized delete detected";
            }
        }
        return "Invalid comment";
    }

    private boolean authorizeCommentRemover(String email, Comment comment) {
        String commentOwnerEmail=comment.getCommenter().getUserEmail();
        String postOwnerEmail=comment.getPost().getUser().getUserEmail();
        return postOwnerEmail.equals(email)|| commentOwnerEmail.equals(email);

    }

    public String followUser(Follow follow, String followerEmail) {
        User followTargetUser=userRepo.findById(follow.getCurrentUser().getUserId()).orElse(null);
        User follower=userRepo.findFirstByuserEmail(followerEmail);
        if(followTargetUser!=null){
            if(followerService.isFollowAllowed(followTargetUser,follower)){
                followerService.startFollowing(follow,follower);
                return follower.getUserHandle()+"is now following"+followTargetUser.getUserHandle();
            }
            else {
                return follower.getUserHandle()+"already follows"+followTargetUser.getUserHandle();
            }
        }
        else {
            return "User to be followed is invalid";
        }

    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }




    public Optional<User> searchUsers(Long userId) {
        return userRepo.findById(userId);
    }


    public String updateComment(Long commentId, Comment comment, String commenterEmail) {
        Optional<Comment> newComment=commentRepo.findById(commentId);
        if(newComment!=null){
            return commentService.updateComment(comment);
        }
        else {
            return "Comment Not found";
        }

    }

    public String updatepost(Long postId, Post post, String posterEmail) {
        Optional<Post>newPost=postRepo.findById(postId);
        if(newPost!=null){
            return postService.updatePost(post);
        }
        else {
            return "Post not found";
        }
    }
}
