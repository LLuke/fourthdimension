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

import java.beans.VetoableChangeListener;
import java.io.Serializable;

import com.datagenic.fourthdimension.data.DataPoint;
import com.datagenic.fourthdimension.data.DataPointCollection;
import com.datagenic.fourthdimension.dates.CalendarFrequency;
import com.datagenic.fourthdimension.data.DataPointStatus;
import org.apache.commons.collections.primitives.ArrayLongList;
import org.apache.commons.collections.primitives.ArrayByteList;
import com.datagenic.fourthdimension.dates.IndexAlignmentException;
import com.datagenic.fourthdimension.dates.IndexRangeTooLargeException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * A <code>Series</code> is a basic object which encapsulates the storage of
 * <code>DataPoints</code> aligned to a Calendar. Every <code>Series</code>
 * has an underying calendar which specifies the frequency at which the periods
 * occur. Series which contain data stored at a time frequency (as opposed to a
 * numeric frequency) are indexed according to the number of milliseconds
 * returned by Java from a Date. Series which are numeric are simply long values
 * ranging from 1 to MAXLONG.
 * <p>
 * In most cases accessing the collection of DataPoints is through the
 * DataPointCollection class for a start and end period.
 */
public abstract class Series implements DataObject, Serializable {
	private static final long serialVersionUID = 1;
	public static final long MIN_DATAPOINT_INDEX = Long.MIN_VALUE;
	public static final long MAX_DATAPOINT_INDEX = Long.MAX_VALUE;

	/**
	 * Internal index array used to represent the indexes for the series data
	 */
	protected ArrayLongList indexList = new ArrayLongList();

	/**
	 * Internal index array used to represent the status of the series data
	 */
	protected ArrayByteList statusList = new ArrayByteList();

	/**
	 * An internal array maintaining the edit status of the data point
	 */
	protected ArrayByteList editStatusList = new ArrayByteList();

	/**
	 * Indicates the minimum series index value for this series
	 */
	protected long firstIndex = 0;

	/**
	 * Indicates the maximum series index value for this series
	 */
	protected long lastIndex = 0;
	private byte EDIT_STATUS_EDITED = 1;
	private byte EDIT_STATUS_UNEDITED = 0;
	protected ListAdapter listAdapter;

	/**
	 * Storage of the attributes of the series. Such as name, calendar,
	 * description etc.
	 */
	private AttributeMap attributeMap = new AttributeMap();

	/**
	 * Can be used to uniquely identify this series. In some cases it will be up
	 * to the implementation to subclass the GlobalIdentifier class to make it
	 * more accessable.
	 * 
	 * This identifer may be null.
	 */
	private GlobalIdentifier globalIdentifier;
	
	private VetoableChangeListener timezoneListener = new MyVetoableChangeListener();

	/**
	 * Constructs a Series with a name and calendar. The series is intially
	 * empty. To populate the contents of the series either create a
	 * DataPointCollection and call <code>Series.setDataPointCollection()</code>
	 * or insert individual DataPoints by using the
	 * <code>Series.setDataPoint()</code> methods.
	 * 
	 * @param name -
	 *            The name of the series
	 * @param calendar -
	 *            The calendar at which DataPoints must align to
	 * @roseuid 4146BF340177
	 */
	public Series(String name, CalendarFrequency calendar) {
		super();
		setName(name);
		setCalendar(calendar);
		listAdapter = makeListAdapter(); // 2004-05-13 The list adpater
											// wasn't being created until data
											// was set via the setDataPoint.
	}

	/**
	 * Access method for the attributeMap property.
	 * 
	 * @return the current value of the attributeMap property
	 */
	public AttributeMap getAttributeMap() {
		return attributeMap;
	}

	/**
	 * Returns the DataPoint object that exists at the <code>index</code>
	 * period position.
	 * 
	 * @param index -
	 *            The index of the DataPoint that is to be returned. If the
	 *            series is indexed by time then this index value is similar to
	 *            the number of milliseconds since 1970.
	 * @return com.datagenic.fourthdimension.data.DataPoint
	 * @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
	 * @throws com.datagenic.fourthdimension.dates.IndexRangeTooLargeException
	 * @roseuid 4146BC6A0138
	 */
	public DataPoint getDataPoint(long index) throws IndexAlignmentException,
			IndexRangeTooLargeException {
		CalendarFrequency calendar = getCalendar();
		if (!calendar.aligns(index)) {
			throw new IndexAlignmentException(index + " is not an index at "
					+ calendar.getCalendarFrequencyName() + " frequency.");
		}

		if (index < getMinIndex() || index > getMaxIndex()) {
			return new DataPoint(index, null, DataPointStatus.MISSING);
		}

		long position = calendar.periodsBetween(getMinIndex(), index);
		if (position > Integer.MAX_VALUE)
			throw new IndexRangeTooLargeException();

		return getDataPointImpl((int) position);
	}

	/**
	 * Provides a non editable collection of DataPoints between the
	 * <code>getMinIndex()</code> and <code>getMaxIndex()</code> of the
	 * series.
	 * 
	 * @return com.datagenic.fourthdimension.data.DataPointCollection
	 * @roseuid 4146BCC70290
	 */
	public DataPointCollection getDataPointCollection() {
		return getDataPointCollection(getMinIndex(), getMaxIndex());
	}

	/**
	 * Provides a non editable collection of DataPoints between the
	 * <code>firstIndex</code> and the <code>lastIndex</code>.
	 * <p>
	 * Cases where the series <code>getMinIndex()</code> or
	 * <code>getMaxIndex()</code> are outside the range of the parameters, a
	 * copy the underlying storage is resized to the required range. Default
	 * values are used where appropriate in these cases
	 * 
	 * @param firstIndex -
	 *            The first index of the series.
	 * @param lastIndex -
	 *            The last index of the series (inclusive)
	 * @return com.datagenic.fourthdimension.data.DataPointCollection
	 * @roseuid 4146BC8A0261
	 */
	public DataPointCollection getDataPointCollection(long firstIndex,
			long lastIndex) {
		if (firstIndex == MIN_DATAPOINT_INDEX)
			firstIndex = getMinIndex();

		if (lastIndex == MAX_DATAPOINT_INDEX)
			lastIndex = getMaxIndex();

		CalendarFrequency calendar = getCalendar();
		if (!calendar.aligns(firstIndex)) {
			throw new IndexAlignmentException(firstIndex
					+ " is not an index at "
					+ calendar.getCalendarFrequencyName() + " frequency.");
		}
		if (!calendar.aligns(lastIndex)) {
			throw new IndexAlignmentException(lastIndex
					+ " is not an index at "
					+ calendar.getCalendarFrequencyName() + " frequency.");
		}

		/**
		 * The startOffset is the number of calendar units positions from the
		 * minIndex. A positive offset means that the firstIndex was greater
		 * than the minIndex, a negative offset means that the firstIndex was
		 * less than the minIndex
		 */
		long startOffset = Math.abs(getCalendar().periodsBetween(firstIndex,
				this.getMinIndex()));
		if (startOffset > Integer.MAX_VALUE)
			throw new IndexRangeTooLargeException();

		if (firstIndex < this.getMinIndex())
			startOffset = -startOffset;

		/**
		 * The endOffset is the number of calendar units positions from the
		 * maxIndex. A positive offset means that the lastIndex was greater than
		 * the maxIndex, a negative offset means that the lastIndex was less
		 * than the maxIndex
		 */
		long endOffset = Math.abs(getCalendar().periodsBetween(lastIndex,
				this.getMaxIndex()));
		if (endOffset > Integer.MAX_VALUE)
			throw new IndexRangeTooLargeException();

		if (lastIndex < this.getMaxIndex())
			endOffset = -endOffset;

		return new DataPointCollection(this.getCalendar(), this.indexList,
				listAdapter, statusList, (int) startOffset, (int) endOffset,
				firstIndex, lastIndex);
	}

	/**
	 * Inserts a DataPoint into the series. The internal index value of this
	 * DataPoint will be used to determine where in the series this DataPoint
	 * belongs.
	 * 
	 * @param data -
	 *            The <code>DataPoint</code> that will be inserted into the
	 *            series.
	 * @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
	 * @see com.datagenic.fourthdimension.Series#setDataPoint(long index, Object
	 *      data, byte status)
	 * @roseuid 4146BE660261
	 */
	public void setDataPoint(DataPoint data) throws IndexAlignmentException {
		setDataPoint(data.getIndex(), data.getValue(), data.getStatus());
	}

	/**
	 * Inserts a <code>value</code> at a position <ocde>index</code> in the
	 * series with a status <code>status</code>. The call to this method may
	 * result in the underlying series storage being expanded to accomodate the
	 * new value.
	 * <p>
	 * A series is always contiguous between the min and max indexes. If the
	 * value to be inserted is after the max index, then a call to the
	 * underlying calendar is made to determine the number of additional periods
	 * which need to be added to the end of the series to accomodate the value.
	 * In this case the <code>index</code> will then become the max index.
	 * <p>
	 * If the index is less than the min index of the series then the number of
	 * periods between the <code>index</code> and the min index is added to
	 * the beginning of the storage. In this case, the <code>index</code> will
	 * then become the min index.
	 * <p>
	 * If the index is between the min and the max then the value which may have
	 * already existed at this period will be overwritten.
	 * <p>
	 * A special case when the min index and max index both equal 0 i.e. there
	 * are no values in the series, the <code>index</code> will become both
	 * the min and the max index.
	 * 
	 * @param index -
	 *            The position of the data either in a time dimension or a
	 *            series position. When the index is a time dimension it can be
	 *            directly translated into a java.util.Date.
	 * @param data -
	 *            The value that is stored at the specific index point
	 * @param status -
	 *            The status of the value which is stored at the index point.
	 * @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
	 * @roseuid 4146BE770213
	 */
	public void setDataPoint(long index, Object data, byte status)
			throws IndexAlignmentException {

		CalendarFrequency calendar = getCalendar();
		if (!calendar.aligns(index)) {
			throw new IndexAlignmentException(index + " is not an index at "
					+ calendar.getCalendarFrequencyName() + " frequency.");
		}

		// If the index array doesn't have any values, then we can simply create
		// a new array and set the
		// min and max values
		if (getMinIndex() == 0 && getMaxIndex() == 0) {

			this.indexList = new ArrayLongList();
			this.indexList.add(index);

			this.listAdapter = makeListAdapter();
			this.listAdapter.add(data);

			this.statusList = new ArrayByteList();
			this.statusList.add(status);

			this.editStatusList = new ArrayByteList();
			this.editStatusList.add(EDIT_STATUS_EDITED);

			this.firstIndex = index;
			this.lastIndex = index;

			fireSeriesChanged();
			return;
		}

		if (index > getMaxIndex()) {
			// The index that we are about to insert needs to be at the end of
			// the array, so expand array make space for it
			long oldLast = getMaxIndex();

			// this is the number of indexes to add to the end of the array
			long tmp = calendar.periodsBetween(oldLast, index);
			if (tmp > Integer.MAX_VALUE)
				throw new IndexRangeTooLargeException();
			int periodsToAdd = (int) tmp;

			// add the difference of the getMaxIndex() to index position to the
			// end of the current index array
			// this is the equivalent of rebuilding the entire index array
			long[] newIndexes = calendar.getIndexes(getMaxIndex(), index);

			// assert(newIndexes[0] == indexList.get(indexList.size() - 1));

			for (int i = 0; i < periodsToAdd; i++) {
				// remember that the indexes will contain an overlapped value at
				// the beginning of the array
				// as we are adding the indexes to the end then we need to skip
				// the first value
				this.indexList.add(newIndexes[i + 1]);
				this.listAdapter.add(null);
				this.statusList
						.add(com.datagenic.fourthdimension.data.DataPointStatus.MISSING);
				this.editStatusList.add(EDIT_STATUS_UNEDITED);
			}

			long position = calendar.periodsBetween(getMinIndex(), index);
			if (position > Integer.MAX_VALUE)
				throw new IndexRangeTooLargeException();

			this.listAdapter.set((int) position, data);
			this.statusList.set((int) position, status);
			this.editStatusList.set((int) position, EDIT_STATUS_EDITED);

		} else if (index < getMinIndex()) {

			long tmp = calendar.periodsBetween(index, getMinIndex());
			if (tmp > Integer.MAX_VALUE)
				throw new IndexRangeTooLargeException();
			int periodsToAdd = (int) tmp;

			// add the indexes between index to getMinIndex() to the beginning
			// of the array.
			// This is the equivalent of rebuilding the entire array
			long[] tmpIndexes = calendar.getIndexes(index, getMinIndex());

			ListAdapter tmpListAdapter = makeListAdapter();

			ArrayByteList tmpStatusList = new ArrayByteList(periodsToAdd);
			ArrayByteList tmpEditList = new ArrayByteList(periodsToAdd);
			ArrayLongList tmpIndexList = new ArrayLongList(periodsToAdd);

			for (int i = 0; i < periodsToAdd; i++) {

				tmpListAdapter.add(null);
				tmpStatusList
						.add(com.datagenic.fourthdimension.data.DataPointStatus.MISSING);
				tmpEditList.add(EDIT_STATUS_UNEDITED);
				// remember that the indexes will contain an overlapped value at
				// the end of the array
				// as we are adding the indexes to the beginning then we don't
				// need to do anything about it
				tmpIndexList.add(tmpIndexes[i]);
			}

			this.listAdapter.addAll(0, tmpListAdapter);

			this.statusList.addAll(0, tmpStatusList);
			this.editStatusList.addAll(0, tmpEditList);
			this.indexList.addAll(0, tmpIndexList);

			this.listAdapter.set(0, data);
			this.statusList.set(0, status);
			this.editStatusList.set(0, EDIT_STATUS_EDITED);

		} else {

			long minPeriodOffset = calendar
					.periodsBetween(getMinIndex(), index);
			if (minPeriodOffset > Integer.MAX_VALUE)
				throw new IndexRangeTooLargeException();

			this.indexList.set((int) minPeriodOffset, index);
			this.listAdapter.set((int) minPeriodOffset, data);
			this.statusList.set((int) minPeriodOffset, status);
			this.editStatusList.set((int) minPeriodOffset, EDIT_STATUS_EDITED);

		}

		this.firstIndex = this.indexList.get(0);
		this.lastIndex = this.indexList.get(this.indexList.size() - 1);

		fireSeriesChanged();
	}

	/**
	 * @param index
	 * @param data
	 * @param status
	 * @roseuid 422750D6001F
	 */
	public void setDataPoint(long index, float data, byte status) {
		setDataPoint(index, new Float(data), status);
	}

	/**
	 * @param index
	 * @param data
	 * @param status
	 * @roseuid 42275106003E
	 */
	public void setDataPoint(long index, double data, byte status) {
		setDataPoint(index, new Double(data), status);
	}

	/**
	 * @param index
	 * @param data
	 * @param status
	 * @roseuid 4227511E0109
	 */
	public void setDataPoint(long index, long data, byte status) {
		setDataPoint(index, new Long(data), status);
	}

	/**
	 * @param index
	 * @param data
	 * @param status
	 * @roseuid 42275127037A
	 */
	public void setDataPoint(long index, int data, byte status) {
		setDataPoint(index, new Integer(data), status);
	}

	/**
	 * @param index
	 * @param data
	 * @param status
	 * @roseuid 4227513601C5
	 */
	public void setDataPoint(long index, byte data, byte status) {
		setDataPoint(index, new Byte(data), status);
	}

	/**
	 * @param index
	 * @param data
	 * @param status
	 * @roseuid 42275141035B
	 */
	public void setDataPoint(long index, short data, byte status) {
		setDataPoint(index, new Short(data), status);
	}

	/**
	 * Overwrites the entire series contents with that of the new
	 * DataPointCollection.
	 * 
	 * @param data -
	 *            The DataPointCollection which contains the new data for this
	 *            series.
	 * @throws com.datagenic.fourthdimension.dates.IndexAlignmentException
	 * @roseuid 4146BE9C01B5
	 */
	public void setDataPointCollection(DataPointCollection data)
			throws IndexAlignmentException {

		ArrayLongList tmpIndexList = new ArrayLongList(data.size());
		ListAdapter tmpListAdapter = makeListAdapter();
		ArrayByteList tmpStatusList = new ArrayByteList(data.size());
		ArrayByteList tmpEditStatusList = new ArrayByteList(data.size());

		// don't use data.get(i) as it will create a new DataPoint for every
		// iteration. Feel there is less overhead in doing the
		// three accesses below as opposed to one access for the dataPoint
		// object, and then three more accesses to get the data.
		for (int i = 0; i < data.size(); i++) {
			tmpIndexList.add(data.getIndex(i));
			tmpListAdapter.add(data.getData(i));
			tmpStatusList.add(data.getStatus(i));
			tmpEditStatusList.add(EDIT_STATUS_EDITED);
		}

		// Now asssign it back to the series.
		this.indexList = tmpIndexList;
		this.listAdapter = tmpListAdapter;
		this.statusList = tmpStatusList;
		this.editStatusList = tmpEditStatusList;

		firstIndex = this.indexList.get(0);
		lastIndex = this.indexList.get(this.indexList.size() - 1);

		fireSeriesChanged();
	}

	/**
	 * Returns the calendar frequency which the indexes of this series are
	 * aligned to
	 * 
	 * @return com.datagenic.fourthdimension.dates.CalendarFrequency
	 * @roseuid 4146C26A01C5
	 */
	public CalendarFrequency getCalendar() {
		Object obj = null;
		try {
			obj = attributeMap.get(AttributeMapKey.CALENDAR);
			return (CalendarFrequency) obj;
		} catch (ClassCastException cce) {
			throw new FourthDimensionRuntimeException(
					"Expected a CalendarFrequency but got a "
							+ obj.getClass().getName() + " instead.");
		}
	}

	/**
	 * Sets the calendar frequency which the indexes of this series are aligned
	 * to.
	 * 
	 * @param calendar -
	 *            calendar frequency that the series indexes must align to
	 * @roseuid 4146C28401D4
	 */
	public void setCalendar(CalendarFrequency calendar) {
		if (statusList.size() != 0) {
			throw new RuntimeException(
					"Attempting to set a calendar when values already exist in this series.");
		} else {
			CalendarFrequency oldCalendar = getCalendar();
			if (oldCalendar != null) {
				// Remove the listener from the old calendar so that we the old
				// calendar can be garbage collected.
				oldCalendar.removeVetoPropertyChangeListener(timezoneListener);
			}

			attributeMap.put(AttributeMapKey.CALENDAR, calendar);
			// Add the timezone listener to this calendar assigned to the series
			calendar.addVetoPropertyChangeListener(timezoneListener);
		}
	}

	/**
	 * Returns the minimum index for this series. If the series is indexed using
	 * a time dimension, the index value returned can be translated into a Java
	 * Date.
	 * 
	 * @return long
	 * @roseuid 4146C2AB0203
	 */
	public long getMinIndex() {
		return firstIndex;
	}

	/**
	 * Returns the maximum index for this series. If the series is indexed using
	 * a time dimension, the index value returned can be translated into a Java
	 * Date.
	 * 
	 * @return long
	 * @roseuid 4146C2B6003E
	 */
	public long getMaxIndex() {
		return lastIndex;
	}

	/**
	 * Provides access to the underlying storage of the data values of this
	 * series. The return object in this case is a ListAdapter
	 * 
	 * @return com.datagenic.fourthdimension.ListAdapter
	 * @roseuid 4146C2C103B9
	 */
	public ListAdapter getDataPointValues() {
		return listAdapter;
	}

	/**
	 * Provides access to the underlying storage of the index values of this
	 * series. The arrays are returned verbatium.
	 * 
	 * @return long[]
	 * @roseuid 4146C2D20290
	 */
	public long[] getDataPointIndexes() {
		return indexList.toArray();
	}

	/**
	 * Returns the indexes of the series converted to a java.util.Date object.
	 * No changes to the underlying long value is made. It is equivalent to
	 * performing <code>new java.util.Date(index[i])</code> on each index.
	 * 
	 * @return java.util.Date[]
	 * @roseuid 4292EF0F030A
	 */
	public Date[] getDataPointIndexesAsJavaDates() {
		Date[] indexesAsDates = new Date[indexList.size()];
		long[] indexes = getDataPointIndexes();
		for (int i = 0; i < indexes.length; i++) {
			indexesAsDates[i] = new Date(indexes[i]);
		}

		return indexesAsDates;
	}

	/**
	 * Provides access to the underlying storage of the data status of this
	 * series. The arrays are returned verbatium.
	 * 
	 * @return byte[]
	 * @roseuid 4146C2DE0000
	 */
	public byte[] getDataPointStatuses() {
		return statusList.toArray();
	}

	/**
	 * As requirement of the DataObject, getDataObject must either return
	 * itself, or a variation of itself. This method may be more appropriate
	 * being part of a function.
	 * 
	 * @return com.datagenic.fourthdimension.DataObject
	 * @roseuid 423F0C85039A
	 */
	public DataObject getDataObject() {
		return this;
	}

	/**
	 * Provides access to the name of this series.
	 * 
	 * @return java.lang.String
	 * @roseuid 414AD93F0196
	 */
	public String getName() {
		return (String) getAttribute(AttributeMapKey.NAME);
	}

	/**
	 * Sets the name of this series.
	 * 
	 * @param name -
	 *            A name for the series
	 * @roseuid 414AD93F01D4
	 */
	public void setName(String name) {
		setAttribute(AttributeMapKey.NAME, name);
	}

	/**
	 * Sets attributes specific to the series such as the name, description ,
	 * calendar etc. Refer to the <code>AttributeMapKey</code> for details of
	 * intrinsic key values.
	 * 
	 * @param name -
	 *            The name of the value
	 * @param value -
	 *            The value for the name
	 * @roseuid 4162AE4702EE
	 */
	public void setAttribute(String name, Object value) {
		if (!name.equals(AttributeMapKey.MODIFIED)) {
			fireSeriesChanged();
		}

		attributeMap.put(name, value);
	}

	/**
	 * Returns an attribute of the series. Refer to the AttributeMapKey for
	 * details of intrinsic key values.
	 * 
	 * @param name -
	 *            The name of the attribute required
	 * @return Object
	 * @roseuid 4162AF7D0222
	 */
	public Object getAttribute(String name) {
		return attributeMap.get(name);
	}

	/**
	 * Returns a <code>SeriesInfo</code> object encapsulating the basic
	 * information pertaining to this series. No data is stored in this
	 * representation.
	 * 
	 * @return com.datagenic.fourthdimension.SeriesInfo
	 * @roseuid 4164FEC3002E
	 */
	public SeriesInfo getSeriesInfo() {
		return new SeriesInfo(getMinIndex(), getMaxIndex(), getAttributeMap());
	}

	/**
	 * Resets the edit statuses all <code>DataPoint</code> instances of this
	 * series to 'not edited'. Previous edit status value are lost
	 * 
	 * @roseuid 416EF13F001F
	 */
	public void resetEditStatus() {
		// This will work as the default value for a byte is 0 which is the same
		// as the EDIT_STATUS_UNEDITED value
		// editStatusList = new ArrayByteList(indexList.size());

		// The above line would have worked if it was an standard array (default
		// being 0 ==EDIT_STATUS_UNEDITED) , however, as it isn't we need to
		// manually
		// insert an EDIT_STATUS_UNEDITED value into each position
		editStatusList = new ArrayByteList(indexList.size());
		for (int i = 0; i < indexList.size() - 1; i++) {
			editStatusList.add(EDIT_STATUS_UNEDITED);
		}
	}

	/**
	 * Provides an Iterator which only returns <code>DataPoint</code>
	 * instances which have been edited. This provides a useful way of determing
	 * changes to the series since the last time it was persisted.
	 * <p>
	 * Note that the edit status of the series can be reset at any time by a
	 * call to the <code>resetEditStatus()</code> method. This method resets
	 * all edit statuses to 'not edited' regardless of whether the value has
	 * been changed or not.
	 * 
	 * <pre>
	 * Iterator i = series.getDataPointIteratorByEdits();
	 * while (i.hasNext()) {
	 * 	DataPoint p = (DataPoint) i.next();
	 * 	System.out.println(p.toString());
	 * }
	 * </pre>
	 * 
	 * @return Iterator
	 * @roseuid 416EF48600FA
	 */
	public Iterator getDataPointIteratorByEdits() {
		return new Iterator() {
			int lastEditIndex = -1;

			private boolean hasMoreEdits() {
				for (int i = lastEditIndex + 1; i < editStatusList.size(); ++i) {
					if (editStatusList.get(i) == EDIT_STATUS_EDITED)
						return true;
				}
				return false;
			}

			private int nextEdit() {
				for (int i = lastEditIndex + 1; i < editStatusList.size(); ++i) {
					if (editStatusList.get(i) == EDIT_STATUS_EDITED)
						return i;
				}
				return -1;
			}

			/**
			 * remove
			 */
			public void remove() {
				throw new UnsupportedOperationException(
						"Elements cannot be removed from this iterator.");
			}

			/**
			 * hasNext
			 * 
			 * @return boolean
			 */
			public boolean hasNext() {
				return hasMoreEdits();
			}

			/**
			 * next
			 * 
			 * @return Object
			 */
			public Object next() {

				lastEditIndex = nextEdit();

				Object value = listAdapter.getObject(lastEditIndex);
				long index = indexList.get(lastEditIndex);
				byte status = statusList.get(lastEditIndex);

				return new DataPoint(index, value, status);
			}
		};
	}

	/**
	 * Provides an Iterator which only returns <code>DataPoint</code>
	 * instances which have a specified status.
	 * <p>
	 * The example below create an Iterator which only returns
	 * DataPointStatus.MISSING values.
	 * 
	 * <pre>
	 * Iterator i = series.getDataPointIteratorByStatus(DataPointStatus.MISSING);
	 * while (i.hasNext()) {
	 * 	DataPoint p = (DataPoint) i.next();
	 * 	System.out.println(p.toString());
	 * }
	 * </pre>
	 * 
	 * @param status -
	 *            The status of <code>DataPoint</code> instances to return in
	 *            the Iterator
	 * @return Iterator
	 * @roseuid 416F9271037A
	 */
	public Iterator getDataPointIteratorByStatus(final byte status) {
		return new Iterator() {
			int lastEditIndex = -1;

			private boolean hasMoreEdits() {
				for (int i = lastEditIndex + 1; i < statusList.size(); ++i) {
					if (statusList.get(i) == status)
						return true;
				}
				return false;
			}

			private int nextEdit() {
				for (int i = lastEditIndex + 1; i < statusList.size(); ++i) {
					if (statusList.get(i) == status)
						return i;
				}
				return -1;
			}

			/**
			 * remove
			 */
			public void remove() {
				throw new UnsupportedOperationException(
						"Elements cannot be removed from this iterator.");
			}

			/**
			 * hasNext
			 * 
			 * @return boolean
			 */
			public boolean hasNext() {
				return hasMoreEdits();
			}

			/**
			 * next
			 * 
			 * @return Object
			 */
			public Object next() {

				lastEditIndex = nextEdit();

				Object value = listAdapter.getObject(lastEditIndex);
				long index = indexList.get(lastEditIndex);
				byte status = statusList.get(lastEditIndex);

				return new DataPoint(index, value, status);
			}
		};
	}

	/**
	 * @roseuid 416CF61E0280
	 */
	void fireSeriesChanged() {
		this.setAttribute(AttributeMapKey.MODIFIED, new java.util.Date());
	}

	/**
	 * Returns an instance of a GlobalIndentifier which can be used to unique
	 * identifiy this object. The default identifier is based upon the 3
	 * attributes ( 2 of which are optional) Service,database and name. If the
	 * optional attributes are not specified then they are ommited from the
	 * identifier.
	 * 
	 * <pre>
	 * GlobalIndentifier identifier = series.getGlobalIdentifier();
	 * System.out.println(identifier.toString());
	 * </pre>
	 * 
	 * @return identifier which can be used to uniquely identifier this object.
	 */
	public GlobalIdentifier getGlobalIdentifier() {
		/**
		 * If the identifier is not null then use it. Otherwise, generate one on
		 * the fly each time. This will ensure that the identifier is up to date
		 */
		if (globalIdentifier == null) {
			ArrayList list = new ArrayList();

			String servicename = (String) getAttribute(AttributeMapKey.SERVICE_NAME);
			if (servicename != null)
				list.add(servicename + "://");
			else
				list.add("UNKNOWN" + "://");

			String database = (String) getAttribute(AttributeMapKey.DATABASE_NAME);
			if (database != null)
				list.add(database + "'");
			else
				list.add("UNKNOWN" + "'");

			String name = (String) getAttribute(AttributeMapKey.NAME);
			list.add(name);

			String[] identifiers = (String[]) list.toArray(new String[0]);
			return new GlobalIdentifier(identifiers);
		}

		return globalIdentifier;
	}

	/**
	 * Set an instance of a GlobalIndentifier which can be used to unique
	 * identifiy this object
	 * 
	 * @param identifier
	 * @roseuid 41B9B740036B
	 */
	public void setGlobalIdentifier(GlobalIdentifier identifier) {
		globalIdentifier = identifier;
	}

	/**
	 * @return com.datagenic.fourthdimension.ListAdapter
	 * @roseuid 422738D602DE
	 */
	public abstract ListAdapter makeListAdapter();

	/**
	 * @param position
	 * @return com.datagenic.fourthdimension.data.DataPoint
	 * @roseuid 42273ECC00FA
	 */
	protected abstract DataPoint getDataPointImpl(int position);

	/**
	 * @param minIndex
	 * @param maxIndex
	 * @return com.datagenic.fourthdimension.DataObject
	 * @roseuid 423F10370271
	 */
	public DataObject getDataObject(long minIndex, long maxIndex) {

		if (this instanceof DoubleSeries) {
			DoubleSeries series = (DoubleSeries) ((DoubleSeries) (this))
					.clone();

			DataPointCollection collection = series.getDataPointCollection(
					minIndex, maxIndex);
			series.setDataPointCollection(collection);

			return series;
		}

		throw new UnsupportedOperationException(
				"This operation is currently not supported in this release.");
	}

	/**
	 * Provides a shallow copy of the object. The name of the series is renamed
	 * to be "Copy of [series]". No additional attributes are copied in the
	 * clone.
	 * 
	 * @return Object
	 * @roseuid 425E753F0069
	 */
	public abstract Object clone();

	class MyVetoableChangeListener implements VetoableChangeListener,
			Serializable {

		private static final long serialVersionUID = -2309201678768104774L;

		public void vetoableChange(PropertyChangeEvent evt)
				throws PropertyVetoException {
			if (evt.getPropertyName().equals(
					CalendarFrequency.TIMEZONE_PROPERTY)) {
				throw new PropertyVetoException(
						"The Series must be empty before changing the timezone of the assigned calendar.",
						evt);
			}
		}
	};

}
