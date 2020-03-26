package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.Role;
import mimimimetr.entity.UserEntity;
import mimimimetr.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final @NonNull
    UserRepository userRepository;

    private final @NonNull
    PasswordEncoder passwordEncoder;

    public UserEntity save(UserEntity userEntity) {
        UserEntity oldUserEntity = userRepository.findByName(userEntity.getName());
        if (oldUserEntity != null) {
            oldUserEntity.setName(userEntity.getName());
        } else {
            return userRepository.save(userEntity);
        }
        return oldUserEntity;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public boolean addUser(UserEntity userEntity) {
        UserEntity userEntityFromDb = userRepository.findByName(userEntity.getName());

        if (userEntityFromDb != null) {
            return false;
        }

        userEntity.setRoles(Collections.singleton(Role.USER));
        userEntity.setActivationCode(UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

//        sendMessage(user);

        return true;
    }

    public void saveUser(UserEntity userEntity, String username, Map<String, String> form) {
        userEntity.setName(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        userEntity.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                userEntity.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(userEntity);
    }

    public void updateProfile(UserEntity userEntity, String password, String email) {
        String userEmail = userEntity.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            userEntity.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                userEntity.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            userEntity.setPassword(password);
        }

        userRepository.save(userEntity);

        if (isEmailChanged) {
//            sendMessage(user);
        }
    }

    public UserEntity getByName(final String name) {
        return userRepository.findOneByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

}
