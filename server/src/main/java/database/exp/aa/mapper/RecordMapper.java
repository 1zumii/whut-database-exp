package database.exp.aa.mapper;

import database.exp.aa.pojo.Record;
import database.exp.aa.pojo.RecordEx;
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

    @Select("SELECT * FROM records WHERE userId = #{userId} ORDER BY time DESC")
    @Results({
        @Result(column = "time",property = "time"),
        @Result(column = "courseId",property = "course" ,one = @One(
            select = "database.exp.aa.mapper.CourseMapper.getCourseById"
        ))
    })
    List<RecordEx> queryRecordsByUserId(@Param("userId") int userId);
}
