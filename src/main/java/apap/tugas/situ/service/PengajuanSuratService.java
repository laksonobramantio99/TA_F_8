package apap.tugas.situ.service;

import apap.tugas.situ.model.PengajuanSuratModel;

import java.util.List;
import java.util.Optional;

public interface PengajuanSuratService {
    List<PengajuanSuratModel> getPengajuanSuratList();
    Optional<PengajuanSuratModel> getSuratbyId(Integer id);
    void generateCode(PengajuanSuratModel surat);
    PengajuanSuratModel ubahSurat(PengajuanSuratModel surat);
    void tambahSurat(PengajuanSuratModel surat);
    void hapusSurat(PengajuanSuratModel surat);

}
