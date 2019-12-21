package database.exp.aa.service.aaServiceInterface;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface CheckInServiceInterface {
    AaResponse<Map<String,Object>> getCourseInfoByUser(JSONObject parameters);
}
