package dreamjob.repository;

import dreamjob.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean deleteById(int id);

    Collection<User> findAll();

}
