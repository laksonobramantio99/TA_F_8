package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.repository.JenisSuratDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JenisSuratServiceImpl implements JenisSuratService {

    @Autowired
    private JenisSuratDB jenisSuratDB;

    @Override
    public List<JenisSuratModel> getSemuaJenisSurat() {
        return jenisSuratDB.findAll();
    }

    @Override
    public void tambahJenisSurat(JenisSuratModel jenisSuratBaru) {
        jenisSuratDB.save(jenisSuratBaru);
    }

    @Override
    public Optional<JenisSuratModel> getJenisSuratById(Integer id) {
        return jenisSuratDB.findById(id);
    }

    @Override
    public Optional<JenisSuratModel> getJenisSuratByNama(String nama) {
        return jenisSuratDB.findByNama(nama);
    }


    @Override
    public void hapusJenisSurat(Integer id) {
        jenisSuratDB.deleteById(id);
    }

    @Override
    public boolean checkAddSuccess(String namaJenisSuratAdded, String namaJenisSuratDiDB) {
        boolean isNotSame = !(namaJenisSuratAdded.equals(namaJenisSuratDiDB));
        return isNotSame;
    }


}
