package com.project.UserService.Services;

import com.project.UserService.Models.UserRequest;
import com.project.UserService.Models.UserResponse;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);

    UserResponse addSession(UserRequest userRequest);

    UserResponse showLatestSession();

    byte[] generatePdf() throws IOException;
}
