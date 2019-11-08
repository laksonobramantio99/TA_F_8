package apap.tugas.situ.service;

import apap.tugas.situ.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel findUserByUserName(String username);
//    UserModel changePassword(UserModel user, String oldPassword, String newPassword);
    Boolean validatePassword(String oldPassword, String newPassword);
}
