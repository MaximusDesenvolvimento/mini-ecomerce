package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Component
public class DataUtil {

    public static String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }

    public static LocalDate formatDatabaseStyleToLocalDateTime(String databaseStyle){
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM-yyyy");
        return LocalDate.parse(databaseStyle,parser);
    }

    public static YearMonth formatRequestMonthStyleToLocalDateTime(String requestMonth){
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM-yyyy");
        return YearMonth.parse(requestMonth,parser);
    }

}
