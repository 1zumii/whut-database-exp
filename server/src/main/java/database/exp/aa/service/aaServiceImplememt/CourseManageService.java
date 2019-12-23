package database.exp.aa.service.aaServiceImplememt;

import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.pojo.Course;
import database.exp.aa.service.aaServiceInterface.CourseManageServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseManageService implements CourseManageServiceInterface {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public AaResponse<Map<String, Object>> queryAllCourses() {
        List<Course> res = courseMapper.getAllCourses();

        Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
                .put("courseList",res)
                .build();

        return AaResponse.createBySuccess(data);
    }
}
