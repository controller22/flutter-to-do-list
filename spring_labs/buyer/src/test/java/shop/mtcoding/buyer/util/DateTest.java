package shop.mtcoding.buyer.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.format.datetime.DateFormatter;

public class DateTest {

    @Test
    public void parse_test() {
        // given
        LocalDateTime current = LocalDateTime.now();
        System.out.println(current);

        Timestamp stamp = Timestamp.valueOf(current);
        System.out.println(stamp);

        LocalDateTime nowTime = stamp.toLocalDateTime();
        String nowStr = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(nowStr);
    }
}
