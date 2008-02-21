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
package com.datagenic.fourthdimension.dates;


public class Business extends Daily
{
   public static boolean[] BUSINESS_PATTERN = {
   false,
   true,
   true,
   true,
   true,
   true,
   false
   };

   /**
   @roseuid 414708AA02BF
    */
   public Business()
   {
       super(BUSINESS_PATTERN);
   }

   /**
   @return java.lang.String
   @roseuid 416505EC033C
    */
   public String getCalendarFrequencyName()
   {
       StringBuffer buffer = new StringBuffer();
       for (int i=0;i<BUSINESS_PATTERN.length;i++) {
           buffer.append(BUSINESS_PATTERN[i]);
           buffer.append(" ");
       }
            return "Business(" + buffer.substring(0,buffer.length() - 1) + ")";
   }

   /**
   @return Object
   @roseuid 425E8B0D0049
    */
   public Object clone()
   {
       return new Business();
   }
}
