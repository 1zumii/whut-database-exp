package database.exp.aa.service.aaServiceInterface;

import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface CourseManageServiceInterface {
    AaResponse<Map<String,Object>> queryAllCourses();
}
