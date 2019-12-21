package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.ClassMapper;
import database.exp.aa.mapper.StudentMapper;
import database.exp.aa.mapper.UserMapper;
import database.exp.aa.pojo.ClassA;
import database.exp.aa.pojo.Student;
import database.exp.aa.pojo.User;
import database.exp.aa.service.aaServiceInterface.LoginServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Service
public class LoginService implements LoginServiceInterface {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public AaResponse<Map<String, Object>> createUser(User user,int studentId) {
        Integer res = userMapper.createUser(user);
        Map<String,Object> data =
            new ImmutableMap.Builder<String,Object>()
                    .put("userId",res)
                    .build();
        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> queryAllClasses() {
        List<ClassA> res = classMapper.queryAllClasses();
        Map<String,Object> data =
                new ImmutableMap.Builder<String,Object>()
                        .put("classList",res)
                        .build();
        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> createNewStudent(JSONObject parameters) {
        Student s = new Student();
        s.setStuNum((String)parameters.get("stuNum"));
        s.setName((String)parameters.get("name"));
        s.setGender((int)parameters.get("gender"));
        s.setPhone((String)parameters.get("phone"));
        s.setClassId((int)parameters.get("classId"));
        studentMapper.createStudent(s);

        User u = new User();
        u.setUsername((String)parameters.get("username"));
        u.setPassword((String)parameters.get("password"));
        u.setStudentId(s.getStudentId());
        userMapper.createUser(u);
        Map<String,Object> data =
                new ImmutableMap.Builder<String,Object>()
                        .put("userId",u.getId())
                        .build();
        return AaResponse.createBySuccess(data);
    }
}
