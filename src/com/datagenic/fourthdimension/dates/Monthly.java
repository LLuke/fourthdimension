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

import org.joda.time.MutableDateTime;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import java.util.Iterator;
import java.util.Date;

/**
The Monthly calendar is a top level class of a number of calendars.  Calendars
such as annual, semiannual etc can all be expressed as a monthly calendar with
a varying periodicity.<p>
Subclasses of the monthly calendar will typically only need to specify a
<code>periodicity</code> to implement the frequency calendar.
<p>
Months are always calculated from an origin date.  (The origin date is current
01Jan1970). Dates are aligned to a period by first calculating the delta from
the origin, then determining the last period that occurs before that month
offset.  The formula used is:
<dl>
alignedMonthIndex = monthsFromOrigin/periodicity * periodicity + referenceMonth
<p>
monthsFromOrigin/periodicity returns an int so any remainder is discarded.
Multiplying this value by the periodicity again will realign the month to a
whole number.  Add the reference month.
<p>
If the alignedMonthIndex is greater than the original monthsFromOrigin then we
need to minus the periodicity to ensure that we have the correct period.
 */
public class Monthly extends CalendarFrequency
{
   public static int REF_JANUARY = java.util.Calendar.JANUARY;
   public static int REF_FEBRUARY = java.util.Calendar.FEBRUARY;
   public static int REF_MARCH = java.util.Calendar.MARCH;
   public static int REF_APRIL = java.util.Calendar.APRIL;
   public static int REF_MAY = java.util.Calendar.MAY;
   public static int REF_JUNE = java.util.Calendar.JUNE;
   public static int REF_JULY = java.util.Calendar.JULY;
   public static int REF_AUGUST = java.util.Calendar.AUGUST;
   public static int REF_SEPTEMBER = java.util.Calendar.SEPTEMBER;
   public static int REF_OCTOBER = java.util.Calendar.OCTOBER;
   public static int REF_NOVEMBER = java.util.Calendar.NOVEMBER;
   public static int REF_DECEMBER = java.util.Calendar.DECEMBER;
   private static final int DEFAULT_PERIODICITY = 1;
   private static final int DEFAULT_REFERENCE_MONTH = REF_JANUARY;

   /**
   Calendars can specify a different reference month to change the alignment of
   the period 'window'.  In most cases the default is JANUARY, however this can be
   overridden by calling the appropriate constructor.  A reference period of
   JANUARY in a ANNUAL calendar will result in the period window starting on every
   01JAN of each year, whereas a reference period of FEBRUARY will result in the
   period window starting on every 01FEB of each year.
    */
   protected int calendarReferenceMonth;

   /**
   The periodicity of a calendar determines the reoccurance of the period. An
   annual calendar has a periodicity of 12, a semiannual 6 and monthly 1. The
   periodicity can be specified by calling the appropriate constructor.
    */
   protected int calendarPeriodicity;

   /**
   @param periodicity
   @param referenceMonth
   @roseuid 4146B3A700AB
    */
   public Monthly(int periodicity, int referenceMonth)
   {
       super();
       this.calendarReferenceMonth = referenceMonth;
       this.calendarPeriodicity = periodicity;
   }

   /**
   @roseuid 4146B767000F
    */
   public Monthly()
   {
       this(DEFAULT_PERIODICITY,DEFAULT_REFERENCE_MONTH);
   }

   /**
   @param startIndex
   @param endIndex
   @return Iterator
   @roseuid 4147097E0232
    */
   public Iterator iterator(long startIndex, long endIndex)
   {
    return null;
   }

   /**
   @param date
   @return int
   @roseuid 4150398E00CB
    */
   private int monthsFromOrigin(Date date)
   {
       DateTime dateTime = new DateTime(date,this.getJodaTimezone());

       //equivalent to getOrigin()
       DateTime originDateTime = new DateTime(0,this.getJodaTimezone());

       int originYear = originDateTime.getYear();
       int originMonth = originDateTime.getMonthOfYear();

       int dateYear = dateTime.getYear();
       int dateMonth = dateTime.getMonthOfYear();

       int differenceYear = dateYear - originYear;
       int differenceMonth = dateMonth - originMonth;

       return differenceYear * 12 + differenceMonth;
   }

   /**
   @return org.joda.time.MutableDateTime
   @roseuid 41503B3E0251
    */
   protected MutableDateTime getOrigin()
   {
       MutableDateTime origin = new MutableDateTime(0 - this.getJodaTimezone().getOffset(0),this.getJodaTimezone());
       return origin;
   }

   /**
   @param index
   @return long
   @roseuid 41503D41033C
    */
   public long startsOnBefore(long index)
   {
       return startsOnBefore(new Date(index)).getTime();
   }

   /**
   @param index
   @return java.util.Date
   @roseuid 4157EB8E005D
    */
   public Date startsOnBefore(Date index)
   {
       int monthIndex = monthsFromOrigin(index);
       int alignedMonthIndex = monthIndex / this.calendarPeriodicity *
           this.calendarPeriodicity + this.calendarReferenceMonth;
       if (alignedMonthIndex > monthIndex)
           alignedMonthIndex = alignedMonthIndex - this.calendarPeriodicity;

       //equivalent of the origin date
//       MutableDateTime dateTime = new MutableDateTime(0 - DateTimeZone.getInstance(getTimezone()).getOffset(0),DateTimeZone.getInstance(getTimezone()));
       MutableDateTime dateTime = getOrigin();
       dateTime.addMonths(alignedMonthIndex);

       return dateTime.toDate();
   }

   /**
   @param index
   @return long
   @roseuid 41503D81033C
    */
   public long startsAfter(long index)
   {
       return startsAfter(new Date(index)).getTime();
   }

   /**
   @param index
   @return java.util.Date
   @roseuid 4157EBA80271
    */
   public Date startsAfter(Date index)
   {
       Date date = startsOnBefore(index);
       MutableDateTime dateTime = new MutableDateTime(date,this.getJodaTimezone());
       dateTime.addMonths(this.calendarPeriodicity);
       return dateTime.toDate();
   }

   /**
   @param startIndex
   @param endIndex
   @return long[]
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 41516F79036B
    */
   public long[] getIndexes(long startIndex, long endIndex) throws IndexAlignmentException
   {
       return getIndexes(new Date(startIndex),new Date(endIndex));
   }

   /**
   @param startIndex
   @param endIndex
   @return long[]
   @roseuid 4157EBE202EE
    */
   public long[] getIndexes(Date startIndex, Date endIndex)
   {
        if (!aligns(startIndex.getTime())) {
            throw new IndexAlignmentException(getDateFormatter().format(
                startIndex) + " is not an index at " +
                                              getCalendarFrequencyName() +
                                              " frequency.");
        }
        if (!aligns(endIndex.getTime())) {
            throw new IndexAlignmentException(getDateFormatter().format(
                endIndex) + " is not an index at " +
                                              getCalendarFrequencyName() +
                                              " frequency.");
        }

        MutableDateTime dateTime = new MutableDateTime(startIndex,this.getJodaTimezone());

        long indexesBetween = periodsBetween(startIndex,
                                            endIndex);
       if (indexesBetween > Integer.MAX_VALUE)
           throw new IndexRangeTooLargeException();


        //want to include the end index so add 1 to this value
        indexesBetween += 1;
        long[] indexes = new long[(int)indexesBetween];
        for (int i = 0; i < indexesBetween; i++) {
            indexes[i] = dateTime.getMillis();
            dateTime.addMonths(calendarPeriodicity);
        }
        return indexes;
   }

   /**
   @param startIndex
   @param endIndex
   @return long
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 4151716D0222
    */
   public long periodsBetween(long startIndex, long endIndex) throws IndexAlignmentException
   {

    return periodsBetween(new Date(startIndex),new Date(endIndex));
   }

   /**
   @param startIndex
   @param endIndex
   @return long
   @roseuid 4157EBC80148
    */
   public long periodsBetween(Date startIndex, Date endIndex)
   {
       if (!aligns(startIndex.getTime())) {
           throw new IndexAlignmentException(getDateFormatter().format(
               startIndex) + " is not an index at " +
                                             getCalendarFrequencyName() +
                                             " frequency.");
       }
       if (!aligns(endIndex.getTime())) {
           throw new IndexAlignmentException(getDateFormatter().format(
               endIndex) + " is not an index at " +
                                             getCalendarFrequencyName() +
                                             " frequency.");
       }
       int startOriginOffset = monthsFromOrigin(startIndex);
       int endOriginOffset = monthsFromOrigin(endIndex);
       int delta = endOriginOffset - startOriginOffset;
       return Math.abs(delta / calendarPeriodicity);
   }

   /**
   @return java.lang.String
   @roseuid 416505AB003E
    */
   public String getCalendarFrequencyName()
   {
       if (calendarReferenceMonth == Monthly.REF_JANUARY) {
           return "Monthly";
       } else {
           return "Monthly(" + DateHelper.getMonthName(calendarReferenceMonth) + ")";
       }
   }

   /**
   @param index
   @param periods
   @return long
   @roseuid 4228781D03B9
    */
   public long getPeriodFrom(long index, int periods)
   {
       MutableDateTime dateTime = new MutableDateTime(index,this.getJodaTimezone());
       dateTime.addMonths(this.calendarPeriodicity * periods);
       return dateTime.getMillis();
   }

   /**
   @return Object
   @roseuid 425E8A8D0328
    */
   public Object clone()
   {
       return new Monthly(this.calendarPeriodicity,this.calendarReferenceMonth);
   }
}
/**
Calendar Monthly.indexBefore(java.util.Calendar){
return null;
}
Calendar Monthly.indexesStartingBetween(java.util.Calendar,java.util.Calendar){
if (!aligns(startCalendar.getTimeInMillis())) {
throw new IndexAlignmentException(getDateFormatter().format(startCalendar.getTime()) + " is not an index at " + getCalendarFrequencyName() + " frequency.");
}
if (!aligns(endCalendar.getTimeInMillis())) {
throw new IndexAlignmentException(getDateFormatter().format(endCalendar.getTime()) + " is not an index at " + getCalendarFrequencyName() + " frequency.");
}
int startOriginOffset = monthsFromOrigin(startCalendar);
int endOriginOffset = monthsFromOrigin(endCalendar);
int delta = endOriginOffset - startOriginOffset;
return delta/calendarPeriodicity;
}
Calendar Monthly.indexStartsOnBefore(java.util.Calendar){
int monthIndex = monthsFromOrigin(date);
int alignedMonthIndex = monthIndex/this.calendarPeriodicity * this.calendarPeriodicity + this.calendarReferenceMonth;
if (alignedMonthIndex > monthIndex)
alignedMonthIndex = alignedMonthIndex - this.calendarPeriodicity;
Calendar cal = Calendar.getInstance(getTimezone());
cal.clear();
cal.add(Calendar.MONTH,alignedMonthIndex);
return cal;
}
Calendar Monthly.indexStartsAfter(java.util.Calendar){
Calendar cal = indexStartsOnBefore(date);
cal.add(Calendar.MONTH,this.calendarPeriodicity);
return cal;
}
Calendar Monthly.getIndexes(java.util.Calendar,java.util.Calendar){
if (!aligns(startCalendar.getTimeInMillis())) {
throw new IndexAlignmentException(getDateFormatter().format(startCalendar.getTime()) + " is not an index at " + getCalendarFrequencyName() + " frequency.");
}
if (!aligns(endCalendar.getTimeInMillis())) {
throw new IndexAlignmentException(getDateFormatter().format(endCalendar.getTime()) + " is not an index at " + getCalendarFrequencyName() + " frequency.");
}
Calendar rollingCalendar = (Calendar)startCalendar.clone();
int indexesBetween = periodsBetween(startCalendar.getTimeInMillis(),endCalendar.getTimeInMillis());
//want to include the end index so add 1 to this value
indexesBetween+=1;
long[] indexes = new long[indexesBetween];
for (int i=0;i<indexesBetween;i++) {
indexes[i] = rollingCalendar.getTimeInMillis();
rollingCalendar.add(Calendar.MONTH,calendarPeriodicity);
}
return indexes;
}
long[] Monthly.getIndexes(long,long){
return null;
}
Monthly.Monthly(int,int,boolean[]){
this.calendarReferenceMonth = referenceMonth;
this.calendarPeriodicity = periodicity;
}
 */
