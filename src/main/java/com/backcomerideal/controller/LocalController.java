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


    @GetMapping
    public ResponseEntity<List<Local>> getAllLocales() {
        List<Local> locales = localService.getAllLocales();
        return new ResponseEntity<>(locales, HttpStatus.OK);
    }

    @GetMapping("/distrito/{id}")
    public ResponseEntity<List<Local>> getLocalesByDistrictId(@PathVariable("id") String districtId) {
        List<Local> locales = localService.getLocalesByDistrictId(districtId);
        if (!locales.isEmpty()) {
            return new ResponseEntity<>(locales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/local/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable("id") String id) {
        Local local = localService.getLocalById(id);
        if (local != null) {
            return new ResponseEntity<>(local, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
