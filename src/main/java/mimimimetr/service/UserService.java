package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.Role;
import mimimimetr.entity.UserEntity;
import mimimimetr.form.UserRegistrationForm;
import mimimimetr.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final @NonNull
    UserRepository userRepository;

    private final @NonNull
    PasswordEncoder passwordEncoder;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public boolean addUser(final UserRegistrationForm userRegistrationForm) {
        UserEntity userEntityFromDb = userRepository.findByName(userRegistrationForm.getName());

        if (userEntityFromDb != null) {
            return false;
        }

        UserEntity userEntity = UserEntity.builder()
                .name(userRegistrationForm.getName())
                .password(passwordEncoder.encode(userRegistrationForm.getPassword()))
                .email(userRegistrationForm.getEmail())
                .roles(Collections.singleton(Role.USER))
                .activationCode(UUID.randomUUID().toString())
                .build();

        userRepository.save(userEntity);

        return true;
    }

    public UserEntity getByName(final String name) {
        return userRepository.findOneByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

}
