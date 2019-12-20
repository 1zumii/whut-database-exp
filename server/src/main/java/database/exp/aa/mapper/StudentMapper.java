package database.exp.aa.mapper;

import database.exp.aa.pojo.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student queryStudentById(Integer id);
}
