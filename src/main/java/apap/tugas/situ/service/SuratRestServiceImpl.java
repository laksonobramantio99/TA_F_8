package apap.tugas.situ.service;


import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.repository.PengajuanSuratDB;
import apap.tugas.situ.rest.Setting;
import apap.tugas.situ.rest.SuratDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class SuratRestServiceImpl implements SuratRestService{

    private final WebClient webClient;


    @Autowired
    private PengajuanSuratDB pengajuanSuratDB;

    public SuratRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.suratUrl).build();
    }

    @Override
    public PengajuanSuratModel getSuratbyNomor(String nomor) {
        Optional<PengajuanSuratModel> surat = pengajuanSuratDB.findByNomorSurat(nomor);
        if (surat.isPresent()){
            return surat.get();
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void createSurat(PengajuanSuratModel surat) {
         pengajuanSuratDB.save(surat);
    }
}
