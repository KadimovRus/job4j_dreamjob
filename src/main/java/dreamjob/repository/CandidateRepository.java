package dreamjob.repository;

import dreamjob.model.Candidate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CandidateRepository {
    Candidate save(Candidate candidate);

    void deleteById(int id);

    boolean update(Candidate candidate);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}
