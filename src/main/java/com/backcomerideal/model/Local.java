package com.backcomerideal.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocal;
    private String nombreLocal;
    private int idDistrito;
    private String nomDistrito;
    private int idBarri;
    private String nomBarri;
    private boolean disponibilidad;
    private String tipoActividad;
    private int numeroTiendasPorDistrito;
    private float longitud;
    private float latitud;
    private float precioAlquilerMes;
    private float precioVenta;

    // Constructor vacío (necesario para JPA)
    public Local() {
    }

    // Constructor con todos los campos
    public Local(int idLocal, String nombreLocal, int idDistrito, String nomDistrito, int idBarri, String nomBarri,
                 boolean disponibilidad, String tipoActividad, int numeroTiendasPorDistrito, float longitud,
                 float latitud, float precioAlquilerMes, float precioVenta) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.idDistrito = idDistrito;
        this.nomDistrito = nomDistrito;
        this.idBarri = idBarri;
        this.nomBarri = nomBarri;
        this.disponibilidad = disponibilidad;
        this.tipoActividad = tipoActividad;
        this.numeroTiendasPorDistrito = numeroTiendasPorDistrito;
        this.longitud = longitud;
        this.latitud = latitud;
        this.precioAlquilerMes = precioAlquilerMes;
        this.precioVenta = precioVenta;
    }

    // Getters y setters para cada campo
    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNomDistrito() {
        return nomDistrito;
    }

    public void setNomDistrito(String nomDistrito) {
        this.nomDistrito = nomDistrito;
    }

    public int getIdBarri() {
        return idBarri;
    }

    public void setIdBarri(int idBarri) {
        this.idBarri = idBarri;
    }

    public String getNomBarri() {
        return nomBarri;
    }

    public void setNomBarri(String nomBarri) {
        this.nomBarri = nomBarri;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public int getNumeroTiendasPorDistrito() {
        return numeroTiendasPorDistrito;
    }

    public void setNumeroTiendasPorDistrito(int numeroTiendasPorDistrito) {
        this.numeroTiendasPorDistrito = numeroTiendasPorDistrito;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getPrecioAlquilerMes() {
        return precioAlquilerMes;
    }

    public void setPrecioAlquilerMes(float precioAlquilerMes) {
        this.precioAlquilerMes = precioAlquilerMes;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
}
