package database.exp.aa.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Record {
    private Date date;
    private int userId;
    private int courseId;
}
