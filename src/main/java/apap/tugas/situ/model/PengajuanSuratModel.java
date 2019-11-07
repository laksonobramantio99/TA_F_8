package apap.tugas.situ.model;

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
    private Long idPengajuanSurat;

    @NotNull
    @Size(max = 200)
    @Column(name = "nomor_surat", nullable = false)
    private String nomorSurat;

    @NotNull
    @Column(name = "tanggal_pengajuan", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengajuan;

    @Column(name = "tanggal_disetujui", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalDisetujui;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;


}
