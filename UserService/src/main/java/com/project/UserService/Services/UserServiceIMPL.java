package com.project.UserService.Services;

import com.project.UserService.Entities.Session;
import com.project.UserService.Entities.User;
import com.project.UserService.Models.UserRequest;
import com.project.UserService.Models.UserResponse;
import com.project.UserService.Repositories.SessionRepository;
import com.project.UserService.Repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Log4j2
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        log.info("CHECKING USER...");
        if (userRepository.findByUserName(userRequest.getUserName()) == null) {
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
        } else {
            log.info("USER ALREADY EXISTS");
            return UserResponse.builder()
                    .message("USER ALREADY EXISTS.")
                    .build();
        }
    }

    @Override
    public UserResponse addSession(UserRequest userRequest) {
        log.info("CREATING SESSION...");
        Session session = Session.builder()
                .userName(userRequest.getUserName())
                .userEmail(userRequest.getUserEmail())
                .startTime(Instant.now())
                .build();
        sessionRepository.save(session);
        return UserResponse.builder()
                .message("SESSION HAS BEEN ADDED.")
                .userName(session.getUserName())
                .userEmail(session.getUserEmail())
                .build();
    }

    @Override
    public UserResponse showLatestSession() {
        Optional<Session> latestSession = sessionRepository.findFirstByOrderByUsersIdDesc();

        UserResponse userResponse = new UserResponse();
        latestSession.ifPresent(session -> {
            userResponse.setMessage("THIS IS THE LATEST SESSION");
            userResponse.setUserName(session.getUserName()); // Assuming getName() returns name
            userResponse.setUserEmail(session.getUserEmail()); // Assuming getEmail() returns email
        });
        return userResponse;
    }
}

