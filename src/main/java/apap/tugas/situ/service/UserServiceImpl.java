package apap.tugas.situ.service;

import apap.tugas.situ.model.UserModel;
import apap.tugas.situ.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDB userDB;


    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }



    @Override
    public UserModel findUserByUserName(String username) {
        return userDB.findByUsername(username);
    }

    @Override
    public Boolean validatePassword(String oldPassword, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(oldPassword, newPassword)) {
            return true;
        }else{
            return false;
        }
    }


//
//    @Override
//    public UserModel changePassword(UserModel user, String oldPassword, String newPassword) {
//        UserModel targetedUser = findUserByUserName(user.getUsername());
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String existingPassword = targetedUser.getPassword();
//        String dbPassword = user.getPassword();
//        if (passwordEncoder.matches(existingPassword, dbPassword)) {
//            targetedUser.setPassword(encrypt(user.getPassword()));
//            userDB.save(user);
//            return user;
//            // Encode new password and store it
//        }else{
//            return null;
//        }
//
//    }
}
