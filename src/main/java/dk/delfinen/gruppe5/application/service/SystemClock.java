package dk.delfinen.gruppe5.application.service;


import dk.delfinen.gruppe5.application.port.in.ClockObj;


import java.time.ZonedDateTime;

public class SystemClock implements ClockObj {

    public SystemClock() {
    }

    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
