package apap.tugas.situ.restcontroller;

import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.service.SuratRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class SuratRestController {
    @Autowired
    private SuratRestService suratRestService;

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
