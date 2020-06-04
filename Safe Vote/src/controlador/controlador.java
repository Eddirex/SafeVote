/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import modelo.persona;
import modelo.votacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    public DefaultTableModel buscarPersonas(String buscar) throws ClassNotFoundException
    {

        int contador = 1; // Dedicado para acomular en número de registros que hay en la tabla
        
        String []  nombresColumnas = {"rut","nombre_p"};//Indica el nombre de las columnas en la tabla
        
        String [] registros = new String[2];
        
        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
        
        String sql = "SELECT * FROM PERSONA WHERE rut LIKE '%"+buscar+"%'";
        
        Connection cn = null;
        
        PreparedStatement pst = null;
        
        ResultSet rs = null;                           
        
        try
        {
            cn = conexion.obtenerConexion();
            
            pst = cn.prepareStatement(sql);                        
            
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                registros[0] = Integer.toString(contador);
                
                registros[1] = rs.getString("rut");
                
                registros[2] = rs.getString("nombre_p");                               
                
                modelo.addRow(registros);
                
                contador++;
                
            }                      
        }
        catch(SQLException e)
        {
            
            JOptionPane.showMessageDialog(null,"Error al conectar. "+e.getMessage());
            
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                
                if (pst != null) pst.close();
                
                if (cn != null) cn.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
         return modelo;
    }    

    public String agregarSupervisor(int rut, String nombre, int salaId) throws ClassNotFoundException {
        try {
            Connection bd = conexion.obtenerConexion();
            Statement stmt = bd.createStatement();
            String query = "INSERT INTO ENCARGADO " +
                            "(id_cargo, rut, nombre_p, id_sala) " +
                            "VALUES (" + 
                            "2," +
                            "" + rut + "," +
                            "'" + nombre + "'," +
                            "" + salaId  +
                            ")";
            System.out.println(query);
            stmt.executeUpdate(query);
            return "Supervisor agregado correctamente";
            
        } catch(SQLException e ){
            System.out.println("Error:" + e);
            return "No se pudo agregar un Supervisor";
        }
    }
    
    public String eliminarSupervisor(int rutSupervisor){
    
    int Confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al supervisor seleccionado?");
     
        if (Confirmar==JOptionPane.OK_OPTION) {
            
            try {
                String sql = "DELETE FROM ENCARGADO WHERE rut=?";
                Connection bd = conexion.obtenerConexion();
                
                PreparedStatement pst = bd.prepareStatement(sql);
                pst.setInt(1,rutSupervisor);
                
                if (pst.executeUpdate()>0) {
                    
                    JOptionPane.showMessageDialog(null, "El supervisor fue eliminado correctamente");
                    
                }else{
                    
                    JOptionPane.showMessageDialog(null, "El supervisor no ha podido ser eliminado");
                    
                }
            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                
                    JOptionPane.showMessageDialog(null, ""+e);
           
            } finally{
                if (conexion != null) {
                    
                    try {
                        
                     conexion.cerrarConexion();
                        
                        
                    } catch (Exception e) {
                        
                    JOptionPane.showMessageDialog(null, "La conexion no ha podido ser cerrada");
                    }
                    
                }
           
           
            
            
            
            
            }
            
        }
         return null;
    
    }

    public String editarSupervisor(int rut,int salaId) throws ClassNotFoundException, SQLException{
        try {
        Connection bd = conexion.obtenerConexion();
        Statement stmt = bd.createStatement();
        String query = "UPDATE ENCARGADO SET id_sala WHERE rut =?";
            System.out.println(query);
            stmt.executeUpdate(query);
            return "Supervisor editado correctamente correctamente";
            
            } catch(SQLException e ){
            System.out.println("Error:" + e);
            return "No se pudo agregar un Supervisor";
        }
    
    
    
    
    }
    
    public int ValidarEdad(){
            ResultSet rs;
            PreparedStatement stm = null;
            int edad = 0;

            
            try {
                rs = stm.executeQuery("SELECT DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(fecha_nacimiento)), '%Y')+"
                    + "0 AS EDAD FROM Safe_Vote.PERSONA WHERE RUT = "+rut+";");
                
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




  
        
    
    

