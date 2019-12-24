package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.mapper.StudentMapper;
import database.exp.aa.pojo.Course;
import database.exp.aa.pojo.Student;
import database.exp.aa.pojo.StudentCourseMap;
import database.exp.aa.service.aaServiceInterface.CourseManageServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CourseManageService implements CourseManageServiceInterface {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public AaResponse<Map<String, Object>> queryAllCourses() {
        List<Course> res = courseMapper.getAllCourses();

        Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
            .put("courseList",res)
            .build();

        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> queryCourseAllInfo(JSONObject parameters) {
        Course c = courseMapper.getCourseById((int)parameters.get("courseId"));

        if(c!=null){
            List<Student> allStudent = studentMapper.queryAllStudent();

            List<StudentCourseMap> res = courseMapper.getAllStudentsByCourseId((int)parameters.get("courseId"));
            Iterator<StudentCourseMap> it = res.iterator();
            List<Student> selectedStudent = new ArrayList<>();
            while (it.hasNext()){
                selectedStudent.add(it.next().getStudent());
            }

            Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
                .put("courseInfo",c)
                .put("all",allStudent)
                .put("selected",selectedStudent)
                .build();

            return AaResponse.createBySuccess(data);
        }else {
            return AaResponse.createByErrorMessage("未找到此课程");
        }
    }

    @Override
    public AaResponse<Map<String, Object>> deleteCourse(JSONObject parameters) {
        return null;
    }

    @Override
    public AaResponse<Map<String, Object>> updateCourse(JSONObject parameters) {
        int updateResult = courseMapper.updateCourseInfo(
            (int)parameters.get("courseId"),
            (String)parameters.get("courseName"),
            (String)parameters.get("teacher"),
            (int)parameters.get("dayIndex"),
            (int)parameters.get("courseIndex")
        );
        if(updateResult!=1){
            return AaResponse.createByErrorMessage("courses表更新异常");
        }
        //删除原来所有的map，再添加新的
        int deleteMapRes = courseMapper.deleteMapByCourseId((int)parameters.get("courseId"));
        Iterator it = ((ArrayList)parameters.get("selected")).iterator();
        while (it.hasNext()){
            courseMapper.insertStudentCourseMap(
                (int)it.next(),(int)parameters.get("courseId")
            );
        }
        return AaResponse.createBySuccessMessage("课程修改成功");
    }
}
