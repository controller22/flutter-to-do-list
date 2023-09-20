package shop.mtcoding.buyer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.User;
import shop.mtcoding.buyer.model.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request) {
        String username = "";

        // ${Cookie.renenber.value}
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember")) {
                username = cookie.getValue();
            }
        }
        request.setAttribute("remember", username);
        // String cookies = request.getHeader("cookie");
        // String arr[] = cookies.split(";");
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(String username, String password, String remember, HttpServletResponse response) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return "redirect:/loginForm";
        } else {
            // 요청헤더 : cookie
            // 응답헤더 : Set-Cookie
            if (remember == null) {
                remember = "";
            }

            if (remember.equals("on")) {
                // Set-Cookie

                Cookie cookie = new Cookie("remember", username);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("remember", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            session.setAttribute("principal", user); // principal 인증주체
            return "redirect:/";
        }
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(String username, String password, String email) {
        int result = userRepository.insert(username, password, email);
        if (result == 1) {
            return "redirect:/loginForm";
        } else {
            return "redirect:/joinForm";
        }
    }
}
