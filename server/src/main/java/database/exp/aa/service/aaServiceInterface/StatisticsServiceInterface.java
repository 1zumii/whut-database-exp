package database.exp.aa.service.aaServiceInterface;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface StatisticsServiceInterface {
    AaResponse<Map<String,Object>> queryRecordsByUserId(JSONObject parameters);

    AaResponse<Map<String,Object>> queryRecordsByCourseId(JSONObject parameters);
}
