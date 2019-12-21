package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.pojo.Course;
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
        //时间处理
        Calendar now = Calendar.getInstance();
        now.setTime(new Date((long)parameters.get("nowTimestamp")));
        int dayIndex = now.get(Calendar.DAY_OF_WEEK);
        int courseIndex = (now.get(Calendar.HOUR_OF_DAY)<12)?1:2;

        //找有没有课
        List<StudentCourseMap> courses = courseMapper.getAllCoursesByStudentId((int)parameters.get("studentId"));
        Iterator<StudentCourseMap> it = courses.iterator();
        Course c = null;
        while (it.hasNext()){
            c = it.next().getCourse();
            if(
                c.getDayIndex() == dayIndex &&
                c.getCourseIndex() == courseIndex
            ){
                break;
            }
        }
        if(c!=null){
            //有课
        }else {
            //没课
        }
        return null;
    }
}
