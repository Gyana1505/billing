package billing.backend.controller;

import billing.backend.io.AuthReq;
import billing.backend.io.AuthRes;
import billing.backend.service.impl.UserServiceImpl;
import billing.backend.service.impl.userDetailsService;
import billing.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final userDetailsService userDetailsservice;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userServiceimpl;
    @PostMapping("/login")
    public AuthRes login(@RequestBody AuthReq authRequest) throws Exception{
        auntheticate(authRequest.getEmail(),authRequest.getPassword());
        final UserDetails userDetails=userDetailsservice.loadUserByUsername(authRequest.getEmail());
        String jwtToken=jwtUtil.generateToken(userDetails);
        String role= userServiceimpl.grtUserRole(authRequest.getEmail());
        return new AuthRes(authRequest.getEmail(),jwtToken,role);

    }

    private void auntheticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (DisabledException e){
            throw new Exception("user disable");
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email or password is wrong");
        }
    }

    @PostMapping("/encode")
    public String encodedPassword(@RequestBody Map<String,String> request){
               return passwordEncoder.encode(request.get("password"));
    }
}
