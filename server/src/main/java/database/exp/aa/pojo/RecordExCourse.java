package database.exp.aa.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RecordExCourse {
    private Timestamp time;
    private Course course;
}
