package apap.tugas.situ.service;

import apap.tugas.situ.model.UserModel;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserRestService {
    Map<String,String> getUserProfile(String uuid, String role);
    Map<String, Object> getAllUserProfile(String role);
}
