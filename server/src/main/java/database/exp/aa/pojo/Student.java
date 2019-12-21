package database.exp.aa.pojo;

import lombok.Data;

@Data
public class Student {
    private int studentId;
    private String stuNum;
    private String name;
    private int gender;     // 0-female,1-male
    private String phone;
    private int classId;
}
