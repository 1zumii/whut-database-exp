package database.exp.aa.controller;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.pojo.User;
import database.exp.aa.service.aaServiceImplememt.LoginService;
import database.exp.aa.util.AaResponse;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

//    @PostMapping("/kk")
//    public String kk(@RequestBody JSONObject jsonParam){
//        // 直接将json信息打印出来
//        System.out.println(jsonParam.toJSONString());
//        System.out.println(jsonParam.get("username"));
//        System.out.println(jsonParam.get("password"));
//
//        // 将获取的json数据封装一层，然后在给返回
//        JSONObject result = new JSONObject();
//        result.put("msg", "ok");
//        result.put("method", "json");
//        result.put("data", jsonParam);
//
//        return result.toJSONString();
//    }

//    @PostMapping("/createUser")
//    public AaResponse<Map<String,Object>> createUser(@RequestBody Map<String,Object> requestM){
//        /*
//        *   创建用户
//        */
//        User insertUser = (User)requestM.get("user");
//        return loginService.createUser(insertUser);
//    }

    @PostMapping("/getAllClasses")
    public AaResponse<Map<String,Object>> getAllClasses(){
        return loginService.queryAllClasses();
    }

    @PostMapping("/createNewStudent")
    public AaResponse<Map<String,Object>> createNewStudent(@RequestBody JSONObject parameters){
        return loginService.createNewStudent(parameters);
    }

    @PostMapping("/loginByUser")
    public AaResponse<Map<String,Object>> loginByUser(@RequestBody @Valid User user){
        return loginService.loginByUser(user);
    }
}
