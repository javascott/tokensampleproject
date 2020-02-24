package com.sample.tokencounter.controller;

import com.sample.tokencounter.controller.dao.models.Tokens;
import com.sample.tokencounter.controller.service.TokenService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private TokenService tokenService;
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/")
    public Tokens generateToken() {
        Tokens token = getTokenService().createToken();
        return token;
    }

    @RequestMapping("/{token}")
    public String getCount(@PathVariable String token) {
        int count = getTokenService().tokenUsed(token);
        return "" + count;
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}
