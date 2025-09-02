package billing.backend.service.impl;

import billing.backend.entity.UserEntity;
import billing.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class userDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user=userrepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("email is not present"+email));
        return new User(user.getEmail(),user.getPassword(),
                Collections.singleton((new SimpleGrantedAuthority(user.getRole()))));
    }


}
