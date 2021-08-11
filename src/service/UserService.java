package service;

import model.User;

import java.util.List;

public interface UserService<E, K> {

    // null if not found, when updated
    User create(E user);

    // null if not found, when updated
    User update(E user);

    // false if not found
    boolean delete(K key);

    // null if not found
    User get(K key);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
