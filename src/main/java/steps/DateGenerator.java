package steps;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class DateGenerator {


    public static String dateGenerator() {
        LocalDate startDate = LocalDate.of(1500, 1, 1);
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.now();
        String dateToTest;

        long end = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return dateToTest = formatter.format(randomEpochDay);
    }
}
