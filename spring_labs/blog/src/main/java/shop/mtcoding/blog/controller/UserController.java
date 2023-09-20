package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.model.UserRepository;
import shop.mtcoding.blog.util.Script;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;
    
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @PostMapping("/login")
    @ResponseBody
    public String  login(String username, String password){
        User principal = userRepository.findByUsernameAndPassword(username, password);
        if(principal==null){
            return Script.back("로그인 실패");
        }
        session.setAttribute("principal",principal);
        return Script.href("/board/list");
    }

    @GetMapping("joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @PostMapping("join")
    @ResponseBody
    public String  join(String unsername, String password, String email){
        int result= userRepository.insert(unsername, password, email);
        if(result != 1){
            return Script.back("회원가입 실패");
        }
        return Script.href("/loginForm");
    }

    @GetMapping("updateForm")
    public String updateForm(Model model){
        User principal = (User)session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }

        User user =userRepository.findById(principal.getId());
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/user/{id}/update")
    @ResponseBody
    public String  update(@PathVariable int id, String password, String email){
        if(password == null || password.isEmpty()){
            return Script.back("password 입력x");
        }
        if(email == null || email.isEmpty()){
            return Script.back("email 입력x");
        }

        User principal = (User) session.getAttribute("principal");
        if (principal==null) {
            return Script.back("로그인 먼저");
        };

        if (principal.getId()!=id) {
            return Script.back("수정권한 x");
        };
        int result = userRepository.updateById(id, password, email);
        if(result!=1){
            return Script.back("회원정보수정 실패");
        }

        User user = userRepository.findById(id);
        session.setAttribute("principal", user);
        return Script.href("회원정보수정 완료", "/user/updateForm");
        


    }
}
