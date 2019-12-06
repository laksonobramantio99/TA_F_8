package apap.tugas.situ.service;

import apap.tugas.situ.repository.LowonganDb;
import apap.tugas.situ.rest.Setting;
import apap.tugas.situ.rest.UserSiperpusDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LowonganRestServiceImpl implements LowonganRestService {
    @Autowired
    private LowonganDb lowonganDb;

    private final WebClient webClient;

    public LowonganRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.listUserSiperpus).build();
    }

    @Override
    public List<Map> getListUserSiperpus() {
        Map<String, Object> response = this.webClient.get()
                .uri("/api/users")
                .retrieve()
                .bodyToMono(Map.class).block();

        List<Map> listUserSiperpus = (ArrayList) response.get("result");

        return listUserSiperpus;
    }
}
