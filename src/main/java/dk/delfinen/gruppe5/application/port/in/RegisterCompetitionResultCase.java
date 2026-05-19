package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.domain.model.Discipline;

public interface RegisterCompetitionResultCase {
    void execute (int id, Discipline discipline, int placement, String competition, String date);
}
