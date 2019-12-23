package database.exp.aa.mapper;

import database.exp.aa.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id = #{id}")
    @Results({
        @Result(id = true,column = "id",property = "studentId")
    })
    Student queryStudentById(Integer id);

    @Insert(
        "INSERT INTO students(stuNum,name,gender,phone,classId)"+
        "VALUES(#{stuNum},#{name},#{gender},#{phone},#{classId})"
    )
    @Options(useGeneratedKeys = true,keyProperty = "studentId",keyColumn = "id")
    Integer createStudent(Student student);

    @Select("SELECT * FROM students")
    @Results({
        @Result(id = true,column = "id",property = "studentId")
    })
    List<Student> queryAllStudent();
}
