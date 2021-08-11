package repository;

import model.User;

import java.util.List;

public interface UserRepository<T> {

    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(T key);

    // null if not found
    User get(T key);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
