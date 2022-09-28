package ru.job4j.dream.store;

import org.apache.tomcat.jni.Time;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger atomicInteger = new AtomicInteger(4);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job",
                "Разработка", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Jov",
                "Разработка", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job",
                "Разработка", LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(atomicInteger.getAndIncrement());
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }
}
