package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostDBStore;
import ru.job4j.dream.store.PostStore;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class PostService {

    private final PostDBStore store;

    private final CityService cityService;

    public PostService(PostDBStore store) {
        this.store = store;
        this.cityService = new CityService();
    }

    public Collection<Post> findAll() {
        List<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return store.findAll();
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
