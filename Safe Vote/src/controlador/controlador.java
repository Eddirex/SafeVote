/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.conexion;
import modelo.persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brian
 */
public class controlador {
     private conexion conexion;
     private int rut;
    
    public controlador(){
        this.setConexion(conexion.nuevaInstancia());
    }

    public conexion getConexion() {
        return conexion;
    }

    public void setConexion(conexion conexion) {
        this.conexion = conexion;
    }
    
    public persona buscarPersona(int rut){
        persona p = null;
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_select_per_all_bus(?)"
                    );
            pstm.setInt(1,rut);
            ResultSet rs = pstm.executeQuery();
            if (rs.first()) {
                p = new persona(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDate(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getString(8)
                        
                        
                );
   
            }
        
        }
    catch(ClassNotFoundException e){
        System.out.println("Clase no encontrada"+e.getMessage());
        
     }
    catch(SQLException e){
        System.out.println(String.format("Error de SQL mensaje %s y codigo %s ",e.getMessage(),e.getSQLState()));
    
    }
     catch(Exception e){
        System.out.println("Error general"+e.getMessage());
        
    
    
    }
    
        return p;
}
}
  
        
    
    

