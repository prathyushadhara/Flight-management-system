package com.dxctraining.schedulemodule.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	long toMillis(LocalDateTime arrivalTime){
        ZonedDateTime zonedDateTime=ZonedDateTime.of(arrivalTime, ZoneId.systemDefault());
       long millis= zonedDateTime.toInstant().toEpochMilli();
       return millis;
    }

    public LocalDateTime toDateTime(long millis){
        System.out.println("inside todatetime="+millis);
        LocalDateTime datetime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(millis),
                       TimeZone.getDefault().toZoneId());
        return datetime;
    }

}
