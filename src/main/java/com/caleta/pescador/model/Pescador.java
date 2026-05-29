package com.caleta.pescador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pescadores")
public class Pescador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "licencia", nullable = false)
    private String licencia;

    @Column(name = "bote_id", nullable = false)
    private Long BoteId;

    @Column(name = "sindicato", nullable = false)
    private String sindicato;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Pescador() {}

    public Pescador(Long id, String nombre, String licencia, Long boteId, String sindicato, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.licencia = licencia;
        this.BoteId = boteId;
        this.sindicato = sindicato;
        this.activo = activo;
    }

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

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Long getBoteId() {
        return BoteId;
    }

    public void setBoteId(Long boteId) {
        BoteId = boteId;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


}
