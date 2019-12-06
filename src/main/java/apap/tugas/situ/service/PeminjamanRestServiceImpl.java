package apap.tugas.situ.service;

import apap.tugas.situ.rest.PeminjamanDetail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class PeminjamanRestServiceImpl implements PeminjamanRestService {

    String peminjamanUrl = "https://sikoperasi-f5.herokuapp.com/api/f5/pinjaman/ajukan";

    private final WebClient webClient;


    //Consumer Service
    public PeminjamanRestServiceImpl (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(peminjamanUrl).build();
    }


    @Override
    public Mono<String> getStatus(Long idRestoran) {
        return this.webClient.get().uri(peminjamanUrl).retrieve().bodyToMono(String.class);
    }

    @Override
    public String postStatus(JSONObject requestBody) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();

        String jsonReqBody = requestBody.toString();


        return this.webClient.post()
                .uri("")
                .header("Content-Type", "application/json")
                .syncBody(jsonReqBody)
                .retrieve()
                .bodyToMono(String.class).block();
    }
}
