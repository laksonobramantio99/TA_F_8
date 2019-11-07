package apap.tugas.situ.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenisLowongan")
public class JenisLowonganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nama", nullable = false)
    @Size(max = 200)
    private String nama;

    @NotNull
    @Column(name = "keterangan", nullable = false)
    @Size(max = 200)
    private String keterangan;

    @OneToMany(mappedBy = "jenisLowongan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LowonganModel> listLowongan;

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

    public List<LowonganModel> getListLowongan() {
        return listLowongan;
    }

    public void setListLowongan(List<LowonganModel> listLowongan) {
        this.listLowongan = listLowongan;
    }
}
