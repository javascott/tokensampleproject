package com.sample.tokencounter.controller.service;

import com.sample.tokencounter.controller.dao.models.Tokens;

public interface TokenService {

    public Tokens createToken();

    public int tokenUsed(String token);

}
