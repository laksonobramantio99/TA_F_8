package apap.tugas.situ.controller;

import apap.tugas.situ.service.PeminjamanRestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PeminjamanRestController {

    @Autowired
    PeminjamanRestService peminjamanRestService;

    @GetMapping(path = "/formulir-peminjaman")
    public String formulirPeminjaman(Model model) {
        model.addAttribute("isPosted", "notPosted");
        return "form-consumer-pengajuan-pinjaman.html";
    }

    @PostMapping(path = "/formulir-peminjaman", params = {"tanggalPeminjaman", "jumlahPinjaman"})
    public String postIsiFormulirPeminjaman(Model model, HttpServletRequest request) {
        String tanggalPeminjaman = request.getParameter("tanggalPeminjaman");
        String jumlahPinjaman = request.getParameter("jumlahPinjaman");

        // POST ke SI-Koperasi
        JSONObject requestBody = new JSONObject();
        requestBody.put("tanggalPeminjaman", tanggalPeminjaman);
        requestBody.put("jumlahPinjaman", jumlahPinjaman);


        model.addAttribute("isPosted", "posted");
        model.addAttribute("tanggalPeminjaman", tanggalPeminjaman);
        model.addAttribute("jumlahPinjaman", jumlahPinjaman);

        String status = peminjamanRestService.postStatus(requestBody);

        return "form-consumer-pengajuan-pinjaman.html";

    }

}
