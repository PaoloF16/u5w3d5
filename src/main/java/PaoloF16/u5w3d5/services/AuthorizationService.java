package PaoloF16.u5w3d5.services;

import PaoloF16.u5w3d5.entities.User;
import PaoloF16.u5w3d5.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;


    public String checkCredentialsAndGenerateToken(LoginRequestDTO body) {

        User found = this.userService.findByEmail(body.email());


        if (bcrypt.matches(body.password(), found.getPassword())) {

            String accessToken = jwtTools.createToken(found);

            return accessToken;
        } else {
            throw new UnauthorizedException("Incorrect credentials!");
        }
    }


}