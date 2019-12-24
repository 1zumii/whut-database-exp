package database.exp.aa.controller;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.service.aaServiceImplememt.CourseManageService;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/course-manage")
public class CourseController {
    @Autowired
    CourseManageService courseManageService;

    @PostMapping("/queryAllCourses")
    public AaResponse<Map<String,Object>> queryAllCourses(){
        return courseManageService.queryAllCourses();
    }

    @PostMapping("/query-courseAllInfo")
    public AaResponse<Map<String,Object>> queryCourseAllInfo(@RequestBody JSONObject parameters){
        return courseManageService.queryCourseAllInfo(parameters);
    }

    @PostMapping("/update-course")
    public AaResponse<Map<String,Object>> updateCourse(@RequestBody JSONObject parameters){
        return courseManageService.updateCourse(parameters);
    }

    @PostMapping("/delete-course")
    public AaResponse<Map<String,Object>> deleteCourseById(@RequestBody JSONObject parameters){
        return courseManageService.deleteCourse(parameters);
    }
}
