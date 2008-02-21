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
package com.datagenic.fourthdimension;

import com.datagenic.fourthdimension.dates.CalendarFrequency;

/**
 * A NumericSeries is a base class to all numeric series implementations.  Consider using one of the following series:<p>
 * 
 * FloatSeries<br>
 * LongSeries<br>
 * IntSeries<br>
 * ShortSeries<br>
 * ByteSeries<br>
  * @see Series
 */

public abstract class NumericSeries extends Series 
{
   
    /**
     * Constructs a Series with a name and calendar. The series is intially empty.  To
     *  populate the contents of the series either create a DataPointCollection and call
     *  <code>Series.setDataPointCollection()</code> or insert individual DataPoints by
     *  using the <code>Series.setDataPoint()</code> methods.
     *  @param name - The name of the series
     *  @param calendar - The calendar at which DataPoints must align to
     */
   public NumericSeries(String name, CalendarFrequency calendar) 
   {
       super(name,calendar);    
   }
}
