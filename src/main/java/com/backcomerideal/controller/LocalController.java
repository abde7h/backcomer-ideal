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

    private final LocalService localService;

    @Autowired
    public LocalController(LocalService localService) {
        this.localService = localService;
    }


    @GetMapping
    public ResponseEntity<List<Local>> getAllLocales() {
        List<Local> locales = localService.getAllLocales();
        return new ResponseEntity<>(locales, HttpStatus.OK);
    }

    @GetMapping("/distrito/{id}")
    public ResponseEntity<List<Local>> getLocalesByDistrictId(@PathVariable("id") int districtId) {
        List<Local> locales = localService.getLocalesByDistrictId(districtId);
        if (!locales.isEmpty()) {
            return new ResponseEntity<>(locales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable("id") String id) {
        Local local = localService.getLocalById(id);
        if (local != null) {
            return new ResponseEntity<>(local, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Local> addLocal(@RequestBody Local local) {
        Local createdLocal = localService.addLocal(local);
        return new ResponseEntity<>(createdLocal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@PathVariable("id") String id, @RequestBody Local local) {
        Local updatedLocal = localService.updateLocal(id, local);
        if (updatedLocal != null) {
            return new ResponseEntity<>(updatedLocal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable("id") String id) {
        boolean deleted = localService.deleteLocal(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
