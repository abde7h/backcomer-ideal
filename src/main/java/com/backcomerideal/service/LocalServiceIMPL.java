package com.backcomerideal.service;

import com.backcomerideal.model.Local;
import com.backcomerideal.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceIMPL implements LocalService{

@Autowired
public LocalRepository local;
    @Override
    public List<Local> getLocalesByDistrictId(String districtId) {
        //Mostrar locales filtrado por distrito
        return null;
    }

    @Override
    public List<Local> getAllLocales() {
        return local.findAll();
    }

    @Override
    public Local getLocalById(String id) {
        return null;
    }

    @Override
    public List<Local> getLocalesByActivityType(String activityType) {
        return null;
    }
}
