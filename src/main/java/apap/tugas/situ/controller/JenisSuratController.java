package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.repository.JenisSuratDB;
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

    @Autowired
    JenisSuratDB jenisSuratDB;

    @GetMapping(path= "/jenisSurat/tambah")
    public String formTambahSurat(Model model) {
        JenisSuratModel calonJenisSurat = new JenisSuratModel();
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);

        return "form-tambah-surat.html";
    }

    @PostMapping(path = "/jenisSurat/tambah")
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
        String statusPenambahan = "Berhasil";
        model.addAttribute("statusPenambahan", statusPenambahan);

        return "form-tambah-surat-status.html";
    }

    @GetMapping(value = "/jenisSurat")
    public String kunjunganPertamaTanpaDelete(Model model) {
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();
        JenisSuratModel calonJenisSurat = new JenisSuratModel();

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);


        return "view-jenis-surat.html";
    }

    @GetMapping(value = "/jenisSurat/hapus")
    public String kunjunganSetelahMelakukanDelete(Model model) {
        List<JenisSuratModel> listSemuaJenisSurat = jenisSuratService.getSemuaJenisSurat();
        JenisSuratModel calonJenisSurat = new JenisSuratModel();
        String statusHapus = "berhasil dihapus";

        model.addAttribute("listSemuaJenisSurat", listSemuaJenisSurat);
        model.addAttribute("calonJenisSurat", calonJenisSurat);


        return "view-jenis-surat.html";
    }

    @RequestMapping(path = "/jenisSurat/hapus/{id}")
    public ModelAndView hapusJenisSurat(@PathVariable Integer id, ModelMap model, RedirectAttributes redirAttrs) {
        String namaJenisSuratTarget = jenisSuratService.getJenisSuratById(id).get().getNama();
        List<PengajuanSuratModel> listjenisSurat = jenisSuratDB.findById(id).get().getListPengajuanSurat();
        if (listjenisSurat.size() == 0) {
            jenisSuratService.hapusJenisSurat(id); // HAPUS
            redirAttrs.addFlashAttribute( "statusHapus", "berhasil dihapus");
        } else {
            redirAttrs.addFlashAttribute( "statusHapus", "gagal dihapus");
        }

        redirAttrs.addFlashAttribute("namaJenisSuratTarget", namaJenisSuratTarget);

        return new ModelAndView("redirect:/jenisSurat", model);
    }
}

