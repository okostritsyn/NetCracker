package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CollectDataUtil {
    private static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate parseDateFromString(View view, String stringDate){
        String currFormat = "\\d{2}-\\d{2}-\\d{4}";
        if(!stringDate.matches(currFormat)){
            System.out.println("incorrect date! Insert date in format dd-mm-yyyy");
            return parseDateFromString(view,view.readInputString());
        }
        try {
            Date date=new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
            return convertToLocalDateViaInstant(date);
        } catch (ParseException e) {
            System.out.println("incorrect date! Insert date in format dd-mm-yyyy");
            return parseDateFromString(view,view.readInputString());
        }
    }

    public static LocalDateTime parseDateTimeFromString(View view,String stringDate){
        String currFormat = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";
        if(!stringDate.matches(currFormat)){
            System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
            return parseDateTimeFromString(view,view.readInputString());
        }
        try {
            Date date=new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(stringDate);
            return convertToLocalDateTimeViaInstant(date);
        } catch (ParseException e) {
            System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
            return parseDateTimeFromString(view,view.readInputString());
        }
    }
}
