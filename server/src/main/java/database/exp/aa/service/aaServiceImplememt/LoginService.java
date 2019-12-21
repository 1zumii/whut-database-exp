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
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        boolean isAdmin = (u.getStudentId() == -1);
        Map<String,Object> data =
                new ImmutableMap.Builder<String,Object>()
                        .put("userId",u.getId())
                        .put("userInfo",u)
                        .put("studentInfo",s)
                        .put("isAdmin",isAdmin)
                        .build();
        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> loginByUser(User user) {
        User res = userMapper.queryUserByUnPw(user);
        if(res!=null){
            boolean isAdmin = (res.getStudentId() == -1);
            Student s = studentMapper.queryStudentById(res.getStudentId());
            Map<String,Object> data;
            if(s!=null){
                 data = new ImmutableMap.Builder<String,Object>()
                    .put("userId",res.getId())
                    .put("userInfo",res)
                    .put("studentInfo",s)
                    .put("isAdmin",isAdmin)
                    .build();
            }else {
                data = new ImmutableMap.Builder<String,Object>()
                    .put("userId",user.getId())
                    .put("userInfo",res)
                    .put("isAdmin",isAdmin)
                    .build();
            }
            return AaResponse.createBySuccess(data);
        }else {
            return AaResponse.createByErrorMessage("登录失败");
        }
    }
}
