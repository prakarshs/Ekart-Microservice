package com.project.APIGateway.Controller;



import com.project.APIGateway.External.Objects.UserRequest;
import com.project.APIGateway.External.Objects.UserResponse;
import com.project.APIGateway.Services.GatewayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@Log4j2
@RequestMapping("/ekart")
public class SendUserController {
    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/login")
    public Mono<Void> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client, @AuthenticationPrincipal OidcUser oidcUser, ServerWebExchange exchange) {

        UserRequest userRequest = UserRequest.builder()
                .userName(oidcUser.getFullName())
                .userEmail(oidcUser.getEmail())
                .build();

        log.info("BEFORE FETCH STATEMENT");


        Mono<Boolean> sendUserRequest = gatewayService.sendUserRequest(userRequest);
        Mono<Boolean> sendUserSessions = gatewayService.sendUserSessions(userRequest); // Add the method for sending user sessions

        return sendUserRequest.zipWith(sendUserSessions) // Combine the results of both calls
                .flatMap(tuple -> {
                    boolean userRequestResult = tuple.getT1();
                    boolean userSessionsResult = tuple.getT2();

                    if (userRequestResult && userSessionsResult) {
                        // Perform the redirection using exchange.getResponse().getHeaders().setLocation(...)
                        exchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
                        exchange.getResponse().getHeaders().setLocation(URI.create("http://localhost:8085/ekart/home"));
                        return exchange.getResponse().setComplete(); // Complete the response without a body
                    } else {
                        // Handle other cases or errors
                        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                        return exchange.getResponse().setComplete();
                    }
                });
}}

