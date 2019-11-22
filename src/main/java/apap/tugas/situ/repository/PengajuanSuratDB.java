package apap.tugas.situ.repository;

import apap.tugas.situ.model.PengajuanSuratModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PengajuanSuratDB extends JpaRepository<PengajuanSuratModel, Integer> {
    Optional<PengajuanSuratModel> findByNomorSurat(String nomor);
}
