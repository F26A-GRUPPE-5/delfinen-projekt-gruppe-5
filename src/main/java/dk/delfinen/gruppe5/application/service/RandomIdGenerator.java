package dk.delfinen.gruppe5.application.service;

import dk.delfinen.gruppe5.application.port.out.IdGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomIdGenerator implements IdGenerator {

    private final Random random = new Random();
    private final Set<Integer> usedIds = new HashSet<>();

    @Override
    public int nextId() {
        int id;

        do {
            id = random.nextInt(9000) + 1000  ;
        } while (usedIds.contains(id));

        usedIds.add(id);
        return id;
    }
}
