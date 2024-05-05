package steps;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateGenerator {
    public static String dateGenerator() {
        Date startDate= new Date(1500);
        Date endDate= new Date();
        long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(random);
    }
}
