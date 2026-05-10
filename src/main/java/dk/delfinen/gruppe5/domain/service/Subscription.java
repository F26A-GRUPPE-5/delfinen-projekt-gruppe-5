package dk.delfinen.gruppe5.domain.service;

import dk.delfinen.gruppe5.application.usecase.MemberList;
import dk.delfinen.gruppe5.domain.model.Member;

import java.util.ArrayList;

public class Subscription {
    private MemberList memberList;

    public Subscription(MemberList memberList) {
        this.memberList = memberList;
    }
    public double totalSubscription() {
        double total = 0;

        for (Member m : memberList.getMembers()) {
            total += m.getFee();
        }
        return total;
    }
    public ArrayList<Member> getPaidMembers() {
        ArrayList<Member> payment = new ArrayList<>();

        for (Member m : memberList.getMembers()) {
            if (m.getHasPaid()) {
                payment.add(m);
            }
        }
        return payment;
    }
    public ArrayList<Member> getUnpaidMembers() {
        ArrayList<Member> unpaid = new ArrayList<>();

        for (Member m : memberList.getMembers()) {
            if (!m.getHasPaid()) {
                unpaid.add(m);
            }
        }
        return unpaid;
    }
}
