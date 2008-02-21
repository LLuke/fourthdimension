//Source file: C:\\Data\\DataGenic\\Projects\\FourthDimension\\src\\com\\datagenic\\fourthdimension\\dates\\SevenDay.java

package com.datagenic.fourthdimension.dates;

import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import java.text.SimpleDateFormat;
import org.joda.time.TimeOfDay;

/**
<p>Title: </p>
<p>Description: </p>
<p>Copyright: Copyright (c) 2004</p>
<p>Company: </p>
@author not attributable
@version 1.0
 */
public class SevenDay extends CalendarFrequency
{
   private static final int DEFAULT_WEEKDAY_REF = Calendar.MONDAY;
   private static final int DEFAULT_HOUR_REF = 0;
   private static final int DEFAULT_MINUTE_REF = 0;
   private static final int DEFAULT_SECOND_REF = 0;
   private int reference_weekday = DEFAULT_WEEKDAY_REF;
   private int reference_hour = DEFAULT_HOUR_REF;
   private int reference_minute = DEFAULT_MINUTE_REF;
   private int reference_second = DEFAULT_SECOND_REF;
   private static final int DEFAULT_PERIODICITY = 7;
   private int periodicity = DEFAULT_PERIODICITY;
   private DateTime calculatedReferenceTime;

   /**
   private TimeOfDay calculatedReferenceTime;
    */
   private DateTime originDateTime = null;

   /**
   @param referenceWeekday
   @param referenceHour
   @param referenceMinute
   @param referenceSecond
   @roseuid 425E8C9902CA
    */
   public SevenDay(int referenceWeekday, int referenceHour, int referenceMinute, int referenceSecond)
   {
        super();

        this.reference_weekday = referenceWeekday;
        this.reference_hour = referenceHour;
        this.reference_minute = referenceMinute;
        this.reference_second = referenceSecond;

        //This calculates the reference time as a time from the origin. It is better than using the TimeOfDay class in Joda
        MutableDateTime tmp = new MutableDateTime(getOrigin(),getJodaTimezone());
        tmp.setTime(referenceHour,referenceMinute,reference_second,0);
        calculatedReferenceTime = tmp.toDateTime();
//        calculatedReferenceTime = new TimeOfDay(reference_hour,reference_minute,reference_second,0);
   }

   /**
   @roseuid 425E8C99028C
    */
   public SevenDay()
   {
        this(DEFAULT_WEEKDAY_REF,DEFAULT_HOUR_REF,DEFAULT_MINUTE_REF,DEFAULT_SECOND_REF);
   }

   /**
   Displays the name of the Calendar and if applicable the reference
   date/time.
   @return java.lang.String
   @todo Implement this
   com.datagenic.fourthdimension.dates.CalendarFrequency method
   @roseuid 425E8C990328
    */
   public String getCalendarFrequencyName()
   {

        //Was using a simple dateformat here, but it was taken unnecessary amounts of memory. Do we have an issue with timezones?
        String hr = reference_hour < 10 ? "0" + reference_hour : reference_hour + "";
        String min = reference_minute < 10 ? "0" + reference_minute : reference_minute + "";
        String sec = reference_second < 10 ? "0" + reference_second : reference_second + "";

        return "SevenDay [" + DateHelper.getDowName(reference_weekday) + " " + hr + ":" + min + ":" + sec + "]";
   }

   /**
   Returns an array of period indexes between a start and end index
   inclusive.
   @param startIndex long
   @param endIndex long
   @return long[]
   @throws IndexAlignmentException
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @todo Implement this
   com.datagenic.fourthdimension.dates.CalendarFrequency method
   @roseuid 425E8C990329
    */
   public long[] getIndexes(long startIndex, long endIndex) throws IndexAlignmentException
   {
        return getIndexes(new java.util.Date(startIndex),new java.util.Date(endIndex));
   }

   /**
   @param startIndex
   @param endIndex
   @return long[]
   @roseuid 425E8C9903B4
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
           dateTime.addDays(periodicity);
       }
         return indexes;
   }

   /**
   @param index long
   @param periods int
   @return long
   @todo Implement this
   com.datagenic.fourthdimension.dates.CalendarFrequency method
   @roseuid 425E8C9A0088
    */
   public long getPeriodFrom(long index, int periods)
   {
        if (!aligns(index)) {
            throw new IndexAlignmentException(index +
                 " is not an index at " + getCalendarFrequencyName() +
                                              " frequency.");
         }

        MutableDateTime dateTime = new MutableDateTime(index,this.getJodaTimezone());
        dateTime.addDays(periodicity * periods);
        return dateTime.getMillis();
   }

   /**
   @param startIndex
   @param endIndex
   @return long
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 425E8C9A00E6
    */
   public long periodsBetween(Date startIndex, Date endIndex) throws IndexAlignmentException
   {
       return periodsBetween(startIndex.getTime(),endIndex.getTime());
   }

   /**
   Indicates the number of periods at the calendar frequency which lie
   between the start and end index.
   @param startIndex - The start index which is aligned to the calendar
   frequency
   @param endIndex - The end index which is aligned to the calendar
   frequency, which designates the start of the next period. Note the
   end index is not excluded in the number of indexes.
   @return long
   @throws IndexAlignmentException
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @todo Implement this
   com.datagenic.fourthdimension.dates.CalendarFrequency method
   @roseuid 425E8C9A01E0
    */
   public long periodsBetween(long startIndex, long endIndex) throws IndexAlignmentException
   {
        if (!aligns(startIndex)) {
            throw new IndexAlignmentException(
                startIndex + " is not an index at " +
                                              getCalendarFrequencyName() +
                                              " frequency.");
        }
        if (!aligns(endIndex)) {
            throw new IndexAlignmentException(
                endIndex + " is not an index at " +
                                              getCalendarFrequencyName() +
                                              " frequency.");
        }
        int startOriginOffset = daysFromOrigin(startIndex);
        int endOriginOffset = daysFromOrigin(endIndex);
        int delta = endOriginOffset - startOriginOffset;
        return Math.abs(delta / periodicity);
   }

   /**
   Provides an index which starts after the specified index.
   @param index long
   @return long
   @todo Implement this
   com.datagenic.fourthdimension.dates.CalendarFrequency method
   @roseuid 425E8C9A027C
    */
   public long startsAfter(long index)
   {

        long indexStartsOnBefore = startsOnBefore(index);
        MutableDateTime dateTime = new MutableDateTime(indexStartsOnBefore,this.getJodaTimezone());
        dateTime.addDays(periodicity);
        return dateTime.getMillis();
   }

   /**
   Provides an index which starts on or before the specified index.
   @param index - The index period
   @return long
   @todo Implement this
   com.datagenic.fourthdimension.dates.CalendarFrequency method
   @roseuid 425E8C9A02AC
    */
   public long startsOnBefore(long index)
   {

        DateTime indexDate = new DateTime(index,this.getJodaTimezone());

        int daysFromOrigin = daysFromOrigin(index);

        int alignedDay = (daysFromOrigin / periodicity) * periodicity - (Calendar.THURSDAY - reference_weekday);

        //get the origin
        MutableDateTime dt = new MutableDateTime(getOrigin());
        dt.addDays(alignedDay);

        if (alignedDay > daysFromOrigin)  //this occurs when the dow is greater than the origin, and < ref
            dt.addDays(-periodicity);

        if (reference_weekday < Calendar.THURSDAY) {
            if (DateHelper.javaDayOfWeek(indexDate.getDayOfWeek()) >= reference_weekday) {
                dt.addDays(periodicity);
            }
        }

        if (dt.getMillis() > index)
            dt.addDays(-periodicity);

        //align the time part. If the timepart is less than the reference time then subtract the periodicity
//        TimeOfDay indexTime = indexDate.toTimeOfDay();

        MutableDateTime indexTime = new MutableDateTime(getOrigin());
        indexTime.setTime(indexDate.getHourOfDay(),indexDate.getMinuteOfHour(),indexDate.getSecondOfMinute(),0);

        if (indexTime.isBefore(calculatedReferenceTime)) {
            dt.addDays(-periodicity);
        }

        //Everything should be aligned to that of the reference times
        dt.setHourOfDay(reference_hour);
        dt.setMinuteOfHour(reference_minute);
        dt.setSecondOfMinute(reference_second);
        dt.setMillisOfSecond(0);

        return dt.getMillis();
   }

   /**
   Calculates the number of days from the origin point
   @param index long
   @return int
   @roseuid 425E8C9A02DA
    */
   private int daysFromOrigin(long index)
   {

        int daysDifference = getOrigin().getChronology().days().getDifference(getOrigin().getMillis(),index);

        return -daysDifference;
   }

   /**
   Creates an origin DateTime that is used as a reference point in calculations.
   @return DateTime
   @roseuid 425E8C9A0309
    */
   private DateTime getOrigin()
   {
        if (this.originDateTime == null) {
            this.originDateTime = new DateTime(0,this.getJodaTimezone());
        }
        return this.originDateTime;
   }

   /**
   @param args
   @roseuid 425E8C9A0318
    */
   public static void main(String[] args)
   {
        SevenDay day = new SevenDay(Calendar.MONDAY,2,0,0);
        day.setTimezone(TimeZone.getTimeZone("GMT"));

        SimpleDateFormat sdf = new SimpleDateFormat("E dd MMM yyyy HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Calendar c = new GregorianCalendar(2005,Calendar.JANUARY,2,2,0,0);
        c.setTimeZone(TimeZone.getTimeZone("GMT"));

        for (int i=0;i<100;i++) {

            long indexStartsBefore = day.startsOnBefore(c.getTimeInMillis());
            long indexStartsAfter = day.startsAfter(c.getTimeInMillis());

            System.out.println(sdf.format(c.getTime()) + ": Before:" + sdf.format(new java.util.Date(indexStartsBefore)) + " :After:" + sdf.format(new java.util.Date(indexStartsAfter)));
//            System.out.println(c.getTime().toGMTString() + ":" + new java.util.Date(index).toGMTString());

            c.add(Calendar.DAY_OF_YEAR,1);

        }
System.exit(-1);

        Calendar start = new GregorianCalendar(1970,Calendar.JANUARY,1,0,0,0);
        Calendar end= new GregorianCalendar(1971,Calendar.JANUARY,1,0,0,0);

        long startIndex = day.startsOnBefore(start.getTimeInMillis());
        long endIndex = day.startsOnBefore(end.getTimeInMillis());

        System.out.println("Start Index:" + sdf.format(new java.util.Date(startIndex)));
        System.out.println("End Index:" + sdf.format(new java.util.Date(endIndex)));

        long[] indexes = day.getIndexes(day.startsOnBefore(start.getTimeInMillis()),day.startsOnBefore(end.getTimeInMillis()));
        for (int i=0;i<indexes.length;i++) {
            System.out.println(sdf.format(new java.util.Date(indexes[i])));
        }

        long periods = day.periodsBetween(day.startsOnBefore(start.getTimeInMillis()),day.startsOnBefore(end.getTimeInMillis()));
        System.out.println(periods);

        long period = day.getPeriodFrom(day.startsOnBefore(start.getTimeInMillis()),1);
        System.out.println(sdf.format(new java.util.Date(period)));
   }

   /**
   @return Object
   @roseuid 425E8CAB0098
    */
   public Object clone()
   {
       return new SevenDay(this.reference_weekday,this.reference_hour,this.reference_minute,this.reference_second);
   }
}
/*
long SevenDay.periodsBetween(java.util.Date,java.util.Date){
        return periodsBetween(startIndex.getTime(),endIndex.getTime());
    }
 */
/*
long SevenDay.getIndexes(java.util.Date,java.util.Date){
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
             dateTime.addDays(periodicity);
         }
         return indexes;
   }
 */
