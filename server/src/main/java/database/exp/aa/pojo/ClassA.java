package database.exp.aa.pojo;

import lombok.Data;

@Data
public class ClassA {
    private Integer id;
    private String className;
    private String major;
    private int classSize;
    private Student monitor;
}
