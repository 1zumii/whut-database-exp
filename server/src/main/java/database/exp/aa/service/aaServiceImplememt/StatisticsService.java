package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.RecordMapper;
import database.exp.aa.pojo.RecordExCourse;
import database.exp.aa.pojo.RecordExStudent;
import database.exp.aa.service.aaServiceInterface.StatisticsServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService implements StatisticsServiceInterface {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public AaResponse<Map<String,Object>> queryRecordsByUserId(JSONObject parameters) {
        List<RecordExCourse> res = recordMapper.queryRecordsByUserId((int)parameters.get("userId"));

        Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
                .put("records",res)
                .build();

        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> queryRecordsByCourseId(JSONObject parameters) {
        List<RecordExStudent> res = recordMapper.queryRecordsByCourseId((int)parameters.get("courseId"));

        Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
                .put("records",res)
                .build();

        return AaResponse.createBySuccess(data);
    }
}
