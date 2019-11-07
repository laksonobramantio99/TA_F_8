package apap.tugas.situ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pengajuan_surat")
public class PengajuanSuratModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nomor_surat", nullable = false)
    private String nomorSurat;

    @NotNull
    @Column(name = "tanggal_pengajuan", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalPengajuan;

    @Column(name = "tanggal_disetujui", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalDisetujui;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_user", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jenis", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisSuratModel jenisSurat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Date getTanggalDisetujui() {
        return tanggalDisetujui;
    }

    public void setTanggalDisetujui(Date tanggalDisetujui) {
        this.tanggalDisetujui = tanggalDisetujui;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public JenisSuratModel getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(JenisSuratModel jenisSurat) {
        this.jenisSurat = jenisSurat;
    }
}
