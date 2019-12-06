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
@Table(name = "pengajuanSurat")
public class PengajuanSuratModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nomorSurat", nullable = false)
    private String nomorSurat;

    @NotNull
    @Column(name = "tanggalPengajuan", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonIgnore
    private Date tanggalPengajuan;

    @Column(name = "tanggalDisetujui", nullable = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonIgnore
    private Date tanggalDisetujui;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuidUser", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idJenis", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
