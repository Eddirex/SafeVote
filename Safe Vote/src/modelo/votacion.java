/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Eddie
 */
public class votacion {
    private String nombre_votacion;
    private String opcion_voto;
    private int urna;

    public votacion() {
    }

    public votacion(String nombre_votacion, String opcion_voto, int urna) {
        this.nombre_votacion = nombre_votacion;
        this.opcion_voto = opcion_voto;
        this.urna = urna;
    }

    public String getNombre_votacion() {
        return nombre_votacion;
    }

    public void setNombre_votacion(String nombre_votacion) {
        this.nombre_votacion = nombre_votacion;
    }

    public String getOpcion_voto() {
        return opcion_voto;
    }

    public void setOpcion_voto(String opcion_voto) {
        this.opcion_voto = opcion_voto;
    }


    public int getUrna() {
        return urna;
    }

    public void setUrna(int urna) {
        this.urna = urna;
    }

    
    
}


