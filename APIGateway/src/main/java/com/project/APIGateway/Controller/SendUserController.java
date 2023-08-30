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
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("/ekart")
public class SendUserController {
    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/login")
    public Mono<RedirectView> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client, @AuthenticationPrincipal OidcUser oidcUser, ServerWebExchange exchange) {

        UserRequest userRequest = UserRequest.builder()
                .userName(oidcUser.getFullName())
                .userEmail(oidcUser.getEmail())
                .build();
        log.info("BEFORE FETCH STATEMENT");
        return gatewayService.sendUserRequest(userRequest)
                .map(res -> {
                    log.info("INSIDE RETURN STATEMENT");
                    // Handle your business logic here based on the response
                    if (res) {
                        // Create a RedirectView to ekart/home
                        log.info("REDIRECTION STAGE PE");
                        return new RedirectView("http://localhost:8085/ekart/home");
                    } else {
                        // Handle other cases or errors
                        log.info("NULLSTAGE PE");
                        return null; // Return null or handle differently
                    }
                });
}}

