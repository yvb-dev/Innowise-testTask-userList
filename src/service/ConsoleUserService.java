package service;

import model.User;
import repository.UserRepository;
import to.UserTO;

import java.util.List;

import static util.ValidationUtil.getValidationUser;


public class ConsoleUserService implements UserService<UserTO, String> {

    private UserRepository<String> repository;

    public ConsoleUserService(UserRepository<String> repository) {
        this.repository = repository;
    }

    @Override
    public User create(UserTO userTO) {
        if (get(userTO.getEmail()) != null) {
            throw new IllegalArgumentException("Такой пользователь уже существует!");
        }
        User user = getValidationUser(userTO);
        return repository.save(user);
    }

    @Override
    public User update(UserTO userTO) {
        User user = getValidationUser(userTO);
        delete(userTO.getEditEmail());
        return repository.save(user);
    }

    @Override
    public boolean delete(String key) {
        return repository.delete(key);
    }

    @Override
    public User get(String key) {
        return repository.get(key);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
