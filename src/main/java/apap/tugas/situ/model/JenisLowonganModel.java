package apap.tugas.situ.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "jenis_lowongan")
public class JenisLowonganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJenisLowongan;

    @NotNull
    @Column(name = "nama", nullable = false)
    @Size(max = 200)
    private String nama;

    @NotNull
    @Column(name = "keterangan", nullable = false)
    @Size(max = 200)
    private String keterangan;

}
