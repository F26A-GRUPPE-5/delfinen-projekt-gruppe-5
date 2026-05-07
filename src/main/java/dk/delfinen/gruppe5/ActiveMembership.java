package dk.delfinen.gruppe5;

public class ActiveMembership implements Membership {

    @Override
    public double calculateFee(int age) {

        if (age < 18) {
            return 1000;
        }

        double fee = 1600;

        if (age >= 60) {
            fee *= 0.75;
        }

        return fee;
    }


    @Override
    public String getType() {
        return "Active";
    }
}
