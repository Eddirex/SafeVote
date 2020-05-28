/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Eddie
 */
public class voto {
    private String opcion_voto;
    private String fecha;
    private int urna;

    public voto(String opcion_voto, String fecha, int urna) {
        this.opcion_voto = opcion_voto;
        this.fecha = fecha;
        this.urna = urna;
    }

    public String getOpcion_voto() {
        return opcion_voto;
    }

    public void setOpcion_voto(String opcion_voto) {
        this.opcion_voto = opcion_voto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUrna() {
        return urna;
    }

    public void setUrna(int urna) {
        this.urna = urna;
    }
    
}


