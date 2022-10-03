package ru.job4j.dream.util;

import ru.job4j.dream.model.User;

import javax.servlet.http.HttpSession;

public final class SessionUser {

    private SessionUser() {
    }

    public static User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        } else {
            user.setName(user.getEmail().split("@")[0]);
        }
        return user;
    }
}
