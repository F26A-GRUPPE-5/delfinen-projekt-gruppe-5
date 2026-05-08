package dk.delfinen.gruppe5.adapter.out.persistence;

import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.Membership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

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
                "active",};
        String csv = "";
        for (Object field: fields) {
                csv+= field.toString() + ", ";
        }

        return csv.substring(0, csv.length() - 2);
    }
    public Member toMember(String csv) {

        String[] tokenized = csv.split(", ");

        return new Member(
                Integer.parseInt(tokenized[0]),
                tokenized[1],
                Integer.parseInt(tokenized[2]),
                Boolean.parseBoolean(tokenized[3]),
                tokenized[4],
                new ActiveMembership()
                );
    }
}
