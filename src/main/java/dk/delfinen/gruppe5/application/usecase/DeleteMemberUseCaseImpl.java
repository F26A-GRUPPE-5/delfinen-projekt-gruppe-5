package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.DeleteMemberUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.Scanner;

public class DeleteMemberUseCaseImpl implements DeleteMemberUseCase {

    private MemberRepository repository;

    public DeleteMemberUseCaseImpl(MemberRepository repository) {
        this.repository = repository;
    }


    public void execute(int id) {
        if (repository.exists(id)) {
            Member toRemove = repository.find(id).orElseThrow( () -> new RuntimeException("Member not found"));
            repository.delete(id);
            System.out.println("Du har slettet: /n");
            System.out.println();
            System.out.println(toRemove);
        } else {
            System.out.println("Intet medlem fundet med det Id.");
        }
    }
}
