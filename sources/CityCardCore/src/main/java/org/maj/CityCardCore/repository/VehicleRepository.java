package org.maj.CityCardCore.repository;

import org.maj.CityCardCore.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
