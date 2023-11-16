package com.backcomerideal.service;

import com.backcomerideal.model.Local;

import java.util.List;

public interface LocalService {
    public List<Local> getLocalesPorDistrito( String districtId);
    public List<Local> getLocalesPorTipo(String activityType);

}
