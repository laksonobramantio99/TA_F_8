package apap.tugas.situ.repository;

import apap.tugas.situ.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDB extends JpaRepository<RoleModel, Integer> {
    Optional<RoleModel> findByNama(String nama);
}
