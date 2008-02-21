package com.datagenic.fourthdimension.dates;

import java.util.*;
import org.joda.time.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestWeeks {
    public TestWeeks() {
    }

    public static void main(String[] args) {
        TestWeeks testweeks = new TestWeeks();
TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
       GregorianCalendar cal = new GregorianCalendar(1970,0,1,0,0,0);
        MutableDateTime dt = new MutableDateTime(1970,1,1,0,0,0,0);
        MutableDateTime dt1 = new MutableDateTime(1971,1,2,0,0,0,0);


        int days = dt.getChronology().days().getDifference(dt1.getMillis(),dt.getMillis());


dt.addDays(8000);

       System.out.println(" Adding time to origin Joda:" + dt.toString() + ": week year:" + dt.getWeekyear() + ": weekyearyear:" + dt.getWeekOfWeekyear());



        for (int i=0;i<1000;i++) {


            System.out.println("Joda:" + dt.toString() + ": week year:" + dt.getWeekyear() + ": weekyearyear:" + dt.getWeekOfWeekyear());
            System.out.println("Calendar: " + cal.getTime().toString() + ": week year:" + cal.get(Calendar.WEEK_OF_YEAR));
            dt.addDays(1);
            cal.add(Calendar.DAY_OF_YEAR,1);
        }

    }
}
