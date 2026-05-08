package dk.delfinen.gruppe5.domain.service;

public class PassiveMembership implements Membership {

    @Override
    public double calculateFee(int age) {
        return 500;
    }

    @Override
    public String getType() {
        return "Passive";
    }
}
