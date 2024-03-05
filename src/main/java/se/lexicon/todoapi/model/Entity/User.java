package se.lexicon.todoapi.model.Entity;

import jakarta.persistence.*;
import lombok.*;
import se.lexicon.todoapi.model.Entity.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder


public class User {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    private boolean expired;

    @ManyToMany
    @JoinTable(
            name = "user-roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Role is null");
        if (roles == null) roles = new HashSet<>();
        roles.add(role);

    }

    public void removeRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Role is Null");
        if (roles != null) {
            roles.remove(role);
        } else {
            // we can add more exeption if need
        }


    }
}
