package com.example.employeeserver.models;

import com.example.employeeserver.enums.EmployeeSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "nom et prénom est rquis")
    @Column(name="full_name")
    private String fullName;

    @NotBlank(message = "adresse est rquis")
    private String address;

    @NotBlank(message = "téléphone est rquis")
    @Column(name="phone", length=20, unique=true)
    private String phone;

    @NotEmpty(message = "email est rquis")
    @Email(message = "entrez un valide email")
    @Column(name="email", length=50, unique=true)
    private String email;

    @NotBlank(message = "mot de pass est rquis")
    private String password;

    @NotBlank(message = "role est rquis")
    private String role;

    @Min(value = 18, message = "age doit etre +18")
    private int age;

    @Enumerated(EnumType.ORDINAL)
    private EmployeeSex sex = EmployeeSex.MALE;

}
