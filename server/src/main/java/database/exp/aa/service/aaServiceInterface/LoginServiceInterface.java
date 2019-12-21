package database.exp.aa.service.aaServiceInterface;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.pojo.User;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface LoginServiceInterface {
    AaResponse<Map<String,Object>> createUser(User user,int studentId);

    AaResponse<Map<String,Object>> queryAllClasses();

    AaResponse<Map<String,Object>> createNewStudent(JSONObject parameters);

    AaResponse<Map<String,Object>> loginByUser(User user);
}
