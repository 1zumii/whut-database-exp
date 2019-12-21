package database.exp.aa.controller;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.service.aaServiceImplememt.CheckInService;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/check-in")
public class CheckInController {
    @Autowired
    CheckInService checkInService;

    @PostMapping("/getCourseInfoByUser")
    public AaResponse<Map<String, Object>> getCourseInfoByUser(@RequestBody JSONObject parameters){
        return checkInService.getCourseInfoByUser(parameters);
    }
}
