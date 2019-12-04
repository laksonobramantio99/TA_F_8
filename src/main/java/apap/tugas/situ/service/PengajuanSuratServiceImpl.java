package apap.tugas.situ.service;


import apap.tugas.situ.model.PengajuanSuratModel;
import apap.tugas.situ.repository.PengajuanSuratDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PengajuanSuratServiceImpl implements PengajuanSuratService {

    @Autowired
    PengajuanSuratDB pengajuanSuratDb;

    @Override
    public List<PengajuanSuratModel> getPengajuanSuratList() {
        return pengajuanSuratDb.findAll();
    }

    @Override
    public Optional<PengajuanSuratModel> getSuratbyId(Integer id) {
        return pengajuanSuratDb.findById(id);
    }

    @Override
    public void generateCode(PengajuanSuratModel surat) {
        String myCode = "";
        String alphabet = "0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int j=0;j<8;j++) {
            int index = (int) (alphabet.length() * Math.random());
            sb.append(alphabet.charAt(index));
        }
        myCode = sb.toString();
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        String tahunTerbit = format.format(surat.getTanggalDisetujui());
        myCode += String.valueOf(tahunTerbit);
        surat.setNomorSurat(myCode);
        pengajuanSuratDb.save(surat);
    }

    @Override
    public PengajuanSuratModel ubahSurat(PengajuanSuratModel surat) {
        PengajuanSuratModel mySurat = pengajuanSuratDb.findById(surat.getId()).get();

        try{
            mySurat.setTanggalDisetujui(surat.getTanggalDisetujui());
            mySurat.setStatus(surat.getStatus());
            generateCode(surat);
            pengajuanSuratDb.save(mySurat);
            return mySurat;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void tambahSurat(PengajuanSuratModel surat){
        surat.setNomorSurat("0");
        surat.setTanggalPengajuan(new Date());
        surat.setStatus(0);
        surat.setTanggalDisetujui(null);
        pengajuanSuratDb.save(surat);
    }

    @Override
    public void hapusSurat(PengajuanSuratModel surat) {
        pengajuanSuratDb.delete(surat);
    }
}


