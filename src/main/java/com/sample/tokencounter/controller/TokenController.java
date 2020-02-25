package com.sample.tokencounter.controller;

import com.sample.tokencounter.dao.models.Tokens;
import com.sample.tokencounter.dto.CountResponse;
import com.sample.tokencounter.dto.TokenResponse;
import com.sample.tokencounter.service.ErrorException;
import com.sample.tokencounter.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TokenResponse> generateToken() {
        TokenResponse token = getTokenService().createToken();
        return new ResponseEntity(token, HttpStatus.CREATED);
    }

    @RequestMapping("/{path}")
    public ResponseEntity<CountResponse> getCount(@PathVariable String path,
                                  @RequestHeader("Authorization") @NotNull(message="A valid Token is required")
                                  @NotBlank(message="A valid Token is required") String token) {
        CountResponse response = null;
        try {
            response = getTokenService().tokenUsed(token, path);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ErrorException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}
