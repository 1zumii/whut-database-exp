package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.mapper.RecordMapper;
import database.exp.aa.pojo.Course;
import database.exp.aa.pojo.StudentCourseMap;
import database.exp.aa.service.aaServiceInterface.CheckInServiceInterface;
import database.exp.aa.util.AaResponse;
import database.exp.aa.util.TimestampParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckInService implements CheckInServiceInterface {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    RecordMapper recordMapper;

    @Override
    public AaResponse<Map<String, Object>> getCourseInfoByUser(JSONObject parameters) {
        //时间处理
        TimestampParser tp = new TimestampParser((long)parameters.get("nowTimestamp"));
        int dayIndex = tp.getDayIndex();
        int courseIndex = tp.getCourseIndex();

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
            System.out.println(c);
            System.out.println(tp.getSqlTimestamp().getClass());
            int res = recordMapper.createRecord(
                tp.getSqlTimestamp(),
                (int)parameters.get("userId"),
                c.getId()
            );
            System.out.println(res);
        }else {
            //没课
        }
        return null;
    }
}
