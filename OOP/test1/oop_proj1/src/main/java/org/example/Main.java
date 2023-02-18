package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String strDate = "Sat, April 4, 2020";
        System.out.println(Locale.getDefault(Locale.Category.FORMAT));
        Calendar c = new GregorianCalendar(2000,Calendar.JANUARY,1);

        int maxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        StringBuilder builder = new StringBuilder("ПН ВТ СР ЧТ ПТ СБ НД");
        builder.append("\n");
        int dayPosition = c.get(Calendar.DAY_OF_WEEK);

        int column = 1;

        for (int j = 1; j <= 7; j++) {
            if(dayPosition!=j){
                builder.append("  ");
                column++;
                continue;
            }
            builder.append("   ");
            break;
        }

        column = column-2;
        for (int i = 1; i<=maxDays;i++) {
            if(column%7==0){
                column=0;
                builder.append("\n");
            }
            builder.append(String.format("%02d ", i));
            column++;
        }
        System.out.println(builder.toString());


        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMMM d, yyyy", Locale.ENGLISH);

        try{
            Date date  = formatter.parse(strDate);
            System.out.println(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}