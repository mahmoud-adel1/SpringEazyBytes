package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import com.eazybytes.eazyschool.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
@FieldsValueMatch.List({
        @FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "Password do not match!"),
        @FieldsValueMatch(field = "email", fieldMatch = "confirmEmail", message = "Email do not match!")
})
public class Person extends BaseEntity{

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "name")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Column(name = "mobile_number")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Column(name = "email")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    @Transient
    private String confirmEmail;

    @Column(name = "password")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @NotBlank(message = "Password must bot be blank")
    @PasswordValidator
    private String password;

    @NotBlank(message = "Password must bot be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Transient
    private String confirmPassword;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = true)
    private Address address;
}
