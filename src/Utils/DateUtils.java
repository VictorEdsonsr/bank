package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatAndParseLocalDateToString(LocalDate date){
        return date.format(FORMATTER);
    }

    public static LocalDate formatAndParseStringToLocalDate(String date){
        return LocalDate.parse(date, FORMATTER);
    }

}
