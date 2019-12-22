package database.exp.aa.mapper;

import database.exp.aa.pojo.Course;
import database.exp.aa.pojo.StudentCourseMap;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {
    @Select("SELECT * FROM stu_crs_map WHERE studentId = #{id}")
    @Results({
        @Result(column = "studentId",property = "student",one=@One(
            select = "database.exp.aa.mapper.StudentMapper.queryStudentById"
        )),
        @Result(column = "courseId",property = "course",one = @One(
            select = "database.exp.aa.mapper.CourseMapper.getCourseById"
        ))
    })
    List<StudentCourseMap> getAllCoursesByStudentId(int id);

    @Select("SELECT * FROM courses WHERE id = #{id}")
    Course getCourseById(int id);


}
