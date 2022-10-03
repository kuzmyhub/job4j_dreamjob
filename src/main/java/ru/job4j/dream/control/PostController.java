package ru.job4j.dream.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;
import ru.job4j.dream.util.SessionUser;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class PostController {

    private final PostService postService;

    private final CityService cityService;

    public PostController(PostService postService,
                          CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        User user = SessionUser.getInstance().getSessionUser(session);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("city", cityService.getAllCities());
        return "posts";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.add(post);
        return "redirect:/posts";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.update(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id,
                                 HttpSession session) {
        User user = SessionUser.getInstance().getSessionUser(session);
        model.addAttribute("user", user);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @GetMapping("/formAddPost")
    public String formAddPost(Model model, HttpSession session) {
        User user = SessionUser.getInstance().getSessionUser(session);
        model.addAttribute("user", user);
        model.addAttribute("candidate", new Post(0, "Заполните поле"));
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }
}
