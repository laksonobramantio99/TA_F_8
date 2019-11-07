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
@Table(name = "lowongan")
public class LowonganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "judul" , nullable = false)
    private String judul;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_dibuka" , nullable = false)
    private Date tanggalDibuka;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_ditutup" , nullable = false)
    private Date tanggalDitutup;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan" , nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_user", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jenis_lowongan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisLowonganModel jenisLowongan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Date getTanggalDibuka() {
        return tanggalDibuka;
    }

    public void setTanggalDibuka(Date tanggalDibuka) {
        this.tanggalDibuka = tanggalDibuka;
    }

    public Date getTanggalDitutup() {
        return tanggalDitutup;
    }

    public void setTanggalDitutup(Date tanggalDitutup) {
        this.tanggalDitutup = tanggalDitutup;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public JenisLowonganModel getJenisLowongan() {
        return jenisLowongan;
    }

    public void setJenisLowongan(JenisLowonganModel jenisLowongan) {
        this.jenisLowongan = jenisLowongan;
    }
}
