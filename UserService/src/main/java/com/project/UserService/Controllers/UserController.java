package com.project.UserService.Controllers;

import com.project.UserService.Models.UserRequest;
import com.project.UserService.Models.UserResponse;
import com.project.UserService.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.OK);}

    @PostMapping("/addSession")
    public ResponseEntity<UserResponse> addSession(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.addSession(userRequest), HttpStatus.OK);}

    @GetMapping("/show")
    public ResponseEntity<UserResponse> show(){
        return new ResponseEntity<>(userService.showLatestSession(), HttpStatus.OK);}
}
