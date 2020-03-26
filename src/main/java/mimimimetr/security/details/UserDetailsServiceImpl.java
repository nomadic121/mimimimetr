package mimimimetr.security.details;

import lombok.RequiredArgsConstructor;
import mimimimetr.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final @NonNull
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findOneByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User not found")));
    }

}
