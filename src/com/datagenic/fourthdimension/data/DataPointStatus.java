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


public class DataPointStatus 
{
   public static final byte VALID = 0;
   public static final byte MISSING = 1;
   public static final byte CERTIFIED_VALID = 2;
   
   /**
   Indicates that the status value represents a missing value.
   @param status - Status value to be checked
   @return boolean
   @roseuid 414980DA038A
    */
   public static boolean isMissing(byte status) 
   {
        return status == MISSING;    
   }
   
   /**
   Indicates that the status value represents a valid value.
   @param status - The status value to be checked.
   @return boolean
   @roseuid 414980E40148
    */
   public static boolean isValid(byte status) 
   {
        return status == VALID;    
   }
   
   /**
   Indicates that the status value represents a certified value.
   @param status - The status value to be checked.
   @return boolean
   @roseuid 414980F0036B
    */
   public static boolean isCertifiedValid(byte status) 
   {
        return status == CERTIFIED_VALID;    
   }
   
   /**
   Provides a textual representation of the status value
   @param status - The status value used in the text resolution
   @return java.lang.String
   @roseuid 415C027201F4
    */
   public static String getStatusText(byte status) 
   {
        switch (status) {
            case MISSING:
                return "Missing";
            case VALID:
                return "Valid";
            case CERTIFIED_VALID:
                return "Certified";
            default:
                return "Unknown";
        }    
   }
}
