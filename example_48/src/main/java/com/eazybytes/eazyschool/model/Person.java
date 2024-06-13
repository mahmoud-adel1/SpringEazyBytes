package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import com.eazybytes.eazyschool.annotation.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
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
    @JsonIgnore
    private String confirmEmail;

    @Column(name = "password")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @NotBlank(message = "Password must bot be blank")
    @PasswordValidator
    @JsonIgnore
    private String password;

    @NotBlank(message = "Password must bot be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Transient
    @JsonIgnore
    private String confirmPassword;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = true)
    private EazyClass eazyClass;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_person",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
            }
    )
    private Set<Course> courses;
}
