package database.exp.aa.mapper;

import database.exp.aa.pojo.Record;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

public interface RecordMapper {
    @Insert(
        "INSERT INTO records(time,userId,courseId) "+
        "VALUES (#{ts},#{userId},#{courseId})"
    )
    int createRecord(@Param("ts") Timestamp ts, @Param("userId") int userId,@Param("courseId") int courseId);

    @Select(
        "SELECT * FROM records "+
        "WHERE DATE(time) = DATE(#{ts}) "+
        "AND userId = #{userId} "+
        "AND courseId = #{courseId}"
    )
    @Results({
        @Result(column = "userId",property = "userId"),
        @Result(column = "courseId",property = "courseId")
    })
    List<Record> queryRecordByAllParam(@Param("ts") Timestamp ts, @Param("userId") int userId, @Param("courseId") int courseId);
}
