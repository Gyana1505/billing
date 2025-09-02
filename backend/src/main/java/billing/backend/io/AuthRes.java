package billing.backend.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRes {
    private String email;
    private String token;
    private String role;
}
