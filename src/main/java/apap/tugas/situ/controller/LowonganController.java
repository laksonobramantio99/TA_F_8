package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.JenisLowonganService;
import apap.tugas.situ.service.LowonganService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    private JenisLowonganService jenisLowonganService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/lowongan", method = RequestMethod.GET)
    public String viewAllLowongan(Model model) {
        List<LowonganModel> listLowongan = lowonganService.getAllLowongan();
        model.addAttribute("listLowongan", listLowongan);
        return "viewall-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.GET)
    public String ubahJumlahLowonganForm(Model model, @PathVariable(value = "id") Integer id) {
        LowonganModel lowongan = lowonganService.getById(id).get();
        model.addAttribute("lowongan", lowongan);

        boolean updatable = false;
        java.util.Date utilDate = new Date();
        java.sql.Date today = new java.sql.Date(utilDate.getTime());

        long selisih = lowongan.getTanggalDibuka().getTime() - today.getTime();
        if (selisih > 0) {
            updatable = true;
        }
        model.addAttribute("updatable", updatable);

        return "form-ubah-jumlah-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.POST)
    public String ubahJumlahLowonganSubmit(
            @PathVariable(value = "id") Integer id,
            @ModelAttribute LowonganModel lowongan,
            Model model
    ) {
        LowonganModel targetLowongan = lowonganService.ubahLowongan(lowongan);
        model.addAttribute("lowongan", targetLowongan);

        return "ubah-jumlah-lowongan";
    }

    @RequestMapping(value = "/lowongan/tambah", method = RequestMethod.GET)
    public String tambahLowonganForm(Model model) {
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAll();
        JenisLowonganModel jenisLowonganPustakawan = jenisLowonganService.getByNama("Pustakawan").get();
        listJenisLowongan.remove(jenisLowonganPustakawan);
        LowonganModel lowongan = new LowonganModel();

        model.addAttribute("listJenisLowongan", listJenisLowongan);
        model.addAttribute("lowongan", lowongan);
        return "form-tambah-lowongan";
    }

    @RequestMapping(value = "/lowongan/tambahPustakawan", method = RequestMethod.GET)
    public String tambahLowonganPustakawanForm(Model model) {
        java.util.Date utilDate = new Date();
        java.sql.Date today = new java.sql.Date(utilDate.getTime());
        java.sql.Date day30 = addDays(today, 30);

        JenisLowonganModel jenisLowonganPustakawan = jenisLowonganService.getByNama("Pustakawan").get();
        LowonganModel lowongan = new LowonganModel();
        lowongan.setJudul("Lowongan Pustakawan");
        lowongan.setTanggalDibuka(today);
        lowongan.setTanggalDitutup(day30);
        lowongan.setKeterangan("Dibutuhkan Pustakawan Cakap");
        lowongan.setJumlah(5);

        model.addAttribute("jenisLowonganPustakawan", jenisLowonganPustakawan);
        model.addAttribute("lowongan", lowongan);
        return "form-tambah-lowongan-pustakawan";
    }

    @RequestMapping(value = "/lowongan/tambah", method = RequestMethod.POST)
    public String tambahLowonganSubmit(@ModelAttribute LowonganModel lowongan, Model model) {
        UserModel userModel = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        lowongan.setUser(userModel);
        lowonganService.tambahLowongan(lowongan);

        model.addAttribute("lowongan", lowongan);
        return "tambah-lowongan";
    }

    public static java.sql.Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new java.sql.Date(c.getTimeInMillis());
    }
}
