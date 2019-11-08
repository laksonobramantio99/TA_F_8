package apap.tugas.situ.service;


import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.repository.PengajuanSuratDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PengajuanSuratServiceImpl implements PengajuanSuratService {

    @Autowired
    PengajuanSuratDB pengajuanSuratDb;

    @Override
    public List<PengajuanSuratModel> getPengajuanSuratList() {
        return pengajuanSuratDb.findAll();
    }

}
