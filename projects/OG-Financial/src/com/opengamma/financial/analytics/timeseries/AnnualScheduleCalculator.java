/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.timeseries;

import java.util.ArrayList;
import java.util.List;

import javax.time.calendar.LocalDate;
import javax.time.calendar.Period;
import javax.time.calendar.ZonedDateTime;

import org.apache.commons.lang.Validate;

/**
 * 
 */
public class AnnualScheduleCalculator extends Schedule {

  @Override
  public LocalDate[] getSchedule(final LocalDate startDate, final LocalDate endDate, final boolean fromEnd, final boolean generateRecursive) {
    Validate.notNull(startDate, "start date");
    Validate.notNull(endDate, "end date");
    Validate.isTrue(startDate.isBefore(endDate) || startDate.equals(endDate));
    if (startDate.equals(endDate)) {
      return new LocalDate[] {startDate};
    }
    final List<LocalDate> dates = new ArrayList<LocalDate>();
    if (fromEnd) {
      LocalDate date = endDate;
      int i = 1;
      while (!date.isBefore(startDate)) {
        dates.add(date);
        date = generateRecursive ? date.minus(Period.ofYears(1)) : endDate.minus(Period.ofYears(i++));
      }
      return getReversedDates(dates);
    }
    LocalDate date = startDate;
    int i = 1;
    while (!date.isAfter(endDate)) {
      dates.add(date);
      date = generateRecursive ? date.plus(Period.ofYears(1)) : startDate.plus(Period.ofYears(i++));
    }
    return dates.toArray(EMPTY_LOCAL_DATE_ARRAY);
  }

  @Override
  public ZonedDateTime[] getSchedule(final ZonedDateTime startDate, final ZonedDateTime endDate, final boolean fromEnd, final boolean generateRecursive) {
    Validate.notNull(startDate, "start date");
    Validate.notNull(endDate, "end date");
    Validate.isTrue(startDate.isBefore(endDate) || startDate.equals(endDate));
    if (startDate.equals(endDate)) {
      return new ZonedDateTime[] {startDate};
    }
    final List<ZonedDateTime> dates = new ArrayList<ZonedDateTime>();
    if (fromEnd) {
      ZonedDateTime date = endDate;
      int i = 1;
      while (!date.isBefore(startDate)) {
        dates.add(date);
        date = generateRecursive ? date.minus(Period.ofYears(1)) : endDate.minus(Period.ofYears(i++));
      }
      return getReversedDates(dates);
    }
    ZonedDateTime date = startDate;
    int i = 1;
    while (!date.isAfter(endDate)) {
      dates.add(date);
      date = generateRecursive ? date.plus(Period.ofYears(1)) : startDate.plus(Period.ofYears(i++));
    }
    return dates.toArray(EMPTY_ZONED_DATE_TIME_ARRAY);
  }

}
