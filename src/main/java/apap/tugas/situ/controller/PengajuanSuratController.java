package apap.tugas.situ.controller;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.service.PengajuanSuratService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PengajuanSuratController {
    @Autowired
    PengajuanSuratService pengajuanSuratService;

    @Autowired
    UserService userService;

    @RequestMapping("/surat")
    public String viewAllPengajuan(Model model){
        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();

        model.addAttribute("pengajuanSuratList", pengajuanSuratModelList);

        return "view-all-surat";
    }

    @RequestMapping(value = "/surat/ubahStatus/{id}", method = RequestMethod.GET)
    public String updateSuratFormPage(@PathVariable Integer id, Model model){
        PengajuanSuratModel existingSurat = pengajuanSuratService.getSuratbyId(id).get();

        model.addAttribute("surat",existingSurat);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("role", userService.findUserByUserName(auth.getName()).getRole().getNama());

        String[] listKepalaSekolah = {"Ditolak", "Disetujui"};
        model.addAttribute("listKepSek", listKepalaSekolah);

        String[] listTU = {"Diproses", "Selesai"};
        model.addAttribute("listTU", listTU);

        return "form-ubah-pengajuan-surat";
    }

    @RequestMapping(value = "/surat/ubahStatus/{id}", method = RequestMethod.POST)
    public String updateSuratSubmit(@PathVariable Integer id, @ModelAttribute PengajuanSuratModel surat,  Model model){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(surat.getTanggalPengajuan());

        model.addAttribute("date", date);

        PengajuanSuratModel newData = pengajuanSuratService.ubahSurat(surat);
        model.addAttribute("surat", newData);
        return "change-surat-submit";
    }


}
