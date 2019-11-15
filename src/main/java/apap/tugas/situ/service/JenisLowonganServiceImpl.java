package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.repository.JenisLowonganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JenisLowonganServiceImpl implements JenisLowonganService {

    @Autowired
    JenisLowonganDB jenisLowonganDB;


    @Override
    public void addJenisLowongan(JenisLowonganModel jenisLowongan) {
        jenisLowonganDB.save(jenisLowongan);

    }
}
