package dk.delfinen.gruppe5.domain.model;

import dk.delfinen.gruppe5.domain.service.ActiveMembership;
import dk.delfinen.gruppe5.domain.service.PassiveMembership;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    void testJuniorFee() {

        ActiveMembership membership = new ActiveMembership();

        double fee = membership.calculateFee(16);

        assertEquals(1000, fee);
    }

    @Test
    void testSeniorFee() {

        ActiveMembership membership = new ActiveMembership();

        double fee = membership.calculateFee(25);

        assertEquals(1600, fee);
    }

    @Test
    void testSeniorDiscountFee() {

        ActiveMembership membership = new ActiveMembership();

        double fee = membership.calculateFee(65);

        assertEquals(1200, fee);
    }

    @Test
    void testPassiveFee() {

        PassiveMembership membership = new PassiveMembership();

        double fee = membership.calculateFee(30);

        assertEquals(500, fee);
    }
}