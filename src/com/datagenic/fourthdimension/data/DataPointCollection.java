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
package com.datagenic.fourthdimension.data;

import org.apache.commons.collections.primitives.*;
import com.datagenic.fourthdimension.*;
import com.datagenic.fourthdimension.adapters.BooleanListAdapter;
import com.datagenic.fourthdimension.adapters.ByteListAdapter;
import com.datagenic.fourthdimension.adapters.DoubleListAdapter;
import com.datagenic.fourthdimension.adapters.FloatListAdapter;
import com.datagenic.fourthdimension.adapters.IntListAdapter;
import com.datagenic.fourthdimension.adapters.LongListAdapter;
import com.datagenic.fourthdimension.adapters.ShortListAdapter;
import com.datagenic.fourthdimension.adapters.StringListAdapter;
import java.util.AbstractList;
import java.io.Serializable;
import com.datagenic.fourthdimension.dates.CalendarFrequency;

/**
A <code>DataPointCollection</code> is a wrapper class which encapsulates access
to the underlying storage of data.  This collection object is not modifiable.
The appropriate method on the <code>Series</code> must be used to update a
<code>DataPoint</code>.
<p>
Not all methods of the <code>AbstractList</code> are implemented.  Therefore
some methods may throw an <code>UnsupportedOperationException</code>.
<p>
The DataCollection allows the retrieval of DataPoints which are either inside
or outside of the min and max index ranges.  DataPoints are created on demand
rather than being all created up front. Each request to a get method will
result in a new DataPoint object being created.
<p>
The startOffSet and endOffSet indicate the boundary of the collection.  Using
offsets a subset of the actual data can be returned.
<p>For example using the series 1  2  3  4  5  6  7  8  9  10, a startOffSet of
3 and endOffSet of -1 produces a collection which when iterated returns
4,5,6,7,8.
 */
public class DataPointCollection extends AbstractList implements Serializable
{
   private static final long serialVersionUID = 1;

   /**
   The calendar of this collection
    */
   private CalendarFrequency calendar;

   /**
   The min index value of this collection.
    */
   private long minIndex;

   /**
   The max index value of this collection.
    */
   private long maxIndex;


   /**
   The ListAdapter used to store the data. When examining this list, also check the
   status list array to verify that a value is indeed valid.
   For example, when dealing with an ArrayIntList you cannot use a null value to
   indicate a missing value. The default therefore must be 0.  For this reason it
   is important to check the status list to verify that the 0 is in fact valid
   value rather than a missing value.
    */
   private ListAdapter valueList;

   /**
   The status list array of the collection.  Each entry indicates the status of the
   data value
    */
   private ByteList statusList;

   /**
   The startoff set is used to determine how far the minIndex value is from the
   start of the ListAdapter.  A negative value indicates that the minIndex is less
   than the beginning of the ListAdapter, whereas a positive value indicates that
   the minIndex is greater than the beginning of the ListAdapter.
    */
   private int startOffset;

   /**
   The endoffset is used to determine how far the maxIndex value is from the end of
   the ListAdapter.  A negative value indicates that the maxIndex is less than the
   end of the ListAdapter, whereas a positive value indicates that the maxIndex is
   greater than the end of the ListAdapter.
    */
   private int endOffset;

   /**
   @param calendar
   @param indexes
   @param values
   @param statuses
   @param startOffset
   @param endOffset
   @param minIndex
   @param maxIndex
   @roseuid 422850B5029F
    */
   public DataPointCollection(CalendarFrequency calendar, LongList indexes, ListAdapter values, ByteList statuses, int startOffset, int endOffset, long minIndex, long maxIndex)
   {
       this.calendar = calendar;
       this.valueList = values;
       this.statusList = statuses;

       this.startOffset = startOffset;
       this.endOffset = endOffset;

       this.minIndex = minIndex;
       this.maxIndex = maxIndex;
   }

   /**
   Creates a DataPointCollection using the specified indexes, data and statuses.
   The indexes must all be aligned to <code>calendar</code>.
   @param calendar - The calendar frequency of the stored data
   @param indexes - An array containing a list of index points. All three parameter
   arrays must be the same size
   @param values - An array containing series data.  All three parameter arrays
   must be the same size
   @param statuses - An array containing statuses of the series data.  All three
   parameter arrays must be the same size
   @roseuid 4146C3A90186
    */
   public DataPointCollection(CalendarFrequency calendar, long[] indexes, ListAdapter values, byte[] statuses)
   {
       int size = indexes.length;
       if (values.size() != size)
           throw new IllegalArgumentException("The number of values doesn't match the number of indexes.");

       if (statuses.length != size)
           throw new IllegalArgumentException("The number of status values doesn't match the number of indexes.");

       this.calendar = calendar;


       this.valueList = values;       
       this.statusList = new ArrayByteList(statuses);

       //set the min and max index based in the indexes passed
       this.minIndex = indexes[0];
       this.maxIndex = indexes[indexes.length - 1];

       //as the indexes and values lengths match, we can set the offsets to 0
       this.startOffset = 0;
       this.endOffset = 0;
   }

   /**
   Returns the minimum index point for this collection.
   @return long
   @roseuid 4146C582009C
    */
   public long getMinIndex()
   {
    return minIndex;
   }

   /**
   Returns the maximum index point for this collection.
   @return long
   @roseuid 4146C5880280
    */
   public long getMaxIndex()
   {
    return maxIndex;
   }

   /**
   Returns the size of this collection.
   @return int
   @roseuid 4146C59501B5
    */
   public int size()
   {
       return valueList.size() + endOffset - startOffset;
   }

   /**
   Gets the index value at the specified position.  The index value can be
   translated into a Java Date if the series represents a time dimension.
   @param position - The position between 0 and DataPointCollection.size() - 1
   @return long
   @roseuid 4146C5A500BB
    */
   public long getIndex(int position)
   {
       if (position < 0 || position >= this.size())
           throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));

       int relativePosition = startOffset + position;

       /**
        * When calculating the index for the data point, the position offset is relative to the
        * data point collection minIndex. For this reason the value of the position variable is used rather than the
        * value of the relativePosition.
        */
       long index = this.calendar.getPeriodFrom(minIndex,position);

       return index;
   }

   /**
   Gets the data value at the specified position.  Note this returns the underlying
   object. It may need to be cast into an appropriate object.  The value return may
   also be null.  Ensure to check the status of the value to ensure that the data
   value is accurate.
   @param position - The position between 0 and DataPointCollection.size() - 1
   @return Object
   @roseuid 4146C5BD005D
    */
   public Object getData(int position)
   {
       if (position < 0 || position >= this.size())
           throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));

       int relativePosition = startOffset + position;

       if (relativePosition < 0) {
           return null;
       }
       else if (relativePosition > valueList.size()) {
           return null;
       }
       else {
           return valueList.getObject(relativePosition);
       }
   }

   /**
   Gets the status value at the specified position.  Use the DataPointStatus class
   to help translate this value into a readable form.
   @param position - The position between 0 and DataPointCollection.size() - 1
   @return byte
   @roseuid 4146C5E1005D
    */
   public byte getStatus(int position)
   {

       if (position < 0 || position >= this.size())
           throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));

       int relativePosition = startOffset + position;

       if (relativePosition < 0) {
           return DataPointStatus.MISSING;
       }
       else if (relativePosition > valueList.size()) {
           return DataPointStatus.MISSING;
       }
       else {
           return statusList.get(relativePosition);
       }
   }

   /**
   Returns a DataPoint object which represents the data stored at the specified
   position.
   @param position - The position between 0 and DataPointCollection.size() - 1
   @return com.datagenic.fourthdimension.data.DataPoint
   @roseuid 415830630167
    */
   public DataPoint getDataPoint(int position)
   {

       if (position < 0 || position >= this.size())
           throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));

       int relativePosition = startOffset + position;

       long index = getIndex(position);

       if (relativePosition < 0) {
           return new DataPoint(index,null,DataPointStatus.MISSING);
       }
       else if (relativePosition >= valueList.size()) {
           return new DataPoint(index,null,DataPointStatus.MISSING);
       }
       else {
           return new DataPoint(index,valueList.getObject(relativePosition),statusList.get(relativePosition));
       }
   }

   /**
   Returns a DataPoint object which represents the data stored at the specified
   position.  The returned object will need to be cast into a DataPoint as the
   method signature indicates that the return type  of the method is
   <code>Object</code>. This is due to the collection interface.
   @param position - The position between 0 and DataPointCollection.size() - 1
   @return java.lang.Object
   @roseuid 41627EDE02DE
    */
   public Object get(int position)
   {
       return getDataPoint(position);
   }

   /**
   @return org.apache.commons.collections.primitives.ArrayLongList
   @roseuid 422DBE2B00DA
    */
   public ArrayLongList getIndexList()
   {
       long[] index = this.calendar.getIndexes(minIndex,maxIndex);
       ArrayLongList list = new ArrayLongList(index.length);
       for (int i=0;i<index.length;i++) {
           list.add(index[i]);
       }
       return list;
   }

   /**
   @return org.apache.commons.collections.primitives.ArrayByteList
   @roseuid 422DBE3E009C
    */
   public ArrayByteList getStatusList()
   {
       ArrayByteList list = new ArrayByteList(this.size());
       int relativePosition = 0;


       for (int i=0;i<this.size();i++) {

           relativePosition = startOffset + i;

           if (relativePosition < 0 || relativePosition >= valueList.size()) {
               list.add(DataPointStatus.MISSING);
           } else {
               list.add(statusList.get(relativePosition));
           }
       }

       return list;
   }

   /**
   @return com.datagenic.fourthdimension.ListAdapter
   @roseuid 422DBE4B03D8
    */
   public ListAdapter getValueList()
   {
       ListAdapter list = null;

       if (valueList instanceof FloatListAdapter) {
           list = new FloatListAdapter();
       } else if (valueList instanceof DoubleListAdapter) {
           list = new DoubleListAdapter();
       } else if (valueList instanceof LongListAdapter) {
           list = new LongListAdapter();
       } else if (valueList instanceof IntListAdapter) {
           list = new IntListAdapter();
       } else if (valueList instanceof ShortListAdapter) {
           list = new ShortListAdapter();
       } else if (valueList instanceof ByteListAdapter) {
           list = new ByteListAdapter();
       } else if (valueList instanceof BooleanListAdapter) {
           list = new BooleanListAdapter();
       } else if (valueList instanceof StringListAdapter) {
           list = new StringListAdapter();
       }

       int relativePosition = 0;

       for (int i=0;i<this.size();i++) {

           relativePosition = startOffset + i;

           if (relativePosition < 0 || relativePosition >= valueList.size()) {
               list.add(null);
           } else {
               list.add(valueList.getObject(relativePosition));
           }
       }

       return list;
   }

   /**
   @return com.datagenic.fourthdimension.dates.CalendarFrequency
   @roseuid 424024D702FD
    */
   public CalendarFrequency getCalendarFrequency()
   {
       return this.calendar;
   }
}
/**

Object DataPointCollection.set(int,java.lang.Object){
    return null;
   }










DataPointCollection.DataPointCollection(com.datagenic.fourthdimension.dates.CalendarFrequency,long,long){
       this.calendar = calendar;
       this.minIndex = startIndex;
       this.maxIndex = endIndex;
       this.indexes = calendar.getIndexes(startIndex,endIndex);
       this.data = new Object[size()];
       this.statuses = new byte[size()];
   }


DataPointCollection.DataPointCollection(com.datagenic.fourthdimension.dates.CalendarFrequency){
       this(calendar,0,0);
   }









 */
