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
        List<Local> localesDistrtrito = local.findAll()
                .stream().
                filter(local -> local.getDistrictId() == Integer.parseInt(districtId))
                .toList();
        return localesDistrtrito;
    }

    @Override
    public List<Local> getAllLocales() {
        System.out.println("localserviceIMPL");
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
