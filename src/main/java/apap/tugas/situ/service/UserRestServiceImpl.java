package apap.tugas.situ.service;

import apap.tugas.situ.repository.UserDB;
import apap.tugas.situ.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Random;
import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    final public static String sisivitasUrl = "http://sivitas.herokuapp.com";
    private final WebClient webClient;
    private final WebClient webClient2;

    @Autowired
    private UserDB userDB;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.getSivitas).build();
        this.webClient2 = webClientBuilder.baseUrl(sisivitasUrl).build();
    }

    @Override
    public Map<String, String> getUserProfile(String uuid, String role) {
        Map<String, String> userProfile = this.webClient.get().uri(Setting.getSivitas + role + "/" + uuid).retrieve().bodyToMono(Map.class).block();
        return userProfile;
    }

    @Override
    public Map<String, Object> getAllUserProfile(String role) {
        Map<String, Object> allUserProfile = this.webClient.get().uri(Setting.getSivitas + role).retrieve().bodyToMono(Map.class).block();
        return allUserProfile;
    }

    @Override
    public String postAddUser(JSONObject requestBody) {
        String newNIP = generateNIP(requestBody.get("tanggalLahir").toString(), requestBody.get("idUser").toString());
        requestBody.put("nip", newNIP);

        System.out.println(requestBody);
        String jsonReqBody = requestBody.toString();
        System.out.println(jsonReqBody);

        return this.webClient2.post()
                .uri("/api/employees")
                .header("Content-Type", "application/json")
                .syncBody(jsonReqBody)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public String generateNIP(String tanggalLahir, String uuid) {
        String nip = "P";

        nip += tanggalLahir.substring(8);
        nip += tanggalLahir.substring(5, 7);
        nip += tanggalLahir.substring(0, 4);

        Random rnd = new Random();
        for (int i = 0; i < 2; i++) {
            char c = (char) (rnd.nextInt(26) + 'A');
            nip += c;
        }
        for (int i = 0; i < 3; i++) {
            nip += rnd.nextInt(10);
        }
        nip += uuid;
        return nip;
    }
}
