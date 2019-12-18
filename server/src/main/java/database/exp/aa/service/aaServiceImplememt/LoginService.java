package database.exp.aa.service.aaServiceImplememt;

import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.UserMapper;
import database.exp.aa.pojo.User;
import database.exp.aa.service.aaServiceInterface.LoginServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService implements LoginServiceInterface {
    @Autowired
    UserMapper userMapper;

    @Override
    public AaResponse<Map<String, Object>> createUser(User user) {
        Integer res = userMapper.createUser(user);
        Map<String,Object> data =
                new ImmutableMap.Builder<String,Object>()
                        .put("userId",res)
                        .build();
        return AaResponse.createBySuccess(data);
    }
}
