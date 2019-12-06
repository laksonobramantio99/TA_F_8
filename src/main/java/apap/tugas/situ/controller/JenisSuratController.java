package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.service.JenisSuratService;
import apap.tugas.situ.service.JenisSuratServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class JenisSuratController {

    @Autowired
    JenisSuratService jenisSuratService;

    @GetMapping(path= "/jenis-surat/tambah")
    public String formTambahSurat(Model model) {
        JenisSuratModel calonJenisSurat = new JenisSuratModel();
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);

        return "form-tambah-surat.html";
    }

    @PostMapping(path = "/jenis-surat/tambah")
    public String aksiTambahSurat(@ModelAttribute JenisSuratModel jenisSuratBaru, Model model) {
        JenisSuratModel calonJenisSurat = new JenisSuratModel();
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);
        model.addAttribute("namaJenisSurat", jenisSuratBaru.getNama());

        // Cek di DB
        for (JenisSuratModel jenisSuratDiDB : listSemuaJenisSurat) {
            if (jenisSuratDiDB.getNama().equals(jenisSuratBaru.getNama())) {
                String statusPenambahan = "Gagal";
                model.addAttribute("statusPenambahan",  statusPenambahan);
                return "form-tambah-surat-status.html";
            }
        }

        // Cek nama kosong
        if(jenisSuratBaru.getNama().equals("")) {
            String statusPenambahan = "Gagal";
            model.addAttribute("statusPenambahan",  statusPenambahan);
            return "form-tambah-surat-status.html";
        }

        jenisSuratService.tambahJenisSurat(jenisSuratBaru);
        System.out.println(jenisSuratBaru.getNama()); //CHECK
        String statusPenambahan = "Berhasil";
        model.addAttribute("statusPenambahan", statusPenambahan);

        return "form-tambah-surat-status.html";
    }

    @GetMapping(value = "/jenis-surat")
    public String kunjunganPertamaTanpaDelete(Model model) {
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();
        JenisSuratModel calonJenisSurat = new JenisSuratModel();

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);


        return "view-jenis-surat.html";
    }

    @GetMapping(value = "/jenis-surat/hapus")
    public String kunjunganSetelahMelakukanDelete(Model model) {
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();
        JenisSuratModel calonJenisSurat = new JenisSuratModel();
        String statusHapus = "berhasil dihapus";

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);


        return "view-jenis-surat.html";
    }

    @PostMapping(path = "/jenis-surat/hapus/{id}")
    public ModelAndView hapusJenisSurat(@PathVariable Integer id, ModelMap model, RedirectAttributes redirAttrs) {
        String namaJenisSuratTarget = jenisSuratService.getJenisSuratById(id).get().getNama();
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();

        model.addAttribute("namaJenisSuratTarget", namaJenisSuratTarget);
        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("id", id);


        jenisSuratService.hapusJenisSurat(id); // HAPUS

        try {
            List<JenisSuratModel> semuaJenisSurat = jenisSuratService.getSemuaJenisSurat();
            if (semuaJenisSurat.size() == 0) {
                jenisSuratService.hapusJenisSurat(id);
            }
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Id " + id.toString() + " tidak ditemukan");
            return new ModelAndView("redirect:/jenis-surat", model);
        }

//        ModelAndView mv = new ModelAndView("redirect:/jenis-surat/hapus");

        // Redirect Attribut
        redirAttrs.addFlashAttribute("namaJenisSuratTarget", namaJenisSuratTarget);
        redirAttrs.addFlashAttribute( "statusHapus", "berhasil dihapus");
        return new ModelAndView("redirect:/jenis-surat", model);
    }
}

