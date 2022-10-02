package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PostDBStore;
import ru.job4j.dream.store.UserDBStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Service
public class UserService {

    private UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public List<User> getAllUsers() {
        return store.findAll();
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }
}
