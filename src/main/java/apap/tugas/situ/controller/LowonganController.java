package apap.tugas.situ.controller;

import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.service.LowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

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
}
