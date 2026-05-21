package dk.delfinen.gruppe5.adapter.out.persistence;

import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;

//TODO: Dennes job er at oversætte mellem member objektet og filer, for eksempel CSV
public class FileMemberSerializer {
    public FileMemberSerializer() {
    }

    public String toCSV(Member member) {
        Object[] fields = {
                member.getId(),
                member.getName(),
                member.getBirthYear(),
                member.getIsCompetitor(),
                member.getActivity(),
                "active",
                member.getTrainerName(),
                member.getHasPaid()
        };

        StringBuilder csv = new StringBuilder();

        for (Object field : fields) {
            csv.append(field).append(", ");
        }

        return csv.substring(0, csv.length() - 2);
    }

    public Member toMember(String csv) {

        String[] t = csv.split(", ");

        Member member = new Member(
                Integer.parseInt(t[0]),
                t[1],
                Integer.parseInt(t[2]),
                Boolean.parseBoolean(t[3]),
                t[4],
                new ActiveMembership()
        );

        member.setTrainerName(t[6]);

        // ✅ THIS IS MISSING
        member.setHasPaid(Boolean.parseBoolean(t[7]));

        return member;
    }
}
