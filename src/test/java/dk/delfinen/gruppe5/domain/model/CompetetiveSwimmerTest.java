package dk.delfinen.gruppe5.domain.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CompetetiveSwimmerTest {

    @Test
    void getTopFiveResults_returnsFiveFastest() {
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(null, null);

        swimmer.addTrainingResult(new TrainingResult(63, "butterfly"));
        swimmer.addTrainingResult(new TrainingResult(45, "crawl"));
        swimmer.addTrainingResult(new TrainingResult(72, "rygcrawl"));
        swimmer.addTrainingResult(new TrainingResult(50, "bryst"));
        swimmer.addTrainingResult(new TrainingResult(40, "crawl"));
        swimmer.addTrainingResult(new TrainingResult(55, "butterfly")); // denne skal ikke med

        ArrayList<TrainingResult> top5 = swimmer.getTopFiveTrainingResults();

        assertEquals(5, top5.size());
        assertEquals(40, top5.get(0).getTime());
    }
}
