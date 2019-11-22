package apap.tugas.situ.repository;

import apap.tugas.situ.model.JenisLowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JenisLowonganDB extends JpaRepository<JenisLowonganModel, Integer> {
    List<JenisLowonganModel> findAll();
}
