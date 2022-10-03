package ru.job4j.dream.control;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;
import ru.job4j.dream.util.SessionUser;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

class PostControllerTest {

    @Test
    public void whenPost() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        List<City> city = Arrays.asList(
                new City(1, "Москва"),
                new City(2, "СПб"));
        User user = new User(0, null, null);
        user.setName("Гость");
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(postService.findAll()).thenReturn(posts);
        when(cityService.getAllCities()).thenReturn(city);
        PostController postController = new PostController(
                postService, cityService
        );
        HttpSession session = mock(HttpSession.class);
        String page = postController.posts(model, session);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", posts);
        verify(model).addAttribute("city", city);
        assertThat(page).isEqualTo("posts");
    }
}