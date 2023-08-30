package com.project.APIGateway.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

   private String userId;
   private String userName;
   private String accessToken;
   private String refreshToken;
   private Long expireAt;
   private Collection<String> authorities;


}
