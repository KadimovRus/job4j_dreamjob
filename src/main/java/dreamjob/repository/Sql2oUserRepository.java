package dreamjob.repository;

import dreamjob.model.User;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oUserRepository implements UserRepository {

    private final Sql2o sql2o;

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<User> save(User user) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO users (name, email, password)
                    VALUES (:name, :email, :password)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("name", user.getName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword());
            int generatedKey = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedKey);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM users WHERE email=:email and password=:password
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("email", email)
                    .addParameter("password", password);
            return Optional.ofNullable(query.executeAndFetchFirst(User.class));

        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var sql = "DELETE FROM users WHERE id=:id";
            var query = connection.createQuery(sql)
                    .addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public Collection<User> findAll() {
        try (var connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM users").executeAndFetch(User.class);
        }
    }
}
