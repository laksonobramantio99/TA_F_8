package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SuratDetail {

    @JsonProperty("id-jenis")
    private Integer idJenis;

    @JsonProperty("keterangan")
    private String keterangan;

    public Integer getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(Integer idJenis) {
        this.idJenis = idJenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
