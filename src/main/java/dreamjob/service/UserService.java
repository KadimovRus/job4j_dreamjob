package dreamjob.service;

import dreamjob.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean deleteById(int id);

    Collection<User> findAll();
}
