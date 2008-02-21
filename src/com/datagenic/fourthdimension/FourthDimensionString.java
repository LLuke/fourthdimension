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


public class FourthDimensionString extends FourthDimensionObject
{

   /**
   @param value
   @roseuid 4226F0FD030D
    */
   public FourthDimensionString(String value)
   {
       super(value);
   }

   /**
   @return com.datagenic.fourthdimension.DataObject
   @roseuid 423F0D8B01E5
    */
   public DataObject getDataObject()
   {
    return this;
   }

   /**
   @param minIndex
   @param maxIndex
   @return com.datagenic.fourthdimension.DataObject
   @roseuid 423F116E0281
    */
   public DataObject getDataObject(long minIndex, long maxIndex)
   {
    return getDataObject();
   }

   /**
   Access method for the value property.
   @return   the current value of the value property
   @roseuid 426E4ACA0177
    */
   public String getStringValue()
   {
    return getValue().toString();
   }
}
//String FourthDimensionString.getValue(){
//      return value;
//   }
