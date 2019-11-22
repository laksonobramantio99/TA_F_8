package apap.tugas.situ.service;

import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.repository.LowonganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LowonganServiceImpl implements  LowonganService {
    @Autowired
    private LowonganDb lowonganDb;

    @Override
    public List<LowonganModel> getAllLowongan() {
        return lowonganDb.findAll();
    }

    @Override
    public Optional<LowonganModel> getById(Integer id) {
        return lowonganDb.findById(id);
    }

    @Override
    public LowonganModel ubahLowongan(LowonganModel lowongan) {
        LowonganModel targetLowongan = lowonganDb.findById(lowongan.getId()).get();

        try {
            targetLowongan.setJumlah(lowongan.getJumlah());
            lowonganDb.save(targetLowongan);
            return targetLowongan;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public LowonganModel tambahLowongan(LowonganModel lowongan) {
        return lowonganDb.save(lowongan);
    }
}
