package ru.job4j.dream.control;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import ru.job4j.dream.model.User;

import javax.servlet.http.HttpSession;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = SessionUser.getSessionUser(session);
        model.addAttribute("user", user);
        return "index";
    }
}
