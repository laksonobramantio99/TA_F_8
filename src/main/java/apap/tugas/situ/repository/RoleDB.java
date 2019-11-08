package apap.tugas.situ.repository;

import apap.tugas.situ.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDB extends JpaRepository<RoleModel, Integer> {
}
