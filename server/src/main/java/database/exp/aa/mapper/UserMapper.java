package database.exp.aa.mapper;

import database.exp.aa.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface UserMapper {
    @Insert(
        "INSERT INTO users(username,password,studentId"+
        "VALUES(#{username},#{password},#{studentId})"
    )
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer createUser(User user);
}
