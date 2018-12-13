package xyz.hui_yi.keywords.utils.commons;

import java.sql.Timestamp;
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
}
