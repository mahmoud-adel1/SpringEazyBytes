package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "fees must not be blank")
    @Size(min = 2, message = "fees must be at least 2 characters long")
    @Column(name = "fees")
    private String fees;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, mappedBy = "courses")
    private Set<Person> persons;
}
