package mimimimetr.service;

import mimimimetr.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    User getUserByAuthentication(Authentication authentication);

}
