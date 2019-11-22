package apap.tugas.situ.repository;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JenisSuratDB extends JpaRepository<JenisSuratModel, Integer> {
    Optional<JenisSuratModel> findById(Long id);
    void deleteById(Long id);
    Optional<JenisSuratModel> findByNama(String nama);
}
