package apap.tugas.situ.model;

import com.google.inject.internal.util.Join;
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
    private long idLowongan;

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

    @Size(max = 200)
    @Column(name = "keterangan" , nullable = true)
    private String keterangan;

    @NotNull
    @Column(name = "jumlah" , nullable = false)
    private Integer jumlah;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diagnosis_penyakit", referencedColumnName = "idJenisLowongan")
    private JenisLowonganModel jenisLowongan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserModel user;



}
