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


public abstract class FourthDimensionNumber extends FourthDimensionValue 
{
   
   /**
   @return com.datagenic.fourthdimension.DataObject
   @roseuid 423F0D74038B
    */
   public DataObject getDataObject() 
   {
       return this;    
   }
   
   /**
   @param minIndex
   @param maxIndex
   @return com.datagenic.fourthdimension.DataObject
   @roseuid 423F113E00FA
    */
   public DataObject getDataObject(long minIndex, long maxIndex) 
   {
    return getDataObject();    
   }
   
   /**
   @return double
   @roseuid 429D664D03AE
    */
   public abstract double getDoubleValue();
   
   /**
   @return float
   @roseuid 429D665E0091
    */
   public abstract float getFloatValue();
   
   /**
   @return long
   @roseuid 429D666B00A4
    */
   public abstract long getLongValue();
   
   /**
   @return int
   @roseuid 429D667700FB
    */
   public abstract int getIntValue();
   
   /**
   @return short
   @roseuid 429D66820223
    */
   public abstract short getShortValue();
   
   /**
   @return byte
   @roseuid 429D66910012
    */
   public abstract byte getByteValue();
   
   /**
   @return java.lang.String
   @roseuid 429D669D007E
    */
   public abstract String getStringValue();
}
//FourthDimensionNumber.FourthDimensionNumber(java.lang.Object){
//       super(value);    
//   }
/**


FourthDimensionNumber.getFloatValue()
FourthDimensionNumber.getLongValue()
FourthDimensionNumber.getIntValue()
FourthDimensionNumber.getByteValue()
FourthDimensionNumber.getStringValue()
FourthDimensionNumber.getShortValue()
FourthDimensionNumber.getDoubleValue()
 
 
 */
