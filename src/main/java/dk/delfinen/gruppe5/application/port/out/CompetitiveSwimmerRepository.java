package dk.delfinen.gruppe5.application.port.out;

import dk.delfinen.gruppe5.domain.model.CompetitiveSwimmer;

import java.util.List;

public interface CompetitiveSwimmerRepository {
     void save(CompetitiveSwimmer swimmer);
     CompetitiveSwimmer find(int id);
     List<CompetitiveSwimmer> findAll();

}
