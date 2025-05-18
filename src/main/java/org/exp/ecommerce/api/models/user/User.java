package org.exp.ecommerce.api.models.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.exp.ecommerce.api.models.base.Attachment;
import org.exp.ecommerce.api.models.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String fullname;
    private String email;
    private String password;
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @ManyToOne
    private Attachment attachment;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
