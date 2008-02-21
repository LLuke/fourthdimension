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

import java.io.Serializable;
import com.datagenic.fourthdimension.dates.CalendarFrequency;

/**
Provides basic information pertaining to the series.  No series data is stored 
in this representation.
 */
public class SeriesInfo implements DataObjectInfo, Serializable 
{
   private long firstIndex = 0;
   private long lastIndex = 0;
   private AttributeMap attributes;
   
   /**
   Creates a representation of a series without the overhead of storing the 
   internal data.
   @param firstIndex - The min index of the series
   @param lastIndex - The max index of the series
   @param attributes - The additional attributes of the series
   @roseuid 4162CEB503B9
    */
   public SeriesInfo(long firstIndex, long lastIndex, AttributeMap attributes) 
   {
       this.firstIndex = firstIndex;
       this.lastIndex = lastIndex;
       this.attributes = attributes;    
   }
   
   /**
   Indicates the min index of the series
   @return long
   @roseuid 4162CE7A0232
    */
   public long getMinIndex() 
   {
    return firstIndex;    
   }
   
   /**
   Returns the max index of the series
   @return long
   @roseuid 4162CE8B01A5
    */
   public long getMaxIndex() 
   {
    return lastIndex;    
   }
   
   /**
   Returns the calendar frequency of the series
   @return com.datagenic.fourthdimension.dates.CalendarFrequency
   @roseuid 4162CE8F038A
    */
   public CalendarFrequency getCalendar() 
   {
       Object obj = null;
       try {
           obj = attributes.get(AttributeMapKey.CALENDAR);
           return (CalendarFrequency)obj;
       } catch (ClassCastException cce) {
           throw new FourthDimensionRuntimeException("Expected a CalendarFrequency but got a " + obj.getClass().getName() + " instead.");
       }    
   }
   
   /**
   Returns the name of the series
   @return java.lang.String
   @roseuid 4162CEA0003E
    */
   public String getName() 
   {
       Object obj = null;
       try {
           obj = attributes.get(AttributeMapKey.NAME);
           return (String)obj;
       } catch (ClassCastException cce) {
           throw new FourthDimensionRuntimeException("Expected a String but got a " + obj.getClass().getName() + " instead.");
       }    
   }
   
   /**
   Returns the description of the series
   @return java.lang.String
   @roseuid 4164FCC9031C
    */
   public String getDescription() 
   {
       Object obj = null;
       try {
           obj = attributes.get(AttributeMapKey.DESCRIPTION);
           return (String)obj;
       } catch (ClassCastException cce) {
           throw new FourthDimensionRuntimeException("Expected a String but got a " + obj.getClass().getName() + " instead.");
       }    
   }
   
   /**
   Returns the attributes of the series.  This is useful if additional/custom 
   attributes have been stored and are not accessable via the class get accessors.
   @return AttributeMap
   @roseuid 4164FD7F003E
    */
   public AttributeMap getAttributes() 
   {
    return attributes;    
   }
}
