package mimimimetr.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mimimimetr.entity.User;
import mimimimetr.repository.UserRepository;
import mimimimetr.security.details.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final @NonNull UserRepository userRepository;

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.getOne(userDetails.getId());
    }

}
