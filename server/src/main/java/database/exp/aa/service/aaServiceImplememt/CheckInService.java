package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.mapper.RecordMapper;
import database.exp.aa.pojo.Course;
import database.exp.aa.pojo.Record;
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
        Map<String,Object> data;
        if(c!=null){
            //有课
            List<Record> res = recordMapper.queryRecordByAllParam(
                tp.getSqlTimestamp(),
                (int)parameters.get("userId"),
                c.getId()
            );
            if(res.size() == 0){
                //还没打卡
                 data = new ImmutableMap.Builder<String,Object>()
                    .put("isCheckin",1)
                    .put("courseInfo",c)
                    .build();
            }else {
                //打卡了
                data = new ImmutableMap.Builder<String,Object>()
                    .put("isCheckin",2)
                    .put("courseInfo",c)
                    .build();
            }
        }else {
            //没课
            data = new ImmutableMap.Builder<String,Object>()
                .put("isCheckin",0)
                .build();
        }
        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> checkInByUser(JSONObject parameters) {
        //时间处理
        TimestampParser tp = new TimestampParser((long)parameters.get("nowTimestamp"));
        int res = recordMapper.createRecord(
            tp.getSqlTimestamp(),
            (int)parameters.get("userId"),
            (int)parameters.get("courseId")
        );
        if(res == 1){
            //打卡成功
            return AaResponse.createBySuccessMessage("打卡成功");
        }else {
            //打卡失败
            return AaResponse.createByErrorMessage("打卡失败");
        }
    }
}
