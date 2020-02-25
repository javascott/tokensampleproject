package com.sample.tokencounter.service;

import com.sample.tokencounter.dao.mappers.TokensRepository;
import com.sample.tokencounter.dao.mappers.UsedRepository;
import com.sample.tokencounter.dao.models.Tokens;
import com.sample.tokencounter.dao.models.Used;
import com.sample.tokencounter.dto.CountResponse;
import com.sample.tokencounter.dto.TokenResponse;
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

    private int tokenLength = 64;

    public TokenServiceImpl(TokensRepository tokensRepository, UsedRepository usedRepository) {
        this.tokensRepository = tokensRepository;
        this.usedRepository = usedRepository;
    }

    @Override
    public TokenResponse createToken() {
        Tokens token = new Tokens();
        token.setToken(generateNewToken());
        token.setCreatedDate(Calendar.getInstance().getTime());
        int saved = getTokensRepository().createToken(token);
        if (saved > 0) {
            return new TokenResponse(token.getToken());
        } else {
            return null;
            //throw new ErrorException();
        }

    }

    @Override
    public CountResponse tokenUsed(String token, String path) {
        Tokens tokens = getTokensRepository().getTokenByTokenId(token);
        if (tokens != null) {
            Used used = new Used();
            used.setPath(path);
            used.setUsedDate(Calendar.getInstance().getTime());
            getUsedRepository().insertUsedPath(used);
            int count = getUsedRepository().getPathCount(path);
            return new CountResponse(count);
        } else {
            return new CountResponse(0);
        }
    }

    private String generateNewToken() {
        String possibleValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder(tokenLength);
        for (int i = 0; i < tokenLength; i++) {
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
