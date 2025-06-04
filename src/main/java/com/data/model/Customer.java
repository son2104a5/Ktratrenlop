package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @Max(value = 10, message = "First name must be less than 10 characters and greater than 3 characters")
    @Min(value = 3, message = "First name must be less than 10 characters and greater than 3 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    @Max(value = 10, message = "Last name must be less than 10 characters and greater than 3 characters")
    @Min(value = 3, message = "Last name must be less than 10 characters and greater than 3 characters")
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    private String address;

    private MultipartFile fileImage;

    private String imageUrl;
}
