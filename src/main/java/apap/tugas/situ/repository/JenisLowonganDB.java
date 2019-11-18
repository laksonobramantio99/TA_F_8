package apap.tugas.situ.repository;

import apap.tugas.situ.model.JenisLowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisLowonganDB extends JpaRepository<JenisLowonganModel, Integer> {
    JenisLowonganModel findByNama(String nama);
}
