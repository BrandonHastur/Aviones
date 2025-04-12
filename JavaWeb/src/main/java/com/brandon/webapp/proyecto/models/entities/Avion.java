package com.brandon.webapp.proyecto.models.entities;
import java.time.LocalDate;

public class Avion {
    private Long id;
    private String numRegistro;
    private String tipo;
    private String codigoModelo;
    private int capacidad;
    private LocalDate fechaPrimerVuelo;
    private Estatus estatus;
    private Aerolinea aerolinea;

    public Avion(){}
    //constructor
    public Avion(Long id, String numRegistro, String tipo, String codigoModelo, int capacidad, LocalDate fechaPrimerVuelo, Estatus estatus, Aerolinea aerolinea) {
        this.id = id;
        this.numRegistro = numRegistro;
        this.tipo = tipo;
        this.codigoModelo = codigoModelo;
        this.capacidad = capacidad;
        this.fechaPrimerVuelo = fechaPrimerVuelo;
        this.estatus = estatus;
        this.aerolinea = aerolinea;
    }


    //Geter,seter,to string


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(String codigoModelo) {
        this.codigoModelo = codigoModelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public LocalDate getFechaPrimerVuelo() {
        return fechaPrimerVuelo;
    }

    public void setFechaPrimerVuelo(LocalDate fechaPrimerVuelo) {
        this.fechaPrimerVuelo = fechaPrimerVuelo;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id=" + id +
                ", numRegistro='" + numRegistro + '\'' +
                ", tipo='" + tipo + '\'' +
                ", codigoModelo='" + codigoModelo + '\'' +
                ", capacidad=" + capacidad +
                ", fechaPrimerVuelo=" + fechaPrimerVuelo +
                ", estatus=" + estatus +
                ", aerolinea=" + aerolinea +
                '}';
    }
}
