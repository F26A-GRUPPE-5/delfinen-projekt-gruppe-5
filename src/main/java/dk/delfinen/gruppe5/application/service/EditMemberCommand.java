package dk.delfinen.gruppe5.application.service;

import java.util.Optional;

public class EditMemberCommand {
    public int memberId;
    public Optional<String> name = Optional.empty();
    public Optional<Integer> birthYear = Optional.empty();
    public Optional<Boolean> isCompetitor = Optional.empty();
    public Optional<String> activity = Optional.empty();
    public Optional<Boolean> isActiveMembership = Optional.empty();
}