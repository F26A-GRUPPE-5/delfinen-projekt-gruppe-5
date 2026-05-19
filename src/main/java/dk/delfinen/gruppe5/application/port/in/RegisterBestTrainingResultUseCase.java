package dk.delfinen.gruppe5.application.port.in;

public interface RegisterBestTrainingResultUseCase {
    boolean execute(int id, double time, String discipline);
}
