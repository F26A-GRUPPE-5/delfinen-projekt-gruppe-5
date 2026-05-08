package dk.delfinen.gruppe5.adapter.in;

import dk.delfinen.gruppe5.application.usecase.MemberList;
import dk.delfinen.gruppe5.domain.model.Member;

import static dk.delfinen.gruppe5.adapter.in.Controller.members;

// TODO: her skal blandt andet formatering være, for eksempel hvis vi vil have farver eller skal vise noget i et table
//  det kan også handle om hvad der skal vises og skjules, eller catching errors og printe dem til brugeren på en pæn måde
public class Presenter {
    public void printMemberlist() {
        for(Member m : members.getMembers()) {
            System.out.println(m);
        }
    }
}
