/* ====================================================================================
 * FourthDimension : a time and numerical sequential library for the Java(tm) platform
 * ====================================================================================
 *
 * (C) Copyright 2000-2005, by DataGenic Limited and Contributors.
 *
 * Project Info:  http://www.datagenic.co.uk/fourthdimension/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 */
package com.datagenic.fourthdimension.dates;

import java.util.*;

public class DateHelper {

    /**
        @param javaCalendarMonth
        @return java.lang.String
        @roseuid 4151E89001F4
     */
    public static String getMonthName(int javaCalendarMonth) {
        switch (javaCalendarMonth) {
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "JUly";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
            default:
                return "Unknown month";

        }
    }

    public static int javaDayOfWeek(int jodaDayOfWeek) {
        switch (jodaDayOfWeek) {
            case 1: //monday
                return Calendar.MONDAY;
            case 2: //tuesday
                return Calendar.TUESDAY;
            case 3: //wednesday
                return Calendar.WEDNESDAY;
            case 4: //thursday
                return Calendar.THURSDAY;
            case 5: //friday
                return Calendar.FRIDAY;
            case 6: //saturday
                return Calendar.SATURDAY;
            case 7: //sunday
                return Calendar.SUNDAY;
            default:
                throw new RuntimeException("Unknown JODA Day of Week");
        }
    }

    public static String getDowName(int javaDow) {
        switch (javaDow) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
            default:
                throw new IllegalArgumentException("Invalid day of week.");

        }
    }
}
