package org.maj.CityCardCore.model;

import jakarta.persistence.*;
import lombok.*;
import org.maj.CityCardCore.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    String login;
    String password;
    List<Role> roles;

    public User(String login, String password, boolean isController) {
        this.login = login;
        this.password = password;
        this.roles = List.of(isController ? Role.CONTROLLER : Role.PASSSENGER);
    }
}
