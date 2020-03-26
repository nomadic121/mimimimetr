package mimimimetr.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationForm {

    private String name;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String password2;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String roles;

}
