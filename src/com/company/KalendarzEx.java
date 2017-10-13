package com.company;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;
import static java.time.DayOfWeek.MONDAY;
import static java.time.format.TextStyle.FULL_STANDALONE;
import static java.time.format.TextStyle.SHORT_STANDALONE;

public class KalendarzEx {
//testing testing testing
    public static void main(String[] args) {
        String calendar = getCalendar();
        System.out.println(calendar);
    }
//cokolwiek innego
    private static String getCalendar() {
        return getCalendar(YearMonth.now());
    }

    private static String getCalendar(final YearMonth yearMonth) {
        StringBuilder sb = new StringBuilder(240);

        //drukowanie nazwy miesiąca i roku
        sb.append(" ");
        sb.append(yearMonth.getMonth().getDisplayName(FULL_STANDALONE, Locale.getDefault()));
        sb.append(" ");
        sb.append(yearMonth.getYear());
        sb.append(format("%n"));

        //drukowanie nazw dni tygodnia
        Arrays.stream(DayOfWeek.values())
                .map(dayOfWeek -> dayOfWeek.getDisplayName(SHORT_STANDALONE, Locale.getDefault()))
                .map(s -> format("%3s ", s))
                .forEach(sb::append);
        sb.append(format("%n"));

        LocalDate date = LocalDate.of(yearMonth.getYear(),
                yearMonth.getMonth(),
                1);

        //drukowanie spacji do ostatniego poniedziałku poprzedniego miesiąca
        //for (int i = date.getDayOfWeek().getValue(); i > MONDAY.getValue(); i--) {
        for (int i = MONDAY.getValue(); i < date.getDayOfWeek().getValue(); i++) {
            sb.append("    ");
        }

        while (date.getMonth().equals(yearMonth.getMonth())) {
            sb.append(format("%3d", date.getDayOfMonth()));

            if (date.equals(LocalDate.now())) {
                sb.append("*");
            } else {
                sb.append(" ");
            }

            date = date.plusDays(1);

            if (date.getDayOfWeek().equals(MONDAY)) {
                sb.append(format("%n"));
            }
        }

        return sb.toString();
    }
}