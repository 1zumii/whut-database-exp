package database.exp.aa.controller;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.service.aaServiceImplememt.StatisticsService;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @PostMapping("/query-studentRecords")
    public AaResponse<Map<String,Object>> queryStudentRecords(@RequestBody JSONObject parameters){
        return statisticsService.queryRecordsByUserId(parameters);
    }
}
