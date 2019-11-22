package apap.tugas.situ.rest;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeminjamanDetail {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("jumlah- pinjaman")
    private Integer jumlahPinjaman;

}
