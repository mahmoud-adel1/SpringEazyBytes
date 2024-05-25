package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity{

    @Id
    private int contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    @Column(name = "mobileNum")
    private String mobileNum;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Please provide a valid email address")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 3 characters long")
    @Column(name = "subject")
    private String subject;

    @NotBlank(message = "message must not be blank")
    @Size(min = 10, message = "Message must be at least 3 characters long")
    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private String status;

}
