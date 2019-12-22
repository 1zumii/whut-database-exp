package database.exp.aa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface RecordMapper {
    @Insert(
        "INSERT INTO records(time,userId,courseId) "+
        "VALUES (#{ts},#{userId},#{courseId})"
    )
    int createRecord(@Param("ts") Timestamp ts, @Param("userId") int userId,@Param("courseId") int courseId);
}
