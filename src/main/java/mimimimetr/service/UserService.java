package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.Role;
import mimimimetr.entity.User;
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

    public User Save(User user) {
        User oldUser = userRepository.findByName(user.getName());
        if (oldUser != null) {
            oldUser.setName(user.getName());
        } else {
            return userRepository.save(user);
        }
        return oldUser;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByName(user.getName());

        if (userFromDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

//        sendMessage(user);

        return true;
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setName(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);

        if (isEmailChanged) {
//            sendMessage(user);
        }
    }

    public User getByName(final String name) {
        return userRepository.findOneByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

}
