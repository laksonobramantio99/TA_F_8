package apap.tugas.situ.service;

import apap.tugas.situ.model.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleModel> findAll();
    Optional<RoleModel> getByNama(String nama);
}
