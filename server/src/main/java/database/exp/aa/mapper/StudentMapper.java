package database.exp.aa.mapper;

import database.exp.aa.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student queryStudentById(Integer id);

    @Insert(
        "INSERT INTO students(stuNum,name,gender,phone,classId)"+
        "VALUES(#{stuNum},#{name},#{gender},#{phone},#{classId})"
    )
    @Options(useGeneratedKeys = true,keyProperty = "studentId",keyColumn = "id")
    Integer createStudent(Student student);
}
