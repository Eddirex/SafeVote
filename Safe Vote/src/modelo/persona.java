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
public class persona {
    private int rut;
    private String nombre_p;
    private String appaterno;
    private String apmaterno;
    private String fecha_nacimiento;
    private int num_documento;
    private String sexo;
    private String comuna;

    public persona() {
    }

    public persona(int rut, String nombre_p, String appaterno, String apmaterno, String fecha_nacimiento, int num_documento, String sexo, String comuna) {
        this.rut = rut;
        this.nombre_p = nombre_p;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.num_documento = num_documento;
        this.sexo = sexo;
        this.comuna = comuna;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre_p() {
        return nombre_p;
    }

    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(int num_documento) {
        this.num_documento = num_documento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }


    @Override
    public String toString() {
        return "persona{" + "rut=" + rut + ", nombre_p=" + nombre_p + ", appaterno=" + appaterno + ", apmaterno=" + apmaterno + ", fecha_nacimiento=" + fecha_nacimiento + ", num_documento=" + num_documento + ", sexo=" + sexo + ", comuna=" + comuna + '}';
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}