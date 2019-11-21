package apap.tugas.situ.service;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDB roleDB;

    @Override
    public List<RoleModel> findAll() {
        return roleDB.findAll();
    }

    @Override
    public Optional<RoleModel> getByNama(String nama) {
        return roleDB.findByNama(nama);
    }
}
