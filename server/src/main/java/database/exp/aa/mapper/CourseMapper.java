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


    @Select("SELECT * FROM stu_crs_map WHERE courseId = #{id}")
    @Results({
        @Result(column = "studentId",property = "student",one=@One(
            select = "database.exp.aa.mapper.StudentMapper.queryStudentById"
        )),
        @Result(column = "courseId",property = "course",one = @One(
            select = "database.exp.aa.mapper.CourseMapper.getCourseById"
        ))
    })
    List<StudentCourseMap> getAllStudentsByCourseId(int id);


    @Select("SELECT * FROM courses WHERE id = #{id}")
    Course getCourseById(int id);


    @Insert(
        "INSERT INTO courses(courseName,teacher,dayIndex,courseIndex) "+
        "VALUES (#{courseName}, #{teacher}, #{dayIndex}, #{courseIndex})"
    )
    int insertCourse(
        @Param("courseName") String courseName,@Param("teacher") String teacher,
        @Param("dayIndex") int dayIndex,@Param("courseIndex") int courseIndex
    );


    @Select("SELECT * FROM courses")
    @Results({
        @Result(id = true,column = "id",property = "id"),
    })
    List<Course> getAllCourses();


    @Delete("DELETE FROM courses WHERE id = #{id}")
    int deleteCourseById(int id);


    @Delete("DELETE FROM records WHERE courseId = #{courseId}")
    int deleteRecordByCourseId(int courseId);


    @Delete("DELETE FROM stu_crs_map WHERE courseId = #{courseId}")
    int deleteMapByCourseId(int courseId);

    @Update(
        "UPDATE courses "+
        "SET courseName = #{courseName}, teacher = #{teacher}, dayIndex = #{dayIndex}, courseIndex = #{courseIndex} "+
        "WHERE id = #{courseId}"
    )
    int updateCourseInfo(
        @Param("courseId") int courseId, @Param("courseName") String courseName,
        @Param("teacher") String teacher, @Param("dayIndex") int dayIndex, @Param("courseIndex") int courseIndex
     );

    @Insert("INSERT INTO stu_crs_map(studentId,courseId) VALUES (#{studentId},#{courseId})")
    int insertStudentCourseMap(@Param("studentId") int studentId,@Param("courseId") int courseId);
}
