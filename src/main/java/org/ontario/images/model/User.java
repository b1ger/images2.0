package org.ontario.images.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User extends BaseUser {

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "password_hash", length = 100)
    @JsonIgnore
    private String password;

    @Column(length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private boolean activated;

    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Column(name = "about")
    @Size(max = 4000)
    private String about;

    @Column(name = "nickname")
    @Size(max = 20)
    private String nickName;

    @Column(name = "picture")
    @Lob
    private Byte[] picture;

    @ManyToMany
    @JoinTable(name = "users_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_name", referencedColumnName = "name")
    )
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    public boolean isActivated() {
        return activated;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public User() {
        super();
    }
}
