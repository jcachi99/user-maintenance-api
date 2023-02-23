package com.maintenance.user.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.maintenance.user.util.validator.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NotNull(message = "el campo name es obligatorio")
    @NotEmpty(message = "el campo name no puede ser vacio")
    @Column(name="name")
    private String name;

    @NotNull(message = "el campo email es obligatorio")
    @NotEmpty(message = "el campo email no puede ser vacio")
    @Email(message = "debe ser un email", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.cl", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(unique=true, name="email")
    private String email;

    @NotNull(message = "el campo password es obligatorio")
    @NotEmpty(message = "el campo password no puede ser vacio")
    @Column(name="password")
    @Password
    private String password;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name="user_id")
    private List<Phone> phones;

    @Column(name="active")
    private Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name="last_login")
    private String last_login;

    @Column(name="token")
    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name="created_at")
    private String created_at;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name="updated_at")
    private String updated_at;


}
