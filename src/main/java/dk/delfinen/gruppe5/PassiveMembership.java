package dk.delfinen.gruppe5;

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
