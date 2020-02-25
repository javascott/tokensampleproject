package com.sample.tokencounter.controller;

import com.sample.tokencounter.dao.models.Tokens;
import com.sample.tokencounter.dto.CountResponse;
import com.sample.tokencounter.dto.TokenResponse;
import com.sample.tokencounter.service.TokenService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
public class TokenController {

    private TokenService tokenService;
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping("/auth")
    public TokenResponse generateToken() {
        TokenResponse token = getTokenService().createToken();
        return token;
    }

    @RequestMapping("/{path}")
    public CountResponse getCount(@PathVariable String path,
                                  @RequestHeader("Authorization") @NotNull(message="A valid Token is required")
                                  @NotBlank(message="A valid Token is required") String token) {
        return getTokenService().tokenUsed(token, path);
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}
