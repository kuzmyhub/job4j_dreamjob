package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Junior",
                "Developer", LocalDateTime.now()));
        candidates.put(2, new Candidate(2, "Middle",
                "Developer", LocalDateTime.now()));
        candidates.put(3, new Candidate(3, "Senior",
                "Developer", LocalDateTime.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public Candidate update(Candidate candidate) {
        Candidate candidateUpdate = candidates.get(candidate.getId());
        candidateUpdate.setName(candidate.getName());
        candidateUpdate.setDesc(candidate.getDesc());
        candidateUpdate.setCreated(candidate.getCreated());
        return candidateUpdate;
    }
}
