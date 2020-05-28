/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.conexion;
import modelo.persona;
import modelo.votacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        public ArrayList<votacion>buscarVotacion(){
        ArrayList lista = new ArrayList<>();    
        votacion v = null;
        int urna;
        String opcion;
        urna = 1;
        opcion = "null";
        
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_nombre_votacion()"
                    );
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                v = new votacion(
                        

                rs.getString(1),
                opcion,
                urna          
                );
                
            lista.add(v);
   
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
         return lista;

    
}
        
        public ArrayList buscarOpcion(String nombre_votacion){
        ArrayList lista = new ArrayList<>();    
        votacion o = null;
        String nombre;
        nombre = ("'"+nombre_votacion+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "sql_votacion_todo_ser_nombre("+nombre+")"
            //si colocas call sql_votacion_todo_ser_nombre('Presidencial 2020') FUNCIONA
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                o = new votacion(

                rs.getString(2),
                rs.getString(3),
                rs.getInt(1)          
                );
                
            lista.add(o);
   
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
         return lista;

    
}
}


  
        
    
    

