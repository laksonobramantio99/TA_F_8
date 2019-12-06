package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.JenisLowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
            model.addAttribute("alert", "EXIST");
            List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAllJenisLowongan();
            model.addAttribute("listJenisLowongan", listJenisLowongan);

            return "view-all-jenis-lowongan";
        }
    }

    @RequestMapping(value = "/jenisLowongan/hapus/{nama}")
    public ModelAndView hapusJenisLowongan(@PathVariable String nama, ModelMap model, RedirectAttributes redirAttrs) {
        jenisLowonganService.deleteJenisLowongan(jenisLowonganService.findByNama(nama)); // HAPUS

//        ModelAndView mv = new ModelAndView("redirect:/jenis-surat/hapus");

        // Redirect Attribut
        redirAttrs.addFlashAttribute("namaJenisLowongan", nama);
        redirAttrs.addFlashAttribute( "statusHapus", "berhasil dihapus");
        return new ModelAndView("redirect:/jenisLowongan", model);
    }

}
