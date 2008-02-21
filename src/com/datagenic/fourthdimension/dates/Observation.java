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

/**
An Observation frequency can be used where a DataPoint is recorded against a
sequential event unrelated to time.  It is most similar to an array of
DataPoint objects.
 */
public class Observation extends CalendarFrequency
{

   /**
   @param startIndex
   @param endIndex
   @return Iterator
   @roseuid 414708AA0399
    */
   public Iterator iterator(long startIndex, long endIndex)
   {
    return super.iterator(startIndex,endIndex);
   }

   /**
   @param startIndex
   @param endIndex
   @return long[]
   @roseuid 41516F9000FA
    */
   public long[] getIndexes(long startIndex, long endIndex)
   {
       long periods = periodsBetween(startIndex,endIndex);
       if (periods > Integer.MAX_VALUE)
           throw new IndexRangeTooLargeException();

       long[] tmp = new long[(int)periods + 1];
       for (int i=0;i<tmp.length;i++) {
           tmp[i] = startIndex++;
       }

       return tmp;
   }

   /**
   @param index
   @return long
   @roseuid 4157E11003C8
    */
   public long startsOnBefore(long index)
   {
    return index;
   }

   /**
   @param index
   @return long
   @roseuid 4157E111002E
    */
   public long startsAfter(long index)
   {
    return index + 1;
   }

   /**
   @param startIndex
   @param endIndex
   @return long
   @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
   @roseuid 4157E111006D
    */
   public long periodsBetween(long startIndex, long endIndex) throws IndexAlignmentException
   {
    return endIndex - startIndex;
   }

   /**
   @return java.lang.String
   @roseuid 416507E70280
    */
   public String getCalendarFrequencyName()
   {
    return "Observation";
   }

   /**
   @param index
   @param periods
   @return long
   @roseuid 42287BC702AF
    */
   public long getPeriodFrom(long index, int periods)
   {
    return index + periods;
   }

   /**
   @return Object
   @roseuid 425E8BAE02DA
    */
   public Object clone()
   {
       return new Observation();
   }
}
