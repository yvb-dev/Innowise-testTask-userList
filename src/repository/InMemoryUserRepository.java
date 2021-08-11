package repository;

import model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository<String> {

    private Map<String, User> storage;

    public InMemoryUserRepository(Map<String, User> storage) {
        this.storage = storage;
    }

    @Override
    public User save(User user) {
        storage.put(user.getEmail(), user);
        return user;
    }

    @Override
    public boolean delete(String key) {
        return storage.remove(key) != null;
    }

    @Override
    public User get(String key) {
        return storage.get(key);
    }

    @Override
    public List<User> getAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return get(email);
    }
}
