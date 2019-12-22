package database.exp.aa.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Record {
    private Timestamp time;
    private int userId;
    private int courseId;
}
