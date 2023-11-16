package com.backcomerideal.service;

import com.backcomerideal.model.Local;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceIMPL implements LocalService{


    @Override
    public List<Local> getLocalesByDistrictId(String districtId) {
        //Mostrar locales filtrado por distrito
        return null;
    }

    @Override
    public List<Local> getAllLocales() {
        return null;
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
