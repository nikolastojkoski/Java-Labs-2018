package sample;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Arrays;

public class Lab1 {

    public static String calculateAge(String day_, String month_, String year_){

        int day = 0, year = 0;
        try{
            day = Integer.parseInt(day_);
            year = Integer.parseInt(year_);
        }catch(NumberFormatException e) {
            e.printStackTrace();
            return "Invalid input";
        }

        String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

        int monthInt = Arrays.asList(months).indexOf(month_.toLowerCase().substring(0, 3)) + 1;

        if(day > Month.of(monthInt).length(Year.isLeap(year)) || day < 1){
            return "Invalid day";
        }

        LocalDate birthdate = LocalDate.of(year, monthInt, day);
        LocalDate now = LocalDate.now();

        if(birthdate.isAfter(now)) {
            return "Invalid date";
        }

        Period period = Period.between(birthdate, LocalDate.now());

        return (period.getYears() + " years, " + period.getMonths() + " months, " +
                period.getDays() + " days.");
    }
}

