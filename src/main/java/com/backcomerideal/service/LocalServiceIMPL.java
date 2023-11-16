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
    public List<Local> getLocalesByDistrict(String district) {
        //Mostrar locales filtrado por distrito
        return local.findAll()
                .stream().
                filter(local1 -> local1.getDistrict().equalsIgnoreCase(district))
                .toList();
    }

    @Override
    public List<Local> getAllLocales() {
        return local.findAll();
    }

    @Override
    public Local getLocalById(String id) {
        return local.findById(id).orElse(null);
    }

    @Override
    public List<Local> getLocalesByActivityType(String activityType) {

        return local.findAll()
                .stream().
                        filter(local1 -> local1.getActivityType().equalsIgnoreCase(activityType))
                .toList();
    }
}
