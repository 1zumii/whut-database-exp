package database.exp.aa.mapper;

import database.exp.aa.pojo.ClassA;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassMapper {
    @Select("SELECT * FROM classes")
    @Results({
        @Result(column="id",property="id",id = true),
        @Result(column="className",property="className"),
        @Result(column="major",property="major"),
        @Result(column="classSize",property="classSize"),
        @Result(column="monitor",property="monitor"),
    })
    List<ClassA> queryAllClasses();
}
