package com.sample.tokencounter.service;

import com.sample.tokencounter.dao.models.Tokens;
import com.sample.tokencounter.dto.CountResponse;
import com.sample.tokencounter.dto.TokenResponse;

public interface TokenService {

    public TokenResponse createToken();

    public CountResponse tokenUsed(String token, String path);

}
