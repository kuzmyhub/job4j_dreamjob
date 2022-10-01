package ru.job4j.dream.store;

import org.junit.jupiter.api.Test;
import ru.job4j.StartUI;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CandidateDBStoreTest {

    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new StartUI().loadPool());
        City city = new City(1, "Москва");
        byte[] array = new byte[1];
        Candidate candidate = new Candidate(0, "name", "description",
                LocalDateTime.now(), true, array, city);
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(candidate.getName());
    }

    @Test
    public void whenUpdate() {
        CandidateDBStore store = new CandidateDBStore(new StartUI().loadPool());
        City city = new City(1, "Москва");
        byte[] array = new byte[1];
        Candidate candidate = new Candidate(0, "name", "description",
                LocalDateTime.now(), true, array, city);
        store.add(candidate);
        Candidate updateCandidate = new Candidate(candidate.getId(), "updateName", "description",
                LocalDateTime.now(), true, array, city);
        store.update(updateCandidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(updateCandidate.getName());
    }

    @Test
    public void whenFindNull() {
        CandidateDBStore store = new CandidateDBStore(new StartUI().loadPool());
        City city = new City(1, "Москва");
        byte[] array = new byte[1];
        Candidate candidate = new Candidate(0, "name", "description",
                LocalDateTime.now(), true, array, city);
        store.add(candidate);
        int notExistingId = 100;
        Candidate candidateInDb = store.findById(notExistingId);
        assertNull(candidateInDb);
    }

}