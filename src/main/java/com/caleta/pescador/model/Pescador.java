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

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "licencia", nullable = false)
    private String licencia;

    @Column(name = "bote_id", nullable = false)
    private Long BoteId;

    @Column(name = "sindicato", nullable = false)
    private String sindicato;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Pescador() {}

    public Pescador(Long id, Long usuarioId, String licencia, Long boteId, String sindicato, boolean activo) {
        this.id = id;
        this.usuarioId = usuarioId;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public void setBoteId(Long BoteId) {
        this.BoteId = BoteId;
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

    