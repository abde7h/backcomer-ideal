package com.backcomerideal.service;

import com.backcomerideal.model.Local;

import java.util.List;


public interface LocalService {

    List<Local> getLocalesByDistrict(String districtId);

    List<Local> getAllLocales();

    Local getLocalById(String id);

    List<Local> getLocalesByActivityType(String activityType);
}
