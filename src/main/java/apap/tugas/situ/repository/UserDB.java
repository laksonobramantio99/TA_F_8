package apap.tugas.situ.repository;

import apap.tugas.situ.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
}
