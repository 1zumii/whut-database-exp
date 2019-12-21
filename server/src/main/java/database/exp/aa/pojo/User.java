package database.exp.aa.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {
    private int id;
    @NotBlank(message = "用户名不能为空")
    @Size(max = 255)
    private String username;
    @NotBlank
    @Size(max = 60)
    private String password;
    private int studentId;
    private String avatar;
}
