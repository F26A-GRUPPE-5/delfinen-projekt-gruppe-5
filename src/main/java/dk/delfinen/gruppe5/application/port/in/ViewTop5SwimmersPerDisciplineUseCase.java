package dk.delfinen.gruppe5.application.port.in;

import dk.delfinen.gruppe5.domain.model.Member;

import java.util.List;

public interface ViewTop5SwimmersPerDisciplineUseCase {

    List<Member> execute(String discipline);
}