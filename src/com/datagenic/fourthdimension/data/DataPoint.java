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

import java.io.Serializable;
import java.util.Date;

/**
A DataPoint is a convenience class which encapsulates the three properties that 
make up an observation - a value, a status and an index.  DataPoint objects are 
only expected to have a relatively short life time.  They are generated on 
demand when the appropriate method is invoked from the DataPointCollection 
class.
 */
public class DataPoint implements Serializable 
{
   
   /**
   The position of the DataPoint either in a time dimension or a series position.  
   When the index is a time dimension it can be directly translated into a 
   java.util.Date.
    */
   private long index;
   
   /**
   The value that is stored at a specific index position.  Getters provide 
   convienance method to convert to object to different numeric types.  If no 
   conversion is required, then use <code>getValue()</code> to retrieve the object.
    */
   private Object value;
   
   /**
   This status flag indicates that status of the value stored at the index point.  
   Various status flags exist.
   <DL>
   <DT>
   VALID - indicates that the value stored at this index position is present and 
   accurate.
   </DT>
   <DT>
   CERTIFIED_VALID - indicates that the value stored at this index position is 
   VALID and has been verified.
   </DT>
   <DT>
   MISSING - indicates that either the valid was not available when this DataPoint 
   was created or has since been deleted.
   </DT>
   </DL>
    */
   private byte status;
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive boolean <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The boolean value stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, boolean value, byte status) 
   {
       this.index = index;
       this.value = new Boolean(value);
       this.status = status;       
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the String <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The String stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, String value, byte status) 
   {
       this.index = index;
       this.value = value;
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive byte <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The byte value stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, byte value, byte status) 
   {
       this.index = index;
       this.value = new Byte(value);
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive short <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The short value stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, short value, byte status) 
   {
       this.index = index;
       this.value = new Short(value);
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive int <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The int stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, int value, byte status) 
   {
       this.index = index;
       this.value = new Integer(value);
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive long <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The long value stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, long value, byte status) 
   {
       this.index = index;
       this.value = new Long(value);
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive double <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The double value stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, double value, byte status) 
   {
       this.index = index;
       this.value = new Double(value);
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the primitive float <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The float stored at the index position. 
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, float value, byte status) 
   {
       this.index = index;
       this.value = new Float(value);
       this.status = status;    
   }
   
    /**
     * Constructs a newly allocated <code>DataPoint</code> object that
     * represents the <code>value</code> argument.
     * @param index - The position of the DataPoint either in a time dimension or as a 
     * series index
     * @param value - The object stored at the index position. This may be null.
     * @param status - The status of the <code>value</code> stored at the 
     * <code>index</code> position.
    */
   public DataPoint(long index, Object value, byte status) 
   {
        this.index = index;
        this.value = value;
        this.status = status;    
   }
   
   /**
   Returns the <code>value</code> as a Boolean object.  The Boolean object returned 
   may be a null value depending on the <code>status</code> field value.
   @return java.lang.Boolean
   @roseuid 4146F40302DE
    */
   public Boolean getBooleanValue() 
   {
       if (value == null)
           return null;

        return new Boolean(value.toString());    
   }
   
   /**
   Returns the <code>value</code> as a Double object.  The Double object returned 
   may be a null value depending on the <code>status</code> field value.
   @return Double
   @roseuid 4146F40D004E
    */
   public Double getDoubleValue() 
   {
       if (value == null)
           return null;

        if (value instanceof Number) {
            return new Double(( (Number) value).doubleValue());
        } else {
            return new Double(value.toString());
        }    
   }
   
   /**
   Returns the <code>value</code> as a Float object.  The Float object returned may 
   be a null value depending on the <code>status</code> field value.
   @return Float
   @roseuid 4146F41C004E
    */
   public Float getFloatValue() 
   {
       if (value == null)
           return null;

        if (value instanceof Number) {
            return new Float(( (Number) value).floatValue());
        } else {
            return new Float(value.toString());
        }    
   }
   
   /**
   Returns the <code>value</code> as a Integer object.  The Integer object returned 
   may be a null value depending on the <code>status</code> field value.
   @return java.lang.Integer
   @roseuid 4146F42403B9
    */
   public Integer getIntegerValue() 
   {
       if (value == null)
           return null;

        if (value instanceof Number) {
            return new Integer(( (Number) value).intValue());
        } else {
            return new Integer(value.toString());

        }    
   }
   
   /**
   Returns the <code>value</code> as a Long object.  The Long object returned may 
   be a null value depending on the <code>status</code> field value.
   @return java.lang.Long
   @roseuid 4146F4510128
    */
   public Long getLongValue() 
   {
       if (value == null)
           return null;

        if (value instanceof Number) {
            return new Long(( (Number) value).longValue());
        } else {
            return new Long(value.toString());
        }    
   }
   
   /**
   Returns the <code>value</code> as a Short object.  The Short object returned may 
   be a null value depending on the <code>status</code> field value.
   @return java.lang.Short
   @roseuid 4146F460031C
    */
   public Short getShortValue() 
   {
       if (value == null)
           return null;

        if (value instanceof Number) {
            return new Short(( (Number) value).shortValue());
        } else {
            return new Short(value.toString());
        }    
   }
   
   /**
   Returns the <code>value</code> as a String object.  The String object returned 
   may be a null value depending on the <code>status</code> field value.
   @return java.lang.String
   @roseuid 4146F4680261
    */
   public String getStringValue() 
   {
       if (value == null)
           return null;

        return value.toString();    
   }
   
   /**
   Returns the <code>value</code> as a Date object.  The Date object returned may 
   be a null value depending on the <code>status</code> field value.
   @return java.util.Date
   @roseuid 4146F47E0109
    */
   public Date getDateValue() 
   {
       if (value == null)
           return null;

        if (value instanceof Number) {
            return new Date( ( (Number) value).longValue());
        } else if (value instanceof Date) {
            return (Date) value;
        }
        return null;    
   }
   
   /**
   Returns the index position for this DataPoint. The index is either a value 
   indicating a position in a time dimension or a position in a series.
   @return long
   @roseuid 4146F4A40167
    */
   public long getIndex() 
   {
        return index;    
   }
   
   /**
   In a time series the <code>index</code> represents the number of milliseconds 
   since 1970, which is the same logic used when constructing a java.util.Date.  
   This method will simply construct a java date based on the value of the 
   <code>index</code>.
   @return java.util.Date
   @roseuid 4162694D037A
    */
   public Date getIndexAsJavaDate() 
   {
    return new java.util.Date(getIndex());    
   }
   
   /**
   Returns the value stored at the index position.  This value may be null.
   @return Object
   @roseuid 4146F47802EE
    */
   public Object getValue() 
   {
        return value;    
   }
   
   /**
   Returns the <code>status</code> of the <code>value</code> stored at the 
   <code>index</code> position.
   @return byte
   @roseuid 4146F4B0036B
    */
   public byte getStatus() 
   {
        return status;    
   }
   
   /**
    * Indicates whether some other object is "equal to" this one
    * @param   obj   the reference object with which to compare.
    * @return  <code>true</code> if this object is the same as the obj
    *          argument; <code>false</code> otherwise.
    */
   public boolean equals(Object object) 
   {
       if ((object instanceof DataPoint) == false){
           return false;
       }

       DataPoint point = (DataPoint)object;
       if (point.getIndex() == index && point.getValue() == value && point.getStatus() == status)
           return true;
       else
           return false;    
   }
   
   /**
    * Returns a string representation of the object.
    * @return a string representation of the object.
    */
   public String toString() 
   {
       return "[Index:" + index + " IndexDate:" + getIndexAsJavaDate() + " Value:" + value + " Status:" + DataPointStatus.getStatusText(status) + "]" ;    
   }
   
   /**
    * Adds by C.Moeller
    */
   
   public void setValue(Object value) {
	   
	   this.value = value;
   }
   
   public void setStatus(byte status) {
	   
	   this.status= status;
   }
   
}
