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

import java.util.Date;

public abstract class Intraday extends CalendarFrequency 
{
   protected long calendarPeriodicity;
   protected int frequency;
   
   /**
   @roseuid 4151523E00AB
    */
   public Intraday() 
   {
    
   }
   
   /**
   @param startIndex
   @param endIndex
   @return long[]
   @roseuid 4151705400BB
    */
   public long[] getIndexes(long startIndex, long endIndex) 
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

       long rollingCalendar = startIndex;
       long indexesBetween = periodsBetween(startIndex,
                                           endIndex);
       if (indexesBetween > Integer.MAX_VALUE)
           throw new IndexRangeTooLargeException();


       //want to include the end index so add 1 to this value
       indexesBetween += 1;
       long[] indexes = new long[(int)indexesBetween];
       for (int i = 0; i < indexesBetween; i++) {
           indexes[i] = rollingCalendar;
           rollingCalendar+=calendarPeriodicity;

       }
       return indexes;    
   }
   
   /**
   @param startIndex
   @param endIndex
   @return long[]
   @roseuid 41810FB502DE
    */
   public long[] getIndexes(Date startIndex, Date endIndex) 
   {
    return getIndexes(startIndex.getTime(),endIndex.getTime());    
   }
   
   /**
   @param index
   @return long
   @roseuid 4151AA6201C5
    */
   public long startsAfter(long index) 
   {
       long alignedIndex = startsOnBefore(index);
       alignedIndex = alignedIndex + this.calendarPeriodicity;
       return alignedIndex;    
   }
   
   /**
   @param index
   @return java.util.Date
   @roseuid 41810DFF00CB
    */
   public Date startsAfter(Date index) 
   {
       long time = startsAfter(index.getTime());
       return new Date(time);    
   }
   
   /**
   @param index
   @return long
   @roseuid 4151AA7303D8
    */
   public long startsOnBefore(long index) 
   {
       long milliseconds = millisecondsFromOrigin(index);
       long alignedMillisecondIndex = milliseconds / this.calendarPeriodicity *
           this.calendarPeriodicity;
       if (alignedMillisecondIndex > milliseconds)
           alignedMillisecondIndex = alignedMillisecondIndex - this.calendarPeriodicity;

       return alignedMillisecondIndex;
//       return getIntradayOrigin() + alignedMillisecondIndex;
//       return getOrigin().getTime() + alignedMillisecondIndex;    
   }
   
   /**
   @param index
   @return java.util.Date
   @roseuid 41810E2103B9
    */
   public Date startsOnBefore(Date index) 
   {
       long time = startsOnBefore(index.getTime());
       return new Date(time);    
   }
   
   /**
   @param startIndex
   @param endIndex
   @return long
   @roseuid 415358F00242
    */
   public long periodsBetween(long startIndex, long endIndex) 
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


       long startOriginOffset = millisecondsFromOrigin(startIndex);
       long endOriginOffset = millisecondsFromOrigin(endIndex);
       long delta = endOriginOffset - startOriginOffset;

       if (delta / this.calendarPeriodicity > Integer.MAX_VALUE) {
        throw new IndexRangeTooLargeException();
       } else {
           return (int)(delta / this.calendarPeriodicity);
       }    
   }
   
   /**
   @param startIndex
   @param endIndex
   @return long
   @roseuid 41810F5402CE
    */
   public long periodsBetween(Date startIndex, Date endIndex) 
   {
    return periodsBetween(startIndex.getTime(),endIndex.getTime());    
   }
   
   /**
   @return java.lang.String
   @roseuid 416508130177
    */
   public String getCalendarFrequencyName() 
   {
    return "Intraday";    
   }
   
   /**
   @return java.util.Date
   @roseuid 41810FD601D4
    */
   protected Date getOrigin() 
   {
//       Calendar origin = Calendar.getInstance(getTimezone());
//       origin.clear();
//       return origin;
       return new Date(0);    
   }
   
   /**
   @param index
   @return long
   @roseuid 41810FFB0177
    */
   public long millisecondsFromOrigin(Date index) 
   {
    return millisecondsFromOrigin(index.getTime());    
   }
   
   /**
   @param index
   @return long
   @roseuid 418110120261
    */
   public long millisecondsFromOrigin(long index) 
   {
       //As we are dealing in milliseconds, the orgin is 0, therefore simply return the index
       return index;
//       return index - getOrigin().getTime();    
   }
   
   /**
   @param index
   @param periods
   @return long
   @roseuid 42287A3B0109
    */
   public long getPeriodFrom(long index, int periods) 
   {
       if (!aligns(index)) {
           throw new IndexAlignmentException(
               index + " is not an index at " +
                                             getCalendarFrequencyName() +
                                             " frequency.");
       }


       return index + (this.calendarPeriodicity * periods);    
   }
   
   /**
   @return Object
   @roseuid 425E8B550328
    */
   public Object clone() 
   {
    return null;
   }
}
/**



long Intraday.getIntradayOrigin(){
//       Calendar origin = Calendar.getInstance(getTimezone());
//       origin.clear();
//       return origin;
       return 0;
   }













long Intraday.millisecondsFromOrigin(java.util.Calendar){
       return millisecondsFromOrigin(cal.getTimeInMillis());
   }









long[] Intraday.getIndexes(java.util.Calendar,java.util.Calendar){
    return null;
   }



















 
 
 */
