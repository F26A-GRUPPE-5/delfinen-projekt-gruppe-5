package dk.delfinen.gruppe5.adapter.in;


import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;



// TODO: her skal blandt andet formatering være, for eksempel hvis vi vil have farver eller skal vise noget i et table
//  det kan også handle om hvad der skal vises og skjules, eller catching errors og printe dem til brugeren på en pæn måde
public class Presenter {
    MemberRepository members;

    public Presenter(MemberRepository members) {
        this.members = members;
    }

    public void printMemberList() {
        for(Member m : members.findAll()) {
            System.out.println(m);
        }
    }

    public void printSubscriptionPaidList() {
        for (Member m : members.findAll()) {

            String status;

            if (m.getHasPaid()) {
                status = "Paid";
            } else {
                status = "Unpaid";
            }

            System.out.println("""
                    medlemsId: %s
                    navn: %s
                    kontigent: %s
                    
                    
                    """. formatted(
                    m.getId(),
                    m.getName(),
                    status

            ));
        }
    }

    public void printSubscriptionUnpaidList() {
        for(Member m : members.findAll()) {
            System.out.println(m);
        }
    }


}


