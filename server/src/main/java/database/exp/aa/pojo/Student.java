package database.exp.aa.pojo;

import lombok.Data;

@Data
public class Student {
    private Integer studentId;
    private String stuNum;
    private String name;
    private int gender;     // 0-male,1-female
    private String phone;
    private ClassA classA;
}
