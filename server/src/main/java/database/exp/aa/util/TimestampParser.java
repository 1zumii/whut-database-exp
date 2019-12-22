package database.exp.aa.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimestampParser {
    private Calendar c = Calendar.getInstance();
    private Timestamp t;

    public TimestampParser(long timestamp){
        this.t = new Timestamp(timestamp);
        this.c.setTime(new Date(timestamp));
    }

    public int getDayIndex(){
        return this.c.get(Calendar.DAY_OF_WEEK);
    }

    public int getCourseIndex(){
        return (this.c.get(Calendar.HOUR_OF_DAY)<12)?1:2;
    }

    public Timestamp getSqlTimestamp(){
        return this.t;
    }
}
