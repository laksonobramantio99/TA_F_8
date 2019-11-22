package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.repository.JenisLowonganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JenisLowonganServiceImpl implements JenisLowonganService {

    @Autowired
    private JenisLowonganDB jenisLowonganDB;

    @Override
    public List<JenisLowonganModel> getAll() {
        return jenisLowonganDB.findAll();
    }
}
