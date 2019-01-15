package org.ontario.images.web.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class NewUserRequest {

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 15;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = NewUserRequest.MIN_PASSWORD_LENGTH, max = NewUserRequest.MAX_PASSWORD_LENGTH)
    private String password;

    public NewUserRequest() {
    }

    public NewUserRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotBlank @Email String email,
            @NotBlank @Size(
                    min = NewUserRequest.MIN_PASSWORD_LENGTH,
                    max = NewUserRequest.MAX_PASSWORD_LENGTH) String password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUserReq{" +
                "email='" + this.email + '\'' +
                ", first name='" + this.firstName + '\'' +
                ", last name='" + this.lastName + '\'' +
                '}';
    }
}
