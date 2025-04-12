package com.brandon.webapp.proyecto.models.entities;
import java.time.LocalDate;

//5️⃣ Esta clase modelo o entidad, representa la tabla AEROLINEA.
//Este archivo describe:
//Qué atributos tiene una aerolínea.
//Cómo se representa con objetos en Java.


public class Aerolinea {
    private Long id;
    private String nombre;
    private String iata;
    private Estatus estatus;
    private String pais;
    private LocalDate fechaFundacion;

    //Constructor vacio
    public Aerolinea(){}

    //Constructor
    public Aerolinea(Long id, String nombre, String iata, Estatus estatus, String pais, LocalDate fechaFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.iata = iata;
        this.estatus = estatus;
        this.pais = pais;
        this.fechaFundacion = fechaFundacion;
    }


//    GETTERS Y SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    //To String
    @Override
    public String toString() {
        return "Aerolinea ->" +
                "id: " + id +
                ", nombre='" + nombre + '\'' +
                ", iata='" + iata + '\'' +
                ", estatus=" + estatus +
                ", pais='" + pais + '\'' +
                ", fechaFundacion=" + fechaFundacion +
                '}';
    }

}
