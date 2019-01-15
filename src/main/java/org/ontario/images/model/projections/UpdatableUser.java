package org.ontario.images.model.projections;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UpdatableUser {

    @NotNull
    private Long id;

    @Size(min = 1, max = 75)
    private String firstName;

    @Size(min = 1, max = 75)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    public UpdatableUser(
            final Long id,
            final String firstName,
            final String lastName,
            final String email
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
