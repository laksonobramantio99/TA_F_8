package apap.tugas.situ.controller;

import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Principal principal, Model model) {
        userService.addUser(user);
        String role = userService.findUserByUserName(principal.getName()).getRole().getNama();
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("role", role);
        return "home";
    }

    @RequestMapping(value = "/tambah", method = RequestMethod.GET)
    public String addUserForm(Model model) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        return "form-add-user";
    }
}
