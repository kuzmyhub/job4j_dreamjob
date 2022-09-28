package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;

import org.springframework.stereotype.Service;

import java.util.List;

@ThreadSafe
@Service
public class PostService {

    private final PostStore store;

    public PostService(PostStore store) {
        this.store = store;
    }

    public List<Post> findAll() {
        return (List<Post>) store.findAll();
    }

    public void add(Post post) {
        store.add(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void update(Post post) {
        store.update(post);
    }
}
