package com.backcomerideal.controller;

import com.backcomerideal.model.Local;
import com.backcomerideal.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locales")
public class LocalController {



    @Autowired
    private LocalService localService;

    //devuelve todos los locales
    @GetMapping
    public ResponseEntity<List<Local>> getAllLocales() {
        List<Local> locales = localService.getAllLocales();
        return new ResponseEntity<>(locales, HttpStatus.OK);
    }

    //Devuelve un local por id
    @GetMapping("/local/{localId}")
    public ResponseEntity<Local> getLocalById(@PathVariable("localId") String id) {
        Local local = localService.getLocalById(id);
        if (local != null) {
            return new ResponseEntity<>(local, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Devuelve los loopcales por distrito
    @GetMapping("/distrito/{id}")
    public ResponseEntity<List<Local>> getLocalesByDistrictId(@PathVariable("id") String districtId) {
        List<Local> locales = localService.getLocalesByDistrict(districtId);
        if (!locales.isEmpty()) {
            return new ResponseEntity<>(locales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Devuelve los locales por actividad
    @GetMapping("/actividad/{activityType}")
    public ResponseEntity<List<Local>> getLocalesByActivityType(@PathVariable("activityType") String activityType) {
        List<Local> locales = localService.getLocalesByActivityType(activityType);
        if (!locales.isEmpty()) {
            return new ResponseEntity<>(locales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Devuelve los locales por actividad y barrio
    @GetMapping("/distrito/{districtId}/actividad/{activityType}")
    public ResponseEntity<List<Local>> getLocalesByDistrictIdAndActivityType(@PathVariable("districtId") String districtId, @PathVariable("activityType") String activityType) {
        List<Local> locales = localService.getLocalesByDistrict(districtId).stream().filter(local -> local.getActivityType().equalsIgnoreCase(activityType)).toList();
        if (!locales.isEmpty()) {
            return new ResponseEntity<>(locales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
