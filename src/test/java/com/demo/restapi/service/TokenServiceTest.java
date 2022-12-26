package com.demo.restapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TokenServiceTest {
    private TokenService tokenService;
    private Authentication authentication;
    private JwtEncoder jwtEncoder;

    @BeforeEach
    void setupService() {
        authentication = mock(Authentication.class);
        jwtEncoder = mock(JwtEncoder.class);
        tokenService = new TokenService(jwtEncoder);

    }

    @Test
    void shouldReturnToken() {
        //given
        String tokenValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU4NTY1Njg4NywiZXhwIjoxNTg1NjU3NDg3LCJhdWQiOiJzZWxmIiwic2NvcGUiOiJST0xFX1VTRVIgUk9MRV9BRE1JTiJ9.QGQb_D8lY3vZuOeZL-7EYF1RmwHYXPJ5Y5d5Y5YOMO8";
        Jwt jwt = mock(Jwt.class);
        when(authentication.getName()).thenReturn("user");
        when(jwt.getTokenValue()).thenReturn(tokenValue);
        when(jwtEncoder.encode(any())).thenReturn(jwt);
        //when
        ResponseEntity<Void> result = tokenService.login(authentication);
        //then
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Bearer " + tokenValue, result.getHeaders().getFirst("Authorization"));
    }
}