package com.Project.Blogging.Platform.API.Controller;

import com.Project.Blogging.Platform.API.Model.Comment;
import com.Project.Blogging.Platform.API.Model.DataObject.SignInInput;
import com.Project.Blogging.Platform.API.Model.DataObject.SignUpOutput;
import com.Project.Blogging.Platform.API.Model.Follow;
import com.Project.Blogging.Platform.API.Model.Post;
import com.Project.Blogging.Platform.API.Model.User;
import com.Project.Blogging.Platform.API.Service.AuthenticationService;
import com.Project.Blogging.Platform.API.Service.PostService;
import com.Project.Blogging.Platform.API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PostService postService;


    @PostMapping("userSignUp")

    public SignUpOutput userSignUp(@RequestBody User user) {
        return userService.userSignUp(user);
    }

    @PostMapping("userSignIn")

    public String userSignIn(@RequestBody SignInInput signInInput) {
        return userService.userSignIn(signInInput);
    }

    @PostMapping("post")

    public String createPost(@RequestBody Post post, @RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            return userService.createPost(post, email);

        } else {
            return "Not an authenticated Activity";
        }
    }


    @DeleteMapping("deletePost")

    public String removePost(@RequestParam Long postId, @RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            return userService.removePost(postId, email);
        } else {
            return "Not an authenticated Activity";
        }
    }

    @PostMapping("addComment")

    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail, @RequestParam String commenterToken) {
        if (authenticationService.authenticate(commenterEmail, commenterToken)) {
            return userService.addComment(comment, commenterEmail);
        } else {
            return "Not an authenticated Activity";
        }
    }


    @DeleteMapping("deleteComment")

    public String deleteComment(@RequestParam Long commentId, @RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            return userService.removeComment(commentId, email);
        } else {
            return "Not an authenticated activity";
        }
    }

    @PostMapping("follow")

    public String followUsers(@RequestBody Follow follow, @RequestParam String followerEmail, @RequestParam String followerToken) {
        if (authenticationService.authenticate(followerEmail, followerToken)) {
            return userService.followUser(follow, followerEmail);

        } else {
            return "Not an authenticated activity";
        }
    }
    @GetMapping("getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("getAllComments")
    public List<Comment> getAllComments(){
        return userService.getAllComments();
    }
    @GetMapping("getAllposts")

    public List<Post> getAllPosts(){
        return userService.getAllPosts();
    }
   @GetMapping("searchUserById/{userId}")

    public Optional<User> searchUsers(@PathVariable Long userId){
        return userService.searchUsers(userId);

   }
    @PutMapping("updateComment/{commentId}")
    public String updateComment(@PathVariable Long commentId, @RequestBody Comment comment,@RequestParam String commenterEmail,@RequestParam String commenterToken){
        if(authenticationService.authenticate(commenterEmail,commenterToken)){
            return userService.updateComment(commentId,comment,commenterEmail);
        }
        else{
            return "unAuthenticated activity";
        }
    }
    @PutMapping("updatePost/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestBody Post post,@RequestParam String posterEmail,@RequestParam String posterToken){
        if(authenticationService.authenticate(posterEmail,posterToken)){
            return userService.updatepost(postId,post,posterEmail);
        }
        else{
            return "unAuthenticated activity";
        }
    }



}