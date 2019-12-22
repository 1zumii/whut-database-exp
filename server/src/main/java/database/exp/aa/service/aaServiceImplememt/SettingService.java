package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.UserMapper;
import database.exp.aa.pojo.User;
import database.exp.aa.service.aaServiceInterface.SettingServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SettingService implements SettingServiceInterface {
    @Autowired
    UserMapper userMapper;

    @Override
    public AaResponse<Map<String, Object>> updateUserInfoById(JSONObject parameters) {
        int res = userMapper.updateUserById(
            (int)parameters.get("id"),
            (String)parameters.get("newUsername"),
            (String)parameters.get("newPassword")
        );
        if(res == 1){
            //修改成功
            User u = userMapper.queryUserById((int)parameters.get("id"));
            Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
                .put("user",u)
                .build();
            return AaResponse.createBySuccess(data);
        }else {
            //修改失败
            return AaResponse.createByErrorMessage("用户信息修改失败");
        }
    }
}
