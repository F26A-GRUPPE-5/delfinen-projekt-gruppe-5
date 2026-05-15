package dk.delfinen.gruppe5.application.service;

import dk.delfinen.gruppe5.application.port.out.IdGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FakeIdGenerator implements IdGenerator {
    private final Random random = new Random();
    private final Set<Integer> usedIds = new HashSet<>();
    private int parameterId;

    public FakeIdGenerator(int parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public int nextId() {
        return parameterId;
    }
}