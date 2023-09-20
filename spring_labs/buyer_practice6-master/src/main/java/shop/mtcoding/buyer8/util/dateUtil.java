package shop.mtcoding.buyer8.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateUtil {

    public static String format(Timestamp stamp) {
        LocalDateTime localDateTime = stamp.toLocalDateTime();
        String res = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return res;
    }
}
