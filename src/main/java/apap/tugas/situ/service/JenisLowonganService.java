package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisLowonganModel;

import java.util.List;
import java.util.Optional;

public interface JenisLowonganService {
    List<JenisLowonganModel> getAll();
    Optional<JenisLowonganModel> getByNama(String nama);
}
