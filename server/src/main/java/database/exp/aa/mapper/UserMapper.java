package database.exp.aa.mapper;

import database.exp.aa.pojo.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Insert(
        "INSERT INTO users(username,password,studentId) "+
        "VALUES(#{username},#{password},#{studentId})"
    )
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer createUser(User user);

    @Select(
        "SELECT * FROM users" +
        " WHERE username = #{username}" +
        " AND password = #{password}"
    )
    @Results({
        @Result(column = "id",property = "id",id = true),
        @Result(column = "studentId",property = "studentId"),
        @Result(column = "avatar",property = "avatar")
    })
    User queryUserByUnPw(User user);
}
