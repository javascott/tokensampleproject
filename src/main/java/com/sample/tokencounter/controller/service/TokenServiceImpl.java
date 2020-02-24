package com.sample.tokencounter.controller.service;

import com.sample.tokencounter.controller.dao.mappers.TokensRepository;
import com.sample.tokencounter.controller.dao.mappers.UsedRepository;
import com.sample.tokencounter.controller.dao.models.Tokens;
import com.sample.tokencounter.controller.dao.models.Used;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;

@Service
@EnableAutoConfiguration
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TokenServiceImpl implements TokenService {

    private TokensRepository tokensRepository;
    private UsedRepository usedRepository;

    public TokenServiceImpl(TokensRepository tokensRepository, UsedRepository usedRepository) {
        this.tokensRepository = tokensRepository;
        this.usedRepository = usedRepository;
    }

    @Override
    public Tokens createToken() {
        Tokens token = new Tokens();
        token.setToken(generateNewToken());
        token.setCreatedDate(Calendar.getInstance().getTime());
        int saved = getTokensRepository().createToken(token);
        if (saved > 0) {
            return token;
        } else {
            return null;
            //throw new ErrorException();
        }

    }

    @Override
    public int tokenUsed(String token) {
        Tokens tokens = getTokensRepository().getTokenByTokenId(token);
        if (tokens != null) {
            Used used = new Used();
            used.setTokenId(tokens.getTokenId());
            used.setUsedDate(Calendar.getInstance().getTime());
            getUsedRepository().insertUsedToken(used);
            int count = getUsedRepository().getTokenCount(token);
            return count;
        } else {
            return 0;
        }
    }

    private String generateNewToken() {
        String possibleValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder(128);
        for (int i = 0; i < 128; i++) {
            int random = (int)(possibleValues.length() * Math.random());
            stringBuilder.append(possibleValues.charAt(random));
        }
        return stringBuilder.toString();
    }

    public TokensRepository getTokensRepository() {
        return tokensRepository;
    }

    public UsedRepository getUsedRepository() {
        return usedRepository;
    }
}
