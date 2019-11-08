package apap.tugas.situ.service;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDB roleDB;

    @Override
    public List<RoleModel> findAll() {
        return roleDB.findAll();
    }
}