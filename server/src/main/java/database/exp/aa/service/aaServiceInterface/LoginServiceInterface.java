package database.exp.aa.service.aaServiceInterface;

import database.exp.aa.pojo.User;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface LoginServiceInterface {
    AaResponse<Map<String,Object>> createUser(User user);

    AaResponse<Map<String,Object>> queryAllClasses();
}
