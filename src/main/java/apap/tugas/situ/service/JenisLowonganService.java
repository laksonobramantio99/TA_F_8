package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisLowonganModel;

import java.util.List;

public interface JenisLowonganService {
    void addJenisLowongan(JenisLowonganModel jenisLowongan);
    List<JenisLowonganModel> getAllJenisLowongan();
    JenisLowonganModel findByNama(String nama);
    void deleteJenisLowongan(JenisLowonganModel jenisLowongan);

}
