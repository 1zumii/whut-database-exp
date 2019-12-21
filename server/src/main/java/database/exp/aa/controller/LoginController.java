package database.exp.aa.controller;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.pojo.User;
import database.exp.aa.service.aaServiceImplememt.LoginService;
import database.exp.aa.util.AaResponse;
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
