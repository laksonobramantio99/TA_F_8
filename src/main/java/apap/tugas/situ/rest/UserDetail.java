package apap.tugas.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail {

    private String nama;

    private String nip;



}
