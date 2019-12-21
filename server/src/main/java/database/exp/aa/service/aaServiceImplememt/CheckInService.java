package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.pojo.StudentCourseMap;
import database.exp.aa.service.aaServiceInterface.CheckInServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckInService implements CheckInServiceInterface {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public AaResponse<Map<String, Object>> getCourseInfoByUser(JSONObject parameters) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date((long)parameters.get("nowTimestamp")));
        List<StudentCourseMap> courses = courseMapper.getAllCoursesByStudentId((int)parameters.get("studentId"));
        Iterator<StudentCourseMap> it = courses.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getCourse());
        }
        return null;
    }
}
