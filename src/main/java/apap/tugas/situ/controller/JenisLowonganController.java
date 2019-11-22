package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.JenisLowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JenisLowonganController {

    @Autowired
    JenisLowonganService jenisLowonganService;

    @RequestMapping(value = "/jenisLowongan", method = RequestMethod.GET)
    private String viewAll(Model model) {
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAllJenisLowongan();
        model.addAttribute("listJenisLowongan", listJenisLowongan);

        return "view-all-jenis-lowongan";
    }

    @RequestMapping(value = "/jenisLowongan/add", method = RequestMethod.GET)
    private String addJenisLowonganForm(Model model) {
        JenisLowonganModel jenisLowongan = new JenisLowonganModel();
        model.addAttribute("jenisLowongan", jenisLowongan);
        return "form_jenis_lowongan";
    }

    @RequestMapping(value = "/jenisLowongan/add", method = RequestMethod.POST)
    private String addJenisLowonganSubmit(@ModelAttribute JenisLowonganModel jenisLowongan, Model model) {
        JenisLowonganModel existing = jenisLowonganService.findByNama(jenisLowongan.getNama());
        if (existing != null) {
            JenisLowonganModel newJenisLowongan = new JenisLowonganModel();
            model.addAttribute("jenisLowongan", newJenisLowongan);
            model.addAttribute("alert", "EXIST");
            return "form_jenis_lowongan";
        }else {
            ArrayList<LowonganModel> listLowongan = new ArrayList<>();
            jenisLowongan.setListLowongan(listLowongan);
            jenisLowonganService.addJenisLowongan(jenisLowongan);
            model.addAttribute("jenisLowongan", jenisLowongan);

            List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAllJenisLowongan();
            model.addAttribute("listJenisLowongan", listJenisLowongan);

            return "view-all-jenis-lowongan";
        }
    }

}
