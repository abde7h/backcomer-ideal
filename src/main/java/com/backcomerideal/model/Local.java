package com.backcomerideal.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Local {
    @Id
    @Column(name="localId")
    private String localId;
    @Column(name="localName")
    private String localName;
    @Column(name="address")
    private String address;
    @Column(name="districtId")
    private int districtId;
    @Column(name="distric")
    private String district;
    @Column(name="isAvailability")
    private boolean isAvailability;
    @Column(name="activityType")
    private String activityType;
    @Column(name="totalDistrictStore")
    private int totalDistrictStore;
    @Column(name="longitude")
    private float longitude;
    @Column(name="latitude")
    private float latitude;
    @Column(name="rentalPrice")
    private float rentalPrice;
    @Column(name="salePrice")
    private float salePrice;

    // Constructor vacío (necesario para JPA)
    public Local() {
    }

    // Constructor con todos los campos
    public Local(String localId, String localName, String address, int districtId, String district,
                 boolean isAvailability, String activityType, int totalDistrictStore, float longitude,
                 float latitude, float rentalPrice, float salePrice) {
        this.localId = localId;
        this.localName = localName;
        this.address = address;
        this.districtId = districtId;
        this.district = district;
        this.isAvailability = isAvailability;
        this.activityType = activityType;
        this.totalDistrictStore = totalDistrictStore;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rentalPrice = rentalPrice;
        this.salePrice = salePrice;
    }

    // Getters y setters para cada campo
    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean isAvailability() {
        return isAvailability;
    }

    public void setAvailability(boolean availability) {
        isAvailability = availability;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getTotalDistrictStore() {
        return totalDistrictStore;
    }

    public void setTotalDistrictStore(int totalDistrictStore) {
        this.totalDistrictStore = totalDistrictStore;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }
}
