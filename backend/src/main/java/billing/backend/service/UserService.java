package billing.backend.service;

import billing.backend.io.UserReq;
import billing.backend.io.UserRes;

import java.util.List;

public interface UserService {
    UserRes createUser(UserReq request);
    String grtUserRole(String email);
    List<UserRes> readUser();
    void deleteUser(String id);
}
