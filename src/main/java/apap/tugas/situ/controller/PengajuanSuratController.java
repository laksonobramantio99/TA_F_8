package apap.tugas.situ.controller;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.JenisSuratService;
import apap.tugas.situ.service.PengajuanSuratService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    JenisSuratService jenisSuratService;

    @RequestMapping(value = "/surat/ubahStatus/{id}", method = RequestMethod.GET)
    public String updateSuratFormPage(@PathVariable Integer id, Model model){
        PengajuanSuratModel existingSurat = pengajuanSuratService.getSuratbyId(id).get();

        model.addAttribute("surat",existingSurat);

        if (existingSurat.getStatus() == 0 ) {
            System.out.println(
                    existingSurat.getStatus()
            );
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("role", userService.findUserByUserName(auth.getName()).getRole().getNama());
            if ((userService.findUserByUserName(auth.getName()).getRole().getNama()).equals("Kepala Sekolah")){
                System.out.println(
                        "test"
                );
                String[] listStatus = {"Ditolak", "Disetujui"};
                model.addAttribute("listStatus", listStatus);
            }else if((userService.findUserByUserName(auth.getName()).getRole().getNama()).equals("Admin TU")) {
                String[] listStatus = {"Tidak dapat mengubah Status"};
                model.addAttribute("listStatus", listStatus);
            }
        }

        if (existingSurat.getStatus() == 2) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("role", userService.findUserByUserName(auth.getName()).getRole().getNama());
            if ((userService.findUserByUserName(auth.getName()).getRole().getNama()).equals("Kepala Sekolah")) {
                String[] listKepalaSekolah = {"Sudah diproses"};
                model.addAttribute("listStatus", listKepalaSekolah);
            } else if ((userService.findUserByUserName(auth.getName()).getRole().getNama()).equals("Admin TU")) {
                String[] listTU = {"Diproses", "Selesai"};
                model.addAttribute("listStatus", listTU);
            }
        }
        return "form-ubah-pengajuan-surat";
    }

    @GetMapping(value = "/surat")
    public String viewAllPengajuan(Model model) {
        UserModel userModel = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        if (userModel.getRole().getNama().equals("Guru") || userModel.getRole().getNama().equals("Siswa") ) {
            List<PengajuanSuratModel> pengajuanSuratModelListUser = pengajuanSuratService.getPengajuanSuratListUser(userModel.getUuid());
            model.addAttribute("pengajuanSuratList", pengajuanSuratModelListUser);
        } else{
            List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();
            model.addAttribute("pengajuanSuratList", pengajuanSuratModelList);
        }

        return "view-all-surat";
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

    @RequestMapping(value = "/surat/tambah",  method = RequestMethod.GET)
    public String formTambahSuratPage (Model model){
        PengajuanSuratModel newPengajuanSurat = new PengajuanSuratModel();
        model.addAttribute("mySurat",newPengajuanSurat);
        List<JenisSuratModel> jenisSurat = jenisSuratService.getSemuaJenisSurat();
        model.addAttribute("listJenis", jenisSurat);
        return "add-pengajuan-surat";
    }

    @RequestMapping(value = "surat/tambah", method = RequestMethod.POST)
    public String formTambahSuratSubmit(@ModelAttribute PengajuanSuratModel surat, Model model){
        UserModel userModel = userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        surat.setUser(userModel);

        pengajuanSuratService.tambahSurat(surat);
        model.addAttribute("mySurat", surat);

        String statusPenambahan = "Berhasil";
        model.addAttribute("statusPenambahan", statusPenambahan);

        List<JenisSuratModel> jenisSurat = jenisSuratService.getSemuaJenisSurat();
        model.addAttribute("listJenis", jenisSurat);

        return  "add-pengajuan-surat-completed";
    }


    @RequestMapping(value = "surat/hapus/{id}")
    public String hapusPengajuanSurat(@PathVariable Integer id, @ModelAttribute PengajuanSuratModel surat, ModelMap model, RedirectAttributes redirAttrs ){
        PengajuanSuratModel mySurat = pengajuanSuratService.getSuratbyId(id).get();

        pengajuanSuratService.hapusSurat(mySurat);

        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();
        model.addAttribute("pengajuanSuratList", pengajuanSuratModelList);

        model.addAttribute("nomorSurat", mySurat.getNomorSurat());
        model.addAttribute( "statusHapus", "berhasil dihapus");

        return "delete-success-surat";
    }





}
