package dreamjob.repository;

import dreamjob.model.Candidate;
import dreamjob.model.Vacancy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryCandidateRepository implements CandidateRepository {

    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;
    private Map<Integer, Candidate> candidates = new HashMap<>();

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    public MemoryCandidateRepository() {
        save(new Candidate(0, "Иванов Иван Иванович", "Java Junior Developer", LocalDateTime.now()));
        save(new Candidate(0, "Петров Петр Петрович", "Java Junior+ Developer", LocalDateTime.now()));
        save(new Candidate(0, "Сидоров Сидр Сидорович", "Java Middle Developer", LocalDateTime.now()));
        save(new Candidate(0, "Васин Василий Васильевич", "Java Senior Developer", LocalDateTime.now()));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public void deleteById(int id) {
        candidates.remove(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) ->
                   new Candidate(oldCandidate.getId(),
                           candidate.getName(),
                           candidate.getDescription(),
                           candidate.getCreationDate())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
