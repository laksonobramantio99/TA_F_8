package apap.tugas.situ.controller;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.service.PengajuanSuratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PengajuanSuratController {
    @Autowired
    PengajuanSuratService pengajuanSuratService;

    @RequestMapping("/surat")
    public String viewAllPengajuan(Model model){
        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();

        model.addAttribute("pengajuanSuratList", pengajuanSuratModelList);


        return "view-all-surat";
    }
}
