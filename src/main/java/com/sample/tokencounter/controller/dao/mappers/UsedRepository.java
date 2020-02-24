package com.sample.tokencounter.controller.dao.mappers;

import com.sample.tokencounter.controller.dao.models.Used;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsedRepository {

    @Select("SELECT COUNT(u.usedId) from used u inner join token t on u.tokenId = t.tokenId where t.token = #{token}")
    public int getTokenCount(String token);

    @Insert("INSERT INTO used (tokenId, usedDate) values (#{tokenId}, #{usedDate})")
    public int insertUsedToken(Used used);

}
