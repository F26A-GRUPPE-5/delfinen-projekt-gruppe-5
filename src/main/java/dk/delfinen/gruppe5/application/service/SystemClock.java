package dk.delfinen.gruppe5.application.service;

import dk.delfinen.gruppe5.application.port.in.Clock;

import java.time.Instant;
import java.time.ZonedDateTime;

public class SystemClock implements Clock {

    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
