package mimimimetr.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mimimimetr.entity.UserEntity;
import mimimimetr.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final @NonNull
    UserService userSevice;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userSevice.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{userEntity}")
    public String userEditForm(@PathVariable UserEntity userEntity, Model model) {
        model.addAttribute("user", userEntity);
        model.addAttribute("roles", "user");

        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") UserEntity userEntity
    ) {
        userSevice.saveUser(userEntity, username, form);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserEntity userEntity) {
        model.addAttribute("username", userEntity.getName());
        model.addAttribute("email", userEntity.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal UserEntity userEntity,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userSevice.updateProfile(userEntity, password, email);

        return "redirect:/user/profile";
    }

}
