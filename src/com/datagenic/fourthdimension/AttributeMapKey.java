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


public interface AttributeMapKey {
    
    /**
     * The name of the series
     */
    public static String NAME = "NAME";
    
    /**
     * The description attribute of the series
     */
    public static String DESCRIPTION = "DESCRIPTION";
    
    /**
     * The data type of the series
     */
    public static String DATATYPE = "DATATYPE";
    
    /**
     * The calendar frequency of the series
     */
    public static String CALENDAR = "CALENDAR";
    
    /**
     * The date the series was created
     */
    public static String CREATED = "CREATED";
    
    /**
     * The date the series was last modified
     */
    public static String MODIFIED = "MODIFIED";
    
    /**
     * The aggregation type used when storing the data points
     */
    public static String AGGREGATION = "AGGREGATION";
    
    /**
     * The documentation associated with the series
     */
    public static final String DOCUMENTATION = "DOCUMENTATION";
    
    /**
     * The actual name of the series
     */
    public static final String TRUENAME_ATTRIBUTE = "TRUENAME";
    
    /**
     * The service used to build the series
     */
    public static final String SERVICE_NAME = "SERVICE";
    
    /**
     * The originating database for the series
     */
    public static final String DATABASE_NAME = "DATABASE";
    
}
