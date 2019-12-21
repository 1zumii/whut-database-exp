package database.exp.aa.pojo;

import lombok.Data;

@Data
public class Course {
    private int id;
    private String courseName;
    private String teacher;
    private int dayIndex;
    private int courseIndex;
}
