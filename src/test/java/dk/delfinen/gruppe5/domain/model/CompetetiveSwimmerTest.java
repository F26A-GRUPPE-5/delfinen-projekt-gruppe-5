package dk.delfinen.gruppe5.domain.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CompetetiveSwimmerTest {

    @Test
    void getTopFiveResults_returnsFiveFastest() {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(null, null);

        swimmer.addResult(new Result(63, "butterfly"));
        swimmer.addResult(new Result(45, "crawl"));
        swimmer.addResult(new Result(72, "rygcrawl"));
        swimmer.addResult(new Result(50, "bryst"));
        swimmer.addResult(new Result(40, "crawl"));
        swimmer.addResult(new Result(55, "butterfly")); // denne skal ikke med

        ArrayList<Result> top5 = swimmer.getTopFiveResults();

        assertEquals(5, top5.size());
        assertEquals(40, top5.get(0).getTime());
    }
}
