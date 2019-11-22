package apap.tugas.situ.service;

import apap.tugas.situ.rest.PeminjamanDetail;
import org.json.JSONObject;
import reactor.core.publisher.Mono;

public interface PeminjamanRestService {
    Mono<String> getStatus(Long idRestoran);
    String postStatus(JSONObject requestBody);
}
