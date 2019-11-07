package apap.tugas.situ.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis_surat")
public class JenisSuratModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @OneToMany(mappedBy = "jenis_surat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PengajuanSuratModel> listPengajuanSurat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<PengajuanSuratModel> getListPengajuanSurat() {
        return listPengajuanSurat;
    }

    public void setListPengajuanSurat(List<PengajuanSuratModel> listPengajuanSurat) {
        this.listPengajuanSurat = listPengajuanSurat;
    }
}
