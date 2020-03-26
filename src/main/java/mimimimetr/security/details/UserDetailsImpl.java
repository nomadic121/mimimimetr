package mimimimetr.security.details;

import mimimimetr.entity.Role;
import mimimimetr.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String name;

    private String password;

    private Set<Role> roles;

    public UserDetailsImpl(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.password = userEntity.getPassword();
        this.roles = userEntity.getRoles();
    }

    public Long getId() {
        return id;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
