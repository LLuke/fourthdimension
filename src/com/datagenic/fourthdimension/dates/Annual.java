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


public class Annual extends Monthly
{
   private static final int DEFAULT_PERIODICITY = 12;
   private static final int DEFAULT_REFERENCE_MONTH = REF_JANUARY;

   /**
   @param referenceMonth
   @roseuid 4146B7AC033C
    */
   public Annual(int referenceMonth)
   {
       super(DEFAULT_PERIODICITY,referenceMonth);
   }

   /**
   @roseuid 4146B61D032C
    */
   public Annual()
   {
       super(DEFAULT_PERIODICITY,DEFAULT_REFERENCE_MONTH);
   }

   /**
   @return java.lang.String
   @roseuid 416502A60157
    */
   public String getCalendarFrequencyName()
   {
            return "Annual(" + DateHelper.getMonthName(calendarReferenceMonth) + ")";
   }

   /**
   @return Object
   @roseuid 425E8A9F02CA
    */
   public Object clone()
   {
       return new Annual(this.calendarReferenceMonth);
   }
}
/**



Annual.Annual(int,boolean[]){
       super(DEFAULT_PERIODICITY,referenceMonth,pattern);
   }



 */
