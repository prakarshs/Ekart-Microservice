package com.project.APIGateway.Services;


import com.project.APIGateway.External.Objects.UserRequest;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@Data
public class GatewayServiceIMPL implements GatewayService {

    private final WebClient webClient;

    public GatewayServiceIMPL(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.baseUrl("http://localhost:8084/users").build();
    }
    @Override
    public Mono<Boolean> sendUserRequest(UserRequest userRequest) {
        return webClient.post()
                .uri("/add")
                .bodyValue(userRequest)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        log.info("USER SENT!");
                        return Mono.just(true);
                    } else {
                        log.warn("Failed to send user!");
                        return Mono.just(false);
                    }
                });
    }

    @Override
    public Mono<Boolean> sendUserSessions(UserRequest userRequest) {
        return webClient.post()
                .uri("/addSession")
                .bodyValue(userRequest)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        log.info("SESSION SENT!");
                        return Mono.just(true);
                    } else {
                        log.warn("Failed to send session!");
                        return Mono.just(false);
                    }
                });
    }


}



