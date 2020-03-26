package mimimimetr.service;

import mimimimetr.entity.UserEntity;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    UserEntity getUserByAuthentication(Authentication authentication);

}
