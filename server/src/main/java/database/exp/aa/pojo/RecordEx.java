package database.exp.aa.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RecordEx {
    private Timestamp time;
    private Course course;
}
