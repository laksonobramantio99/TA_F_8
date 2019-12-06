package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.repository.UserDB;
import apap.tugas.situ.rest.BaseResponse;
import apap.tugas.situ.rest.SuratDetail;
import apap.tugas.situ.service.JenisSuratService;
import apap.tugas.situ.service.SuratRestService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class SuratRestController {
    @Autowired
    private SuratRestService suratRestService;

    @Autowired
    private UserService userService;

    @Autowired
    private JenisSuratService jenisSuratService;

    @PostMapping(value = "/surat/tambah/")
    private BaseResponse<PengajuanSuratModel> createSurat(@RequestBody SuratDetail surat, BindingResult bindingResult){
        BaseResponse<PengajuanSuratModel> response = new BaseResponse<PengajuanSuratModel>();
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            PengajuanSuratModel mySurat = new PengajuanSuratModel();

            mySurat.setJenisSurat(jenisSuratService.getJenisSuratById(surat.getIdJenis()).get());
            mySurat.setStatus(0);
            mySurat.setUser(userService.findUserByUserName("admin1"));
            mySurat.setKeterangan(surat.getKeterangan());
            mySurat.setNomorSurat("0");
            mySurat.setTanggalDisetujui(null);
            mySurat.setTanggalPengajuan(new Date());
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(mySurat);
            suratRestService.createSurat(mySurat);
            return response;
        }
    }

    @GetMapping(value = "/surat/{nomorSurat}")
    private PengajuanSuratModel retriveSurat(@PathVariable("nomorSurat") String nomor){
        try{
            return suratRestService.getSuratbyNomor(nomor);
        }catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Nomor Surat " + nomor + " Not Found");
        }
    }
}
