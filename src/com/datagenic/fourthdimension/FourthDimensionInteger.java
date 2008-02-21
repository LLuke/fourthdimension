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


public class FourthDimensionInteger extends FourthDimensionNumber
{
   private int value;

   /**
   @param value
   @roseuid 4226F2DC029F
    */
   public FourthDimensionInteger(int value)
   {
       this.value = value;
   }

   /**
   Access method for the value property.

   @return   the current value of the value property
    */
   public int getValue()
   {
      return value;
   }

   public double getDoubleValue() {
       return (double)value;
   }
   public float getFloatValue() {
       return (float)value;
   }
   public long getLongValue() {
       return (long)value;
   }
   public int getIntValue() {
       return (int)value;
   }
   public short getShortValue() {
       return (short)value;
   }
   public byte getByteValue() {
       return (byte)value;
   }
   public String getStringValue() {
       return Integer.toString(value);
   }
}
