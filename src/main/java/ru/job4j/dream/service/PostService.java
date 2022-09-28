package ru.job4j.dream.service;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;

import java.util.List;

public class PostService {

    private static final PostService INST = new PostService();

    private final PostStore store = PostStore.instOf();

    private PostService() {
    }

    public final PostService instOf() {
        return INST;
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
