package database.exp.aa.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RecordExStudent {
    private Timestamp time;
    private Student student;
}
