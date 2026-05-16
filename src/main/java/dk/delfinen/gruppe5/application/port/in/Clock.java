package dk.delfinen.gruppe5.application.port.in;

import java.time.ZonedDateTime;

public interface Clock {
    ZonedDateTime now();
}

