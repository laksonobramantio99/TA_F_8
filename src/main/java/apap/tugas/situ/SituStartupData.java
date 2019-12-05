package apap.tugas.situ;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.service.RoleService;
import apap.tugas.situ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SituStartupData implements ApplicationRunner {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Tambah role
        String[] listRole = {
                "Kepala Sekolah",
                "Admin TU",
                "Guru",
                "Siswa",
                "Pustakawan",
                "Pengurus Koperasi",
                "Anggota Koperasi"
        };
        for (String roleName : listRole) {
            RoleModel role = new RoleModel();
            role.setNama(roleName);
            roleService.addRole(role);
        }

        // Tambah user admin
        UserModel userAdmin = new UserModel();
        userAdmin.setRole(roleService.getByNama("Admin TU").get());
        userAdmin.setUsername("admin");
        userAdmin.setPassword("admin");
        userService.addUser(userAdmin);
    }
}
