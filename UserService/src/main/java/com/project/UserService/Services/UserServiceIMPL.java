package com.project.UserService.Services;

import com.project.UserService.Entities.User;
import com.project.UserService.Models.UserRequest;
import com.project.UserService.Models.UserResponse;
import com.project.UserService.Repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceIMPL implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponse addUser(UserRequest userRequest) {
        log.info("CHECKING USER...");
        if(userRepository.findByUserName(userRequest.getUserName())==null)
        {
            log.info("CREATING USER...");
            User user = User.builder()
                    .userName(userRequest.getUserName())
                    .userEmail(userRequest.getUserEmail())
                    .build();
            userRepository.save(user);
            return UserResponse.builder()
                    .message("USER HAS BEEN ADDED.")
                    .userName(user.getUserName())
                    .userEmail(user.getUserEmail())
                    .build();
        }
        else{
            log.info("USER ALREADY EXISTS");
            return UserResponse.builder()
                    .message("USER ALREADY EXISTS.")
                    .build();
        }
    }
}
