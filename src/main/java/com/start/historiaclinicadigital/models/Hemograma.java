package com.start.historiaclinicadigital.models;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Hemograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float globulos_blancos;
    private float globulos_rojos;
    private float plaquetas;

    @OneToOne
    @JoinColumn(name = "hc_id")
    private HistoriaClinica historiaClinica;

    public Hemograma() {
    }

    public Hemograma(float globulos_blancos, float globulos_rojos, float plaquetas, HistoriaClinica historiaClinica) {
        this.globulos_blancos = globulos_blancos;
        this.globulos_rojos = globulos_rojos;
        this.plaquetas = plaquetas;
        this.historiaClinica = historiaClinica;
    }

    public long getId() {
        return id;
    }

    public float getGlobulos_blancos() {
        return globulos_blancos;
    }

    public void setGlobulos_blancos(float globulos_blancos) {
        this.globulos_blancos = globulos_blancos;
    }

    public float getGlobulos_rojos() {
        return globulos_rojos;
    }

    public void setGlobulos_rojos(float globulos_rojos) {
        this.globulos_rojos = globulos_rojos;
    }

    public float getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(float plaquetas) {
        this.plaquetas = plaquetas;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public Map<String,Object> hemogramaDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("globulosBlancos",this.getGlobulos_blancos());
        dto.put("globulosRojos", this.getGlobulos_rojos());
        dto.put("plaquetas",this.getPlaquetas());
        return dto;
    }
}
