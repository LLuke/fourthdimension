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

import com.datagenic.fourthdimension.adapters.BooleanListAdapter;
import com.datagenic.fourthdimension.adapters.ByteListAdapter;
import com.datagenic.fourthdimension.adapters.DoubleListAdapter;
import com.datagenic.fourthdimension.adapters.FloatListAdapter;
import com.datagenic.fourthdimension.adapters.IntListAdapter;
import com.datagenic.fourthdimension.adapters.LongListAdapter;
import com.datagenic.fourthdimension.adapters.ShortListAdapter;
import com.datagenic.fourthdimension.adapters.StringListAdapter;
import java.io.Serializable;
import org.apache.commons.collections.primitives.*;
import java.util.ArrayList;



/**
 * The ListAdapter is used to encapsulate access to underlying primitive data arrays.  
 * As java.util.ArrayList only works with Object datatypes, primitive data types don't have
 * a similar implementation class within the JDK.  Instead an OpenSource solution called
 * Apache Commons is used.  This library provides a ArrayList like features to all the
 * remaining primitive datatypes.
 * Apache Commons performs better than simply using primitive arrays as blocks of data can
 * be preallocated space on inserts. The preallocation prevents unnecessary calls to
 * the System.arrayCopy method.
 * @version 1.2 Revised Apache Commons usage.
 */
public abstract class ListAdapter implements Serializable {
    private static final long serialVersionUID = 2;
    
    
    /**
     * Appends the specified float value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(float value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified double value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(double value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified long value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(long value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified int value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified short value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(short value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified byte value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(byte value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified Object value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(Object value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified boolean value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(boolean value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified float value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, float value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified double value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, double value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified long value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, long value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified int value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, int value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified short value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, short value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified byte value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, byte value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified Object value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, Object value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Appends the specified boolean value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void add(int index, boolean value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Inserts all the elements of the ListAdapter starting at the specified location. Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param list the ListAdapter that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void addAll(int index, ListAdapter list) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the float value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, float value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the double value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, double value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the long value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, long value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the int value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, int value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the short value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, short value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the byte value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, byte value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the Object value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, Object value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Replaces the boolean value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public abstract void set(int index, boolean value) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract float getFloat(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract double getDouble(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract long getLong(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract int getInt(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract short getShort(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract byte getByte(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract Object getObject(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract String getString(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public abstract boolean getBoolean(int index) throws UnsupportedOperationException,IllegalArgumentException;
    
    /**
     * Returns the number of elements in the list
     * @return int the size of the list
     */
    public abstract int size();
    
    /**
     * Returns a ListAdapter of the elements between the specified fromIndex and toIndex exclusive.
     * @param fromIndex the starting index inclusive
     * @param toIndex the end index exclusive
     */
    public abstract ListAdapter subList(int fromIndex,int toIndex);
    

    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract FloatList getFloatList() throws UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract DoubleList getDoubleList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract LongList getLongList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract IntList getIntList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract ShortList getShortList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract ByteList getByteList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract ArrayList getStringList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract BooleanList getBooleanList() throws java.lang.UnsupportedOperationException;
    
    /**
     * Produces a ListAdapter of the correct type with a defined size.  All the values are set to
     * default primitive values
     * @param size defines the length of the internal storage array
     */
    public abstract ListAdapter makeAdapter(int size);
    
    /**
     * Creates a ListAdapter which is capable of holding double values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(double[] values) {
        
        DoubleListAdapter adapter = new DoubleListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding float values 
     * @param values the data used to populate the adapter     
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(float[] values) {
        FloatListAdapter adapter = new FloatListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding long values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(long[] values) {
        LongListAdapter adapter = new LongListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding int values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(int[] values) {
        IntListAdapter adapter = new IntListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding short values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(short[] values) {
        ShortListAdapter adapter = new ShortListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding byte values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(byte[] values) {
        ByteListAdapter adapter = new ByteListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding String values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */
    public static ListAdapter makeAdapter(String[] values) {
        StringListAdapter adapter = new StringListAdapter(values);
        return adapter;
    }
    
    /**
     * Creates a ListAdapter which is capable of holding boolean values 
     * @param values the data used to populate the adapter
     * @return a populated ListAdapter
     */

    public static ListAdapter makeAdapter(boolean[] values) {
        BooleanListAdapter adapter = new BooleanListAdapter(values);
        return adapter;
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return double[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract double[] toDoubleArray() throws UnsupportedOperationException;
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return float[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract float[] toFloatArray() throws UnsupportedOperationException;
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return int[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract int[] toIntArray() throws UnsupportedOperationException;
    
     /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return long[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
      */
    public abstract long[] toLongArray() throws UnsupportedOperationException;
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return short[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract short[] toShortArray() throws UnsupportedOperationException;
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return byte[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract byte[] toByteArray() throws UnsupportedOperationException;
    
     /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return String[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
      */
    public abstract String[] toStringArray() throws UnsupportedOperationException;
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return boolean[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public abstract boolean[] toBooleanArray() throws UnsupportedOperationException;
    
    /**
     * Creates and returns a copy of this object
     * @return a clone of this instance
     */
    public abstract Object clone();
}
