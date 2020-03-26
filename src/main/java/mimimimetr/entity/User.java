package mimimimetr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mimiUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Transient
    @NotBlank(message = "Password confirmation cannot be empty")
    private String password2;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String activationCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", nullable = false))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

}
