package database.exp.aa.service.aaServiceInterface;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface CourseManageServiceInterface {
    AaResponse<Map<String,Object>> queryAllCourses();

    AaResponse<Map<String,Object>> queryCourseAllInfo(JSONObject parameters);

    AaResponse<Map<String,Object>> deleteCourse(JSONObject parameters);

    AaResponse<Map<String,Object>> updateCourse(JSONObject parameters);
}
