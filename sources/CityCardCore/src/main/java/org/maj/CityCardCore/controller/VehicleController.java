package org.maj.CityCardCore.controller;

import org.maj.CityCardCore.model.Vehicle;
import org.maj.CityCardCore.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    public VehicleRepository repository;

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        try {
            List<Vehicle> vehicleList = new ArrayList<>(repository.findAll());

            if(vehicleList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(vehicleList, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVehicleById/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id){
        Optional<Vehicle> vehicle = repository.findById(id);

        if(vehicle.isPresent()){
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
        Vehicle createdVehicle = repository.save(vehicle);

        return new ResponseEntity<>(createdVehicle, HttpStatus.OK);
    }

    @DeleteMapping("/deleteVehicleById/{id}")
    public ResponseEntity<Vehicle> deleteVehicleById(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
