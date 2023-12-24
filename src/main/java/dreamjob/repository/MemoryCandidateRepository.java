package dreamjob.repository;

import dreamjob.model.Candidate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private int nextId = 1;
    private final Map<Integer, Candidate> candidates = new HashMap<>();

    public MemoryCandidateRepository() {
        save(new Candidate(0, "Иванов Иван Иванович", "Java Junior Developer"));
        save(new Candidate(0, "Петров Петр Петрович", "Java Junior+ Developer"));
        save(new Candidate(0, "Сидоров Сидр Сидорович", "Java Middle Developer"));
        save(new Candidate(0, "Васин Василий Васильевич", "Java Senior Developer"));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) ->
                   new Candidate(oldCandidate.getId(),
                           candidate.getName(),
                           candidate.getDescription(),
                           oldCandidate.getCreationDate())) != null;
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
