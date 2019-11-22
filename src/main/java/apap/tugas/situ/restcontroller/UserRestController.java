package apap.tugas.situ.restcontroller;

import apap.tugas.situ.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserRestService userRestService;

    @GetMapping(value = "/user/{uuid}")
    private Map<String,String> getProfile(@PathVariable String uuid){
        return userRestService.getUserProfile(uuid, "employees");
    }
}
