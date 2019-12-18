package database.exp.aa.service.aaServiceInterface;

import database.exp.aa.pojo.User;
import database.exp.aa.util.AaResponse;

import java.util.Map;

public interface LoginServiceInterface {
    public AaResponse<Map<String,Object>> createUser(User user);
}
