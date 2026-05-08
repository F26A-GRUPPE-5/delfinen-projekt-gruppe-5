package dk.delfinen.gruppe5;

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
            if (m.hasPaid()) {
                payment.add(m);
            }
        }
        return payment;
    }
    public ArrayList<Member> getUnpaidMembers() {
        ArrayList<Member> unpaid = new ArrayList<>();

        for (Member m : memberList.getMembers()) {
            if (!m.hasPaid()) {
                unpaid.add(m);
            }
        }
        return unpaid;
    }
    }
