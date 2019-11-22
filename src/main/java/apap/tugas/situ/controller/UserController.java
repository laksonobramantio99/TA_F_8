package apap.tugas.situ.controller;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.rest.Setting;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserRestService;
import apap.tugas.situ.service.UserService;
import org.apache.maven.artifact.repository.Authentication;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUserAllForm(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "form-add-user-all";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Principal principal, Model model) {
        userService.addUser(user);
        String role = userService.findUserByUserName(principal.getName()).getRole().getNama();
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("role", role);
        return "form-add-user-all";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    private String viewProfile(Model model){
        UserModel user = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        String userId = user.getUuid();
        Map<String,String> profile =  null;
        String role = "";
        System.out.println("masuk");
        if (user.getRole().getNama().equals("Admin TU")) {
            System.out.println("masuk1");
            role = "employees";
        }else if (user.getRole().getNama().equals("Siswa")){
            System.out.println("masuk2");
            role = "students";
        }else if (user.getRole().getNama().equals("Guru") || user.getRole().getNama().equals("Kepala Sekolah") ) {
            System.out.println("masuk3");
            role = "teachers";
        }
        System.out.println(role);
        Map<String, Object> allProfile = userRestService.getAllUserProfile(role);
        System.out.println(allProfile);
        System.out.println(allProfile.size());
        ArrayList listUser = (ArrayList) allProfile.get("result");
        System.out.println("masuk5");

        ArrayList<String> listUuid = new ArrayList<String>();
        for(int i = 0; i < listUser.size(); i++){
            LinkedHashMap<String, Object> ab = (LinkedHashMap<String, Object>) listUser.get(i);
            listUuid.add((String)ab.get("idUser"));
        }
        if(listUuid.contains(userId)){
            profile = userRestService.getUserProfile(userId, role);
            System.out.println(listUuid);
            System.out.println(profile);
        }else {
            profile = null;
        }

        model.addAttribute("profile", profile);
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "view-profile";
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
        requestBody.put("nip", "");
        requestBody.put("nama", nama);
        requestBody.put("tempatLahir", tempatLahir);
        requestBody.put("tanggalLahir", tanggalLahir);
        requestBody.put("alamat", alamat);
        requestBody.put("telepon", noTelepon);

        model.addAttribute("user", addedUser);

        userRestService.postAddUser(requestBody);
        return "add-user";
    }
}
