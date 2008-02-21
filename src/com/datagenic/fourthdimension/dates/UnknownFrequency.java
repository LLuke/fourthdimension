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


public class UnknownFrequency extends CalendarFrequency
{

   /**
   @roseuid 41825FDA0060
    */
   public UnknownFrequency()
   {
    super();
   }

   /**
   @param index
   @return long
   @roseuid 41825FDA01C7
    */
   public long startsOnBefore(long index)
   {
    return index;
   }

   /**
   @param index
   @return long
   @roseuid 41825FDA038C
    */
   public long startsAfter(long index)
   {
    return index;
   }

   /**
   @param startIndex
   @param endIndex
   @return long[]
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 41825FDB012B
    */
   public long[] getIndexes(long startIndex, long endIndex) throws IndexAlignmentException
   {
    return new long[0];
   }

   /**
   @param startIndex
   @param endIndex
   @return long
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 41825FDC0050
    */
   public long periodsBetween(long startIndex, long endIndex) throws IndexAlignmentException
   {
    return 0;
   }

   /**
   @return java.lang.String
   @roseuid 41825FDC0292
    */
   public String getCalendarFrequencyName()
   {
    return "Unknown";
   }

   /**
   @param index
   @param periods
   @return long
   @roseuid 42287BC00399
    */
   public long getPeriodFrom(long index, int periods)
   {
    return 0;
   }

   /**
   @return Object
   @roseuid 425E8BFA0357
    */
   public Object clone()
   {
       return new UnknownFrequency();
   }
}
