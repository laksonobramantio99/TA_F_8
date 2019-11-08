package apap.tugas.situ.controller;


import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }




}
