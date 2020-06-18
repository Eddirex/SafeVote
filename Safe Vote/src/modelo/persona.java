/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eddie
 */
public class persona {
    private int rut;
    private String nombre_p;
    private String appaterno;
    private String apmaterno;
    private Date fecha_nacimiento;
    private int num_documento;
    private String sexo;
    private String comuna;

    public persona() {
    }

    public persona(int rut, String nombre_p, String appaterno, String apmaterno, Date fecha_nacimiento, int num_documento, String sexo, String comuna) {
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
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
    
    public int ExtraerEdad(int rut){
            ResultSet rs;
            PreparedStatement stm = null;
            int edad = 0;

            
            try {
                rs = stm.executeQuery("SELECT DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(fecha_nacimiento)), '%Y')+0 AS EDAD FROM Safe_Vote.PERSONA WHERE rut = '"+rut+"';");
                System.out.println(edad);
                
                while (rs.next()) {
                    edad = rs.getInt(1);
                }
            }
              catch(SQLException e){
                System.out.println(String.format("Error de SQL mensaje %s y codigo %s ",e.getMessage(),e.getSQLState()));
    
            } catch (Exception e) {
                System.out.println("Error general"+e.getMessage());
            }
         return edad;
            
        }
    
}