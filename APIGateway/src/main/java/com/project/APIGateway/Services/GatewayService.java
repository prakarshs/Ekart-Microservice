package com.project.APIGateway.Services;

import com.project.APIGateway.External.Objects.UserRequest;
import reactor.core.publisher.Mono;

public interface GatewayService {

    Mono<Boolean> sendUserRequest(UserRequest userRequest);
    Mono<Boolean> sendUserSessions(UserRequest userRequest);
}
