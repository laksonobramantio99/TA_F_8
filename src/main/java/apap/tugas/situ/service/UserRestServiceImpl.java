package apap.tugas.situ.service;


import apap.tugas.situ.repository.UserDB;
import apap.tugas.situ.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

    private final WebClient webClient;

    @Autowired
    private UserDB userDB;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.getSivitas).build();
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


}
