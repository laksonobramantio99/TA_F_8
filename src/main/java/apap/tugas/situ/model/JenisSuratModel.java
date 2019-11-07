package apap.tugas.situ.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis_surat")
public class JenisSuratModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJenisSurat;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String namaJenisSurat;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @OneToMany(mappedBy = "pengajuan_surat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PengajuanSuratModel> listPengajuanSurat;
}
