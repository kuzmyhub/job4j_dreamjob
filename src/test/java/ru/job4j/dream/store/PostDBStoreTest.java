package ru.job4j.dream.store;

import org.junit.Test;
import ru.job4j.StartUI;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class PostDBStoreTest {
    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new StartUI().loadPool());
        City city = new City(1, "Москва");
        Post post = new Post(0, "name", "description",
                LocalDateTime.now(), true, city);
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdate() {
        PostDBStore store = new PostDBStore(new StartUI().loadPool());
        City city = new City(1, "Москва");
        Post post = new Post(0, "name", "description",
                LocalDateTime.now(), true, city);
        store.add(post);
        Post updatePost = new Post(post.getId(), "updateName", "description",
                LocalDateTime.now(), true, city);
        store.update(updatePost);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(updatePost.getName()));
    }

    @Test
    public void whenFindNull() {
        PostDBStore store = new PostDBStore(new StartUI().loadPool());
        City city = new City(1, "Москва");
        Post post = new Post(0, "name", "description",
                LocalDateTime.now(), true, city);
        store.add(post);
        int notExistingId = 20;
        Post postInDb = store.findById(notExistingId);
        assertNull(postInDb);
    }
}