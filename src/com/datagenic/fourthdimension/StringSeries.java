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

import com.datagenic.fourthdimension.adapters.StringListAdapter;
import org.apache.commons.collections.primitives.*;
import com.datagenic.fourthdimension.dates.CalendarFrequency;
import com.datagenic.fourthdimension.data.DataPoint;

/**
 * A StringSeries is used to store String data.  If you wish to store other datatypes consider using one of the
 * following series:<p>
 * 
 * FloatSeries<br>
 * LongSeries<br>
 * IntSeries<br>
 * ShortSeries<br>
 * ByteSeries<br>
 * BooleanSeries<br>
 * StringSeries<br>
 * @see Series
 */
public class StringSeries extends Series
{

    /**
     * Constructs a Series with a name and calendar. The series is intially empty.  To
     *  populate the contents of the series either create a DataPointCollection and call
     *  <code>Series.setDataPointCollection()</code> or insert individual DataPoints by
     *  using the <code>Series.setDataPoint()</code> methods.
     *  @param name - The name of the series
     *  @param calendar - The calendar at which DataPoints must align to
     */
   public StringSeries(String name, CalendarFrequency calendar)
   {
       super(name,calendar);
   }

    /**
     * Produces an empty ListAdapter which is suitable as a storage vessel for this series.
     * @return com.datagenic.fourthdimension.ListAdapter The correct storage structure for this series
     */
   public ListAdapter makeListAdapter()
   {
       return new StringListAdapter();
   }

    /**
     * This delegated datapoint implementation.  It is responsible for extracting the correct datatype from the internal storage structure
     * @param position inidcates the element to return
     * @return com.datagenic.fourthdimension.data.DataPoint the DataPoint
     */
   protected DataPoint getDataPointImpl(int position)
   {
       return new DataPoint(indexList.get(position),listAdapter.getString(position),statusList.get(position));
   }

    /**
     * Produces a shallow copy of this series
     * @return Object which is a copy of this series
     */
   public Object clone()
   {
       StringSeries series = new StringSeries("Copy of " + this.getName(),this.getCalendar());
       series.firstIndex = this.firstIndex;
       series.lastIndex = this.lastIndex;
       series.editStatusList = new ArrayByteList((ByteList)editStatusList);
       series.indexList = new ArrayLongList((LongList)indexList);
       series.listAdapter = ListAdapter.makeAdapter(listAdapter.toStringArray());
       series.statusList = new ArrayByteList((ByteList)statusList);
       return series;
   }
}
