package com.project.APIGateway.Controller;

import com.project.APIGateway.External.Objects.UserRequest;
import com.project.APIGateway.Models.AuthResponse;
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
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client, @AuthenticationPrincipal OidcUser oidcUser, Model model){

        AuthResponse authResponse = AuthResponse.builder()
                .userId(oidcUser.getEmail())
                .userName(oidcUser.getName())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expireAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .authorities(oidcUser.getAuthorities().stream().map(grantedAuthority -> {return grantedAuthority.getAuthority();}).collect(Collectors.toList())).build();


        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
}
