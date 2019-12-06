package apap.tugas.situ.service;

import apap.tugas.situ.model.LowonganModel;

import java.util.List;
import java.util.Optional;

public interface LowonganService {
    List<LowonganModel> getAllLowongan();
    Optional<LowonganModel> getById(Integer id);
    LowonganModel ubahLowongan(LowonganModel lowongan);
    LowonganModel tambahLowongan(LowonganModel lowongan);
}
