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
    private int id_opcion;
    private String nombre_votacion;
    private String opcion_voto;

    public votacion() {
    }

    public votacion(int id_opcion, String nombre_votacion, String opcion_voto) {
        this.id_opcion = id_opcion;
        this.nombre_votacion = nombre_votacion;
        this.opcion_voto = opcion_voto;
    }

    public int getId_opcion() {
        return id_opcion;
    }

    public void setId_opcion(int id_opcion) {
        this.id_opcion = id_opcion;
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

    

    
    
}


