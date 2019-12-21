package database.exp.aa.pojo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int studentId;
    private String avatar;
}
