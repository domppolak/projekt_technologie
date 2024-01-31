package org.maj.CityCardCore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Vehicle")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}
