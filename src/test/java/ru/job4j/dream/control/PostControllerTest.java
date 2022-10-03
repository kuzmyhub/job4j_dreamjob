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
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        List<City> cities = Arrays.asList(
                new City(1, "Москва"),
                new City(2, "СПб"));
        User user = new User(0, null, null);
        user.setName("Гость");
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession session = mock(HttpSession.class);
        PostController postController = new PostController(
                postService, cityService
        );
        when(postService.findAll()).thenReturn(posts);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.posts(model, session);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", posts);
        verify(model).addAttribute("city", cities);
        assertThat(page).isEqualTo("posts");
    }

    @Test
    public void whenCreatePost() {
        City city = new City(1, "Москва");
        Post post = new Post(1, "Post");
        post.setCity(city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService, cityService
        );
        String page = postController.createPost(post);
        verify(postService).add(post);
        assertThat(page).isEqualTo("redirect:/posts");
    }

    @Test
    public void whenUpdatePost() {
        City city = new City(1, "Москва");
        Post post = new Post(1, "Post");
        post.setCity(city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService, cityService
        );
        String page = postController.updatePost(post);
        verify(postService).update(post);
        assertThat(page).isEqualTo("redirect:/posts");
    }

    @Test
    public void whenFormUpdatePost() {
        User user = new User(0, null, null);
        List<City> cities = Arrays.asList(
                new City(1, "Москва")
        );
        Post post = new Post(1, "Post");
        int inId = 1;
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession httpSession = mock(HttpSession.class);
        PostController postController = new PostController(
                postService, cityService
        );
        when(postService.findById(any(int.class))).thenReturn(post);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formUpdatePost(
                model, inId, httpSession
        );
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("post", post);
        verify(model).addAttribute("cities", cities);
        assertThat(page).isEqualTo("updatePost");
    }

    @Test
    public void thenFormAddPost() {
        User user = new User(0, null, null);
        List<City> cities = Arrays.asList(
                new City(1, "Москва")
        );
        Post post = new Post(0, "Заполните поле");
        Model model = mock(Model.class);
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService, cityService
        );
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formAddPost(
                model, httpSession
        );
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("post", post);
        verify(model).addAttribute("cities", cities);
        assertThat(page).isEqualTo("addPost");
    }
}