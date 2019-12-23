package database.exp.aa.service.aaServiceInterface;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface SettingServiceInterface {
    AaResponse<Map<String,Object>> updateUserInfoById(JSONObject parameters);

    AaResponse<Map<String,Object>> queryCoursesByStudentId(JSONObject parameters);

    AaResponse<Map<String,Object>> addCourse(JSONObject parameters);
}
