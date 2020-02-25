package com.sample.tokencounter.dao.mappers;

import com.sample.tokencounter.dao.models.Used;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsedRepository {

    @Select("SELECT COUNT(u.usedId) from used u where u.path = #{path}")
    public int getPathCount(String path);

    @Insert("INSERT INTO used (path, usedDate) values (#{path}, #{usedDate})")
    public int insertUsedPath(Used used);

}
