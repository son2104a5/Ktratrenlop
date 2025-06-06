package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 10, message = "First name must be between 3 and 10 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 10, message = "Last name must be between 3 and 10 characters")
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    private String address;

    private String imageUrl;
}
