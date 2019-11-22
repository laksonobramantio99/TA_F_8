package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;
import reactor.core.publisher.Mono;

public interface SuratRestService {
    PengajuanSuratModel getSuratbyNomor(String nomor);
//    Mono<PengajuanSuratModel> postSurat();
}
