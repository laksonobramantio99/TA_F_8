//package apap.tugas.situ.rest;
//
//import apap.tugas.situ.model.JenisSuratModel;
//import apap.tugas.situ.model.PengajuanSuratModel;
//import apap.tugas.situ.model.UserModel;
//import apap.tugas.situ.repository.PengajuanSuratDB;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class SuratDetail {
//
//    private PengajuanSuratModel pengajuanSuratModel;
//
//    @JsonProperty("id")
//    private Integer id;
//
//    @JsonProperty("nomor-surat")
//    private String nomorSurat;
//
//    @JsonProperty("keterangan")
//    private String keterangan;
//
//    @JsonProperty("user")
//    @OneToOne
//    @JoinColumn(name = "idUser")
//    private UserModel user;
//
//    @JsonProperty("jenis-surat")
//    @OneToOne
//    @JoinColumn(name = "idJenisSurat")
//    private JenisSuratModel jenisSurat;
//
//    public PengajuanSuratModel getPengajuanSuratModel() {
//        return pengajuanSuratModel;
//    }
//
//    public void setPengajuanSuratModel(PengajuanSuratModel pengajuanSuratModel) {
//        this.pengajuanSuratModel = pengajuanSuratModel;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNomorSurat() {
//        return nomorSurat;
//    }
//
//    public void setNomorSurat(String nomorSurat) {
//        this.nomorSurat = nomorSurat;
//    }
//
//    public String getKeterangan() {
//        return keterangan;
//    }
//
//    public void setKeterangan(String keterangan) {
//        this.keterangan = keterangan;
//    }
//
//    public UserModel getUser() {
//        return user;
//    }
//
//    public void setUser(UserModel user) {
//        this.user = user;
//    }
//
//    public JenisSuratModel getJenisSurat() {
//        return jenisSurat;
//    }
//
//    public void setJenisSurat(JenisSuratModel jenisSurat) {
//        this.jenisSurat = jenisSurat;
//    }
//}
