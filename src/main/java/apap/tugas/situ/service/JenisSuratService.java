package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisSuratModel;

import java.util.List;
import java.util.Optional;

public interface JenisSuratService {
    List<JenisSuratModel> getSemuaJenisSurat();
    Optional<JenisSuratModel> getJenisSuratById(Integer id);
    Optional<JenisSuratModel> getJenisSuratByNama(String nama);
    void tambahJenisSurat(JenisSuratModel jenisSuratBaru);
    void hapusJenisSurat(Integer id);
    boolean checkAddSuccess(String namaJenisSuratAdded, String namaJenisSuratDiDB);
}
