package com.sample.tokencounter.dao.mappers;

import com.sample.tokencounter.dao.models.Tokens;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TokensRepository {

    @Insert("INSERT INTO token (token, createdDate, expirationDate) values (#{token}, #{createdDate}, #{expirationDate})")
    @Options(useGeneratedKeys = true, keyProperty = "tokenId")
    public int createToken(Tokens tokens);

    @Select("SELECT * from token where token = #{token}")
    public Tokens getTokenByTokenId(String token);


}
