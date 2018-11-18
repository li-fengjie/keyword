package xyz.hui_yi.keywords.utils.commons;

import java.util.UUID;

public class CommonsUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","").trim();
    }
}
