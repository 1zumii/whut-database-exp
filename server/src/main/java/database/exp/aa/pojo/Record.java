package database.exp.aa.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Record {
    private Date date;
    private User user;
    private ClassA classA;
}
