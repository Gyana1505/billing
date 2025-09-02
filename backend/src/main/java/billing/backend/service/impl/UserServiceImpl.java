package billing.backend.service.impl;

import billing.backend.entity.UserEntity;
import billing.backend.io.UserReq;
import billing.backend.io.UserRes;
import billing.backend.repository.UserRepo;
import billing.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserRes createUser(UserReq request) {
        UserEntity newUser=convertToEntity(request);
        newUser=userRepo.save(newUser);
        return  convetToResponse(newUser);
    }

    private UserRes convetToResponse(UserEntity newUser) {
       return UserRes.builder()
                .userId(newUser.getUserId())
                .name(newUser.getName())
                .email(newUser.getEmail())
                .role(newUser.getRole())
                .createdAt(newUser.getCreatedAt())
                .updatedAt(newUser.getUpdatedAt())
                .build();
    }

    private UserEntity convertToEntity(UserReq request) {
       return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase())
                .build();
    }

    @Override
    public String grtUserRole(String email) {
        UserEntity user= userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getRole();
    }

    @Override
    public List<UserRes> readUser() {
        return userRepo.findAll().stream().map(user -> convetToResponse(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
       UserEntity findUser=userRepo.findByUserId(id)
               .orElseThrow(()->new UsernameNotFoundException("User is not found"));
       userRepo.delete(findUser);
    }
}
