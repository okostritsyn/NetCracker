package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.io.IOException;
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
        boolean convertError = !stringDate.matches(currFormat);
        LocalDate resultDay = LocalDate.MIN;
        do {
            if (convertError) {
                System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
                try {
                    stringDate = view.readInputString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                convertError = !stringDate.matches(currFormat);
                if (convertError) continue;
            }
            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
                resultDay = convertToLocalDateViaInstant(date);
            } catch (ParseException e) {
                convertError = true;
            }
        }while(convertError);

        return resultDay;
    }

    public static LocalDateTime parseDateTimeFromString(View view,String stringDate){
        String currFormat = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";
        boolean convertError = !stringDate.matches(currFormat);
        LocalDateTime resultDay = LocalDateTime.MIN;
        do {
            if (convertError) {
                System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
                try {
                    stringDate = view.readInputString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                convertError = !stringDate.matches(currFormat);
                if (convertError) continue;
            }
            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(stringDate);
                resultDay = convertToLocalDateTimeViaInstant(date);
            } catch (ParseException e) {
                System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
                convertError = true;
            }
        }while(convertError);

       return resultDay;
    }
}
