package com.datagenic.fourthdimension.data;

import org.apache.commons.collections.primitives.*;
import com.datagenic.fourthdimension.*;
import java.util.AbstractList;
import java.io.Serializable;
import com.datagenic.fourthdimension.dates.CalendarFrequency;

/**
 A <code>DataPointCollection</code> is a wrapper class which encapsulates access
 to the underlying storage of data.  This collection object is not modifiable.
 Instead the appropriate method on the <code>Series</code> must be used to update a
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
 HISTORY
 <p>

 This class has be revisited a number of times to improve performance and to improve
 the in memory object size.  As access to the underlying data is
 paramount, the object is a little heavier than it needs to be.
 In previous implementations offsets from a min and max index were used to calculate
 the required index. Although this resulted in a smaller object, in order
 to retrieve any data points the index value had to be recalculated using the
 CalendarFrequency.  Depending on the Calendar, some implementations were slower
 than others in determining this data.<br>
 <p>
 For this reason, the index array is back in this implementation.  The responsibility of
 creating the index array is now down to the series.getDataPointCollection().
 <p>
 */
public class DataPointCollectionV2 extends AbstractList implements Serializable {
    private static final long serialVersionUID = 1;

    /**
        The calendar of this collection
     */
    private CalendarFrequency calendar;

    /**
     * Retains the minimum index of this DataPointCollection.  It is the same value as the index value in the 0th position
     * of the index array
     */
    private long minIndex;

    /**
     * Retains the maximum index of this DataPointCollection.  It is the same values as the index value in the size() - 1 position
     * of the index array
     */
    private long maxIndex;

    /**
        The array list used to represent the index of the data.  The index value must
        align to the calendar
     */
    private LongList indexList;

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
     * Creates a DataPointCollection using the specified indexes, data and statuses.
     *   The indexes must all be aligned to <code>calendar</code>.
     *
     *   @param calendar CalendarFrequency which the datapoint are aligned to. Note, the index values are
     * never validated against the calendar.
     *   @param indexes A LongList of index values which align to the calendar
     *   @param values The values of this DataPointCollection.
     *   @param statuses The statuses of this DataPointCollection.
     */
    public DataPointCollectionV2(CalendarFrequency calendar, LongList indexes, ListAdapter values, ByteList statuses) {

        this.calendar = calendar;
        this.indexList = indexes;
        this.valueList = values;
        this.statusList = statuses;

        int size = indexes.size();
        if (values.size() != size) {
            throw new IllegalArgumentException("Size mismatch. [index.size=" + indexes.size() + " values.size=" +
                                               values.size() + "]");
        }

        if (statuses.size() != size) {
            throw new IllegalArgumentException("Size mismatch. [index.size=" + indexes.size() + " statuses.size=" +
                                               statuses.size() + "]");
        }

        //Store the min and max indexes. Saves having to access them each time a call is made
        this.minIndex = indexes.get(0);
        this.maxIndex = indexes.get(indexes.size() - 1);

    }

    /**
        Returns the minimum index point for this collection.
        @return long
        @roseuid 4146C582009C
     */
    public long getMinIndex() {
        return minIndex;
    }

    /**
        Returns the maximum index point for this collection.
        @return long
        @roseuid 4146C5880280
     */
    public long getMaxIndex() {
        return maxIndex;
    }

    /**
        Returns the size of this collection. This is calculated by using the size of the internal index array
        @return int The size of the collection
        @roseuid 4146C59501B5
     */
    public int size() {
        return indexList.size();
    }

    /**
        Gets the index value at the specified position.  The index value can be
        translated into a Java Date if the series represents a time dimension.
        @param position - The position between 0 and DataPointCollection.size() - 1
        @return long
        @roseuid 4146C5A500BB
     */
    public long getIndex(int position) {
        if (position < 0 || position >= this.size()) {
            throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));
        }

        return indexList.get(position);
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
    public Object getData(int position) {

        if (position < 0 || position >= this.size()) {
            throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));
        }

        return valueList.getObject(position);

    }

    /**
        Gets the status value at the specified position.  Use the DataPointStatus class
        to help translate this value into a readable form.
        @param position - The position between 0 and DataPointCollection.size() - 1
        @return byte
        @roseuid 4146C5E1005D
     */
    public byte getStatus(int position) {

        if (position < 0 || position >= this.size()) {
            throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));
        }

        return statusList.get(position);

    }

    /**
        Returns a DataPoint object which represents the data stored at the specified
        position.
        @param position - The position between 0 and DataPointCollection.size() - 1
        @return com.datagenic.fourthdimension.data.DataPoint
        @roseuid 415830630167
     */
    public DataPoint getDataPoint(int position) {

        if (position < 0 || position >= this.size()) {
            throw new IndexOutOfBoundsException("[" + position + "] Value must be between 0 and " + (this.size() - 1));
        }

        return new DataPoint(indexList.get(position), valueList.getObject(position), statusList.get(position));

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
    public Object get(int position) {
        return getDataPoint(position);
    }

    /**
        @return org.apache.commons.collections.primitives.ArrayLongList
        @roseuid 422DBE2B00DA
     */
    public ArrayLongList getIndexList() {
        return new ArrayLongList(indexList.toArray());
    }

    /**
        @return org.apache.commons.collections.primitives.ArrayByteList
        @roseuid 422DBE3E009C
     */
    public ArrayByteList getStatusList() {
        return new ArrayByteList(statusList.toArray());
    }

    /**
        @return com.datagenic.fourthdimension.ListAdapter
        @roseuid 422DBE4B03D8
     */
    public ListAdapter getValueList() {
        return (ListAdapter) valueList.clone();
    }

    /**
        @return com.datagenic.fourthdimension.dates.CalendarFrequency
        @roseuid 424024D702FD
     */
    public CalendarFrequency getCalendarFrequency() {
        return this.calendar;
    }
}
