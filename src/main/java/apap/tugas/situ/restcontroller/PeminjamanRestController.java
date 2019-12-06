package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.PeminjamanRestService;
import apap.tugas.situ.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PeminjamanRestController {

    @Autowired
    PeminjamanRestService peminjamanRestService;

    @Autowired
    UserService userService;

    @GetMapping(path = "/formulir-peminjaman")
    public String formulirPeminjaman(Model model) {
        model.addAttribute("isPosted", "notPosted");
        return "form-consumer-pengajuan-pinjaman.html";
    }

    @PostMapping(path = "/formulir-peminjaman", params = {"jumlahPinjaman"})
    public String postIsiFormulirPeminjaman(Model model, HttpServletRequest request) {
        UserModel user = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        String jumlahPinjaman = request.getParameter("jumlahPinjaman");
        String uuid = user.getUuid();
        String namaUser = user.getUsername();

        // POST ke SI-Koperasi
        JSONObject requestBody = new JSONObject();
        requestBody.put("uuid", "df4b3daf-0362-11ea-8121-40b0340b67c7");
        requestBody.put("jumlahPinjaman", jumlahPinjaman);

        System.out.println(uuid);
        System.out.println(jumlahPinjaman);

        model.addAttribute("isPosted", "posted");
        model.addAttribute("uuid", "df4b3daf-0362-11ea-8121-40b0340b67c7");
        model.addAttribute("namaUser", namaUser);
        model.addAttribute("jumlahPinjaman", jumlahPinjaman);


        peminjamanRestService.postStatus(requestBody);

        return "form-consumer-pengajuan-pinjaman.html";

    }

}
