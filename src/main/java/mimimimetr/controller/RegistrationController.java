package mimimimetr.controller;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.Role;
import mimimimetr.entity.UserEntity;
import mimimimetr.form.UserRegistrationForm;
import mimimimetr.service.UserService;
import mimimimetr.util.ControllerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userSevice;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid UserRegistrationForm userRegistrationForm, BindingResult bindingResult, Model model) {
        if (userRegistrationForm.getPassword() != null && !userRegistrationForm.getPassword().equals(userRegistrationForm.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        UserEntity userEntity = UserEntity.builder()
                .name(userRegistrationForm.getName())
                .password(userRegistrationForm.getPassword())
                .email(userRegistrationForm.getEmail())
//                .roles(Collections.singleton(Role.valueOf(registrationForm.getRoles())))
                .roles(Collections.singleton(Role.USER))
                .build();

        if (!userSevice.addUser(userEntity)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/activate/{code}")
//    public String activate(Model model, @PathVariable String code) {
//        boolean isActivated = userSevice.activateUser(code);
//
//        if (isActivated) {
//            model.addAttribute("message", "User successfully activated");
//        } else {
//            model.addAttribute("message", "Activation code is not found!");
//        }
//
//        return "login";
//    }

}
