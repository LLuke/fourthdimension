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

import java.util.Iterator;
import java.util.Date;

public class Weekly extends CalendarFrequency 
{
   public int MONDAY;
   public int TUESDAY;
   private static int PERIODS = 1;
   
   /**
   @roseuid 414708AD035B
    */
   public Weekly() 
   {
    
   }
   
   /**
   @param periods
   @param referenceDate
   @param pattern
   @roseuid 4145A04E02FD
    */
   public Weekly(int periods, int referenceDate, boolean[] pattern) 
   {
    
   }
   
   /**
   @param startIndex
   @param endIndex
   @return Iterator
   @roseuid 414708AD038A
    */
   public Iterator iterator(long startIndex, long endIndex) 
   {
    return null;
   }
   
   /**
   @param calendar
   @return java.util.Date
   @roseuid 4151526503B9
    */
   public Date indexBefore(Date calendar) 
   {
    return null;
   }
   
   /**
   @param calendar
   @return java.util.Date
   @roseuid 41515266008C
    */
   public Date indexAfter(Date calendar) 
   {
    return null;
   }
   
   /**
   @param startIndex
   @param endIndex
   @return long[]
   @roseuid 415170AE00FA
    */
   public long[] getIndexes(long startIndex, long endIndex) 
   {
    return null;
   }
   
   /**
   @param startCalendar
   @param endCalendar
   @return long[]
   @roseuid 415170AE029F
    */
   public long[] getIndexes(Date startCalendar, Date endCalendar) 
   {
    return null;
   }
   
   /**
   @param calendar
   @return java.util.Date
   @roseuid 41519B74007D
    */
   public Date indexStartsOnBefore(Date calendar) 
   {
    return null;
   }
   
   /**
   @param calendar
   @return java.util.Date
   @roseuid 41519B740203
    */
   public Date indexStartsAfter(Date calendar) 
   {
    return null;
   }
   
   /**
   @param startCalendar
   @param endCalendar
   @return int
   @roseuid 415359EE0203
    */
   public int indexesStartingBetween(Date startCalendar, Date endCalendar) 
   {
    return 0;
   }
   
   /**
   @param index
   @return long
   @roseuid 4157E186007D
    */
   public long startsOnBefore(long index) 
   {
    return 0;
   }
   
   /**
   @param index
   @return long
   @roseuid 4157E18600DA
    */
   public long startsAfter(long index) 
   {
    return 0;
   }
   
   /**
   @param startIndex
   @param endIndex
   @return long
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 4157E1860148
    */
   public long periodsBetween(long startIndex, long endIndex) throws IndexAlignmentException 
   {
    return 0;
   }
   
   /**
   @return java.lang.String
   @roseuid 4165095C02EE
    */
   public String getCalendarFrequencyName() 
   {
    return null;
   }
   
   /**
   @param index
   @param periods
   @return long
   @roseuid 42287B000271
    */
   public long getPeriodFrom(long index, int periods) 
   {
    return 0;
   }
   
   /**
   @return Object
   @roseuid 425E8C0E0124
    */
   public Object clone() 
   {
    return null;
   }
}
