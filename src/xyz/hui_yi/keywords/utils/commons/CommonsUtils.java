package xyz.hui_yi.keywords.utils.commons;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonsUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","").trim();
    }

    /**
     * @return 2018-12-13 14:57:17.578
     */
    public static Timestamp getTimestamp(){
        return new Timestamp(new Date().getTime());
    }

    /**
     * @return 2018-12-13 14:57:17
     */
    public static String getFormatTimestamp(){
        return timeFormat(new Timestamp(new Date().getTime())).toString();
    }
    /**
     * @param timestamp
     * @return
     */
    public static String timeFormat(Timestamp timestamp){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ss = sdf.format(timestamp);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
