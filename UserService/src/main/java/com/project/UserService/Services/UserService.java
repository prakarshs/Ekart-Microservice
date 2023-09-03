package com.project.UserService.Services;

import com.project.UserService.Models.UserRequest;
import com.project.UserService.Models.UserResponse;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);

    UserResponse addSession(UserRequest userRequest);

    UserResponse showLatestSession();
}
