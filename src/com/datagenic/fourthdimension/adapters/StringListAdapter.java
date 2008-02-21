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
package com.datagenic.fourthdimension.adapters;

import org.apache.commons.collections.primitives.*;
import java.util.*;
import com.datagenic.fourthdimension.ListAdapter;

/**
 * The BooleanListAdapter is an implementation of the ListAdapter storing only String data
 */
public class StringListAdapter extends ListAdapter implements java.io.Serializable {

    private static final long serialVersionUID = 1008;
    
    /**
     * The datatype which this class is providing an adapter to
     */
    private ArrayList list;
    
    /** Creates a new instance of StringListAdapter with an empty internal string array*/
    public StringListAdapter() {
        super();
        list = new ArrayList();
    }
    
    /** Creates a new instance of StringListAdapter with an initial internal string array
     * @param data intial data to populate the internal string array
     */
    public StringListAdapter(String[] data) {
        super();
        list = new ArrayList(Arrays.asList(data));
    }
    
    /**
     * Appends the specified double value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(double value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified double value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, double value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index,String.valueOf(value));
    }
    
    /**
     * Replaces the double value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, double value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public double getDouble(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Double.parseDouble(this.getString(index));
    }
    
    /**
     * Appends the specified float value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(float value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified float value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, float value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index,String.valueOf(value));
    }
    
    /**
     * Replaces the float value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, float value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public float getFloat(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Float.parseFloat(this.getString(index));
    }
    
    /**
     * Appends the specified long value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(long value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified long value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, long value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index,String.valueOf(value));
    }
    
    /**
     * Replaces the long value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, long value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public long getLong(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Long.parseLong(this.getString(index));
    }
    
    /**
     * Appends the specified int value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified int value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, int value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index,String.valueOf(value));
    }
    
    /**
     * Replaces the int value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, int value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public int getInt(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Integer.parseInt(this.getString(index));
    }
    
    /**
     * Appends the specified short value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(short value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified short value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, short value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index,String.valueOf(value));
    }
    
    /**
     * Replaces the short value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, short value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public short getShort(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Short.parseShort(this.getString(index));
    }
    
    /**
     * Appends the specified byte value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(byte value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified byte value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, byte value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index, String.valueOf(value));
    }
    
    /**
     * Replaces the byte value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, byte value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public byte getByte(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Byte.parseByte(this.getString(index));
    }
    
    /**
     * Appends the specified Object value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(Object value) throws UnsupportedOperationException,IllegalArgumentException {
        if (value == null) {
            list.add(null);
        } else if (value instanceof String) {
            list.add(value);
        } else {
            list.add(value.toString());
        }
    }
    
    /**
     * Appends the specified Object value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, Object value) throws UnsupportedOperationException,IllegalArgumentException {
        
        if (value == null) {
            list.add(index,null);
        } else if (value instanceof String) {
            list.add(index,value);
        } else {
            list.add(index, value.toString());
        }
    }
    
    /**
     * Replaces the Object value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, Object value) throws UnsupportedOperationException,IllegalArgumentException {
        
        if (value == null) {
            list.set(index,null);
        } else if (value instanceof String) {
            list.set(index,value);
        } else {
            list.set(index,value.toString());
        }
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public Object getObject(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return list.get(index);
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public String getString(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return list.get(index).toString();
    }
    
    /**
     * Appends the specified boolean value to the end of the list.<p>
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(boolean value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(String.valueOf(value));
    }
    
    /**
     * Appends the specified boolean value at the specified location.  Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void add(int index, boolean value) throws UnsupportedOperationException,IllegalArgumentException {
        list.add(index,String.valueOf(value));
    }
    
    /**
     * Replaces the boolean value at the specified location.  <p>
     * @param index the index at which to element is to be replaced
     * @param value the element that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void set(int index, boolean value) throws UnsupportedOperationException,IllegalArgumentException {
        list.set(index,String.valueOf(value));
    }
    
    /**
     * Returns the value of the element at the specified position.  <p>
     * @param index the index at which to element is to be replaced
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     * @return the stored value
     */
    public boolean getBoolean(int index) throws UnsupportedOperationException,IllegalArgumentException {
        return Boolean.parseBoolean(this.getString(index));
    }
    
    /**
     * Inserts all the elements of the ListAdapter starting at the specified location. Shifts the element at that position ( if any ) and any subsequent elements to the right increasing their indicies.<p>
     * @param index the index at which to insert the element
     * @param list the ListAdapter that is to be stored
     * @throws java.lang.UnsupportedOperationException - when this operation is not supported.
     * @throws java.lang.IllegalArgumentException - may be thrown if some aspect of the specified element prevents it from being added.
     */
    public void addAll(int index, ListAdapter list) throws UnsupportedOperationException,IllegalArgumentException {
        Collection collection = Arrays.asList((String[])(list.getStringList().toArray(new String[0])));
        this.list.addAll(index, collection );
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public DoubleList getDoubleList() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Data type not supported. [" + DoubleList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public ByteList getByteList() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Data type not supported. [" + BooleanList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public BooleanList getBooleanList() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Data type not supported. [" + BooleanList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public FloatList getFloatList() throws UnsupportedOperationException  {
        throw new UnsupportedOperationException("Data type not supported. [" + BooleanList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public IntList getIntList() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Data type not supported. [" + ShortList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public LongList getLongList() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Data type not supported. [" + IntList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public ShortList getShortList() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Data type not supported. [" + ByteList.class.getName());
    }
    
    /**
     * Provides access to the underlying List that this adapter is using
     * @return the underlying primitive array implementation
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public java.util.ArrayList getStringList() throws UnsupportedOperationException {
        return list;
    }
    
    /**
     * Returns the number of elements in the list
     * @return int the size of the list
     */
    public int size() {
        return list.size();
    }
    
    /**
     * Returns a ListAdapter of the elements between the specified fromIndex and toIndex exclusive.
     * @param fromIndex the starting index inclusive
     * @param toIndex the end index exclusive
     * @return A ListAdapter of the same type containing elements between the start and end indexes
     */
    public ListAdapter subList(int fromIndex, int toIndex) {
        return new StringListAdapter((String[])list.subList(fromIndex,toIndex).toArray(new String[0]));
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * If the String cannot be converted to a double a Double.NaN is used in its place
     * @return double[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public double[] toDoubleArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        double[] d = new double[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                d[i] = Double.parseDouble(tmp[i]);
            } catch (NumberFormatException nfe) {
                d[i] = Double.NaN;
            }
        }
        
        return d;
        
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * If the String cannot be converted to a float a Float.NaN is used in its place
     * @return float[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public float[] toFloatArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        float[] d = new float[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                d[i] = Float.parseFloat(tmp[i]);
            } catch (NumberFormatException nfe) {
                d[i] = Float.NaN;
            }
        }
        
        return d;
        
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * If the String cannot be converted to a long a zero is used in its place
     * @return long[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public long[] toLongArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        long[] d = new long[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                d[i] = Long.parseLong(tmp[i]);
            } catch (NumberFormatException nfe) {
                d[i] = 0;
            }
        }
        
        return d;
        
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * If the String cannot be converted to a int a zero is used in its place
     * @return int[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public int[] toIntArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        int[] d = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                d[i] = Integer.parseInt(tmp[i]);
            } catch (NumberFormatException nfe) {
                d[i] = 0;
            }
        }
        
        return d;
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * If the String cannot be converted to a short a zero is used in its place
     * @return short[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public short[] toShortArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        short[] d = new short[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                d[i] = Short.parseShort(tmp[i]);
            } catch (NumberFormatException nfe) {
                d[i] = (short)0;
            }
        }
        
        return d;

    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * If the String cannot be converted to a byte a zero is used in its place
     * @return byte[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public byte[] toByteArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        byte[] d = new byte[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            try {
                d[i] = Byte.parseByte(tmp[i]);
            } catch (NumberFormatException nfe) {
                d[i] = (byte)0;
            }
        }
        
        return d;
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return String[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public String[] toStringArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        return tmp;
        
    }
    
    /**
     * Converts the contents of the ListAdapter into the corresponding primitive array
     * @return boolean[] containing the contents of the ListAdapter
     * @throws java.lang.UnsupportedOperationException if this operation is not supported
     */
    public boolean[] toBooleanArray() throws UnsupportedOperationException {
        
        String[] tmp = (String[])list.toArray(new String[0]);
        
        boolean[] d = new boolean[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            d[i] = tmp[i].equalsIgnoreCase("true");
        }
        
        return d;
    }
    
    /**
     * Creates and returns a copy of this object
     * @return a clone of this instance
     */
    public Object clone() {
        StringListAdapter adapter = new StringListAdapter(this.toStringArray());
        return adapter;
    }

    /**
     * Produces a ListAdapter of the correct type with a defined size.  All the values are set to
     * default primitive values.
     * @param size defines the length of the internal storage array
     */
    public ListAdapter makeAdapter(int size) {
        String[] tmp = new String[size];
        return new StringListAdapter(tmp);
    }    
}
