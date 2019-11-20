package apap.tugas.situ.controller;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserRestService;
import apap.tugas.situ.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Principal principal, Model model) {
        userService.addUser(user);
        String role = userService.findUserByUserName(principal.getName()).getRole().getNama();
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("role", role);
        return "home";
    }

    @RequestMapping(value = "/tambah", method = RequestMethod.GET)
    public String addUserForm() {
        return "form-add-user";
    }

    @RequestMapping(value = "/tambah", method = RequestMethod.POST,
                    params = {"username", "password",
                              "nama", "tempatLahir", "tanggalLahir", "alamat", "noTelepon"})
    public String addUserSubmit(Model model, HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String nama = req.getParameter("nama");
        String tempatLahir = req.getParameter("tempatLahir");
        String tanggalLahir = req.getParameter("tanggalLahir");
        String alamat = req.getParameter("alamat");
        String noTelepon = req.getParameter("noTelepon");

        // Menambah ke DB pribadi
        RoleModel role = roleService.getByNama("Admin TU").get();
        UserModel userModel = new UserModel();
        userModel.setRole(role);
        userModel.setUsername(username);
        userModel.setPassword(password);
        userService.addUser(userModel);

        // Retrieve added user
        UserModel addedUser = userService.findUserByUserName(username);

        // POST to SI-Sivitas
        JSONObject requestBody = new JSONObject();
        requestBody.put("idUser", addedUser.getUuid());
        requestBody.put("nip", "P07051999AB123" + addedUser.getUuid());
        requestBody.put("nama", nama);
        requestBody.put("tempatLahir", tempatLahir);
        requestBody.put("tanggalLahir", tanggalLahir);
        requestBody.put("alamat", alamat);
        requestBody.put("telepon", noTelepon);

        model.addAttribute("user", addedUser);
        model.addAttribute("userDetail", requestBody);

        userRestService.postAddUser(requestBody);
        return "add-user";
    }
}
