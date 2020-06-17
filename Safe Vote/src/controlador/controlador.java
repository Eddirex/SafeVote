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
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.comuna_region;

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
        int id_opc;
        String opcion;
        id_opc = 1;
        opcion = "null";
        
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_nombre_votacion()"
                    );
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                v = new votacion(
                        

                id_opc, 
                rs.getString(1),
                opcion         
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
                    "call sql_votacion_todo_ser_nombre("+nombre+")"

                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                o = new votacion(
                        
                rs.getInt(1), 
                rs.getString(2),
                rs.getString(3)         
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
    public void GuardarVoto(int opc_voto,int p_urna,String fecha,int p_rut){
        
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_insert_voto("+opc_voto+","+p_urna+",'"+fecha+"',"+p_rut+")"
                    );
            ResultSet rs = pstm.executeQuery();

        
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
    

}
    public votacion buscarOpc(String nombre_opcion){    
        votacion v = null;
        String nombre;
        String opcion;
        opcion = "null";
        nombre = ("'"+nombre_opcion+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_votacion_opc_nomb("+nombre+")"

                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                v = new votacion(
                        
                rs.getInt(1), 
                opcion,
                rs.getString(2)         
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
         return v;
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
            
        public ArrayList Buscar_com(String nombre_cr){
        ArrayList lista = new ArrayList<>();    
        comuna_region g = null;
        String nombre;
        nombre = ("'"+nombre_cr+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "select nombre_comuna from COMUNA where nombre_comuna =("+nombre+")"                            
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new comuna_region(                         
                rs.getString(1)        
                );
                
            lista.add(g);   
            }        
        }
    catch(ClassNotFoundException e){
        System.out.println("Clase no encontrada"+e.getMessage());}
    catch(SQLException e){        
        System.out.println(String.format("Error de SQL mensaje %s y codigo %s ",e.getMessage(),e.getSQLState()));}
    catch(Exception e){         
        System.out.println("Error general"+e.getMessage());}
        return lista;
        
}
        public ArrayList listar_com(){
        ArrayList lista = new ArrayList<>();    
        comuna_region g = null;
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "select nombre_comuna from COMUNA"
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new comuna_region(                         
                rs.getString(1)        
                );
                
            lista.add(g);   
            }        
        }
    catch(ClassNotFoundException e){
        System.out.println("Clase no encontrada"+e.getMessage());}
    catch(SQLException e){        
        System.out.println(String.format("Error de SQL mensaje %s y codigo %s ",e.getMessage(),e.getSQLState()));}
    catch(Exception e){         
        System.out.println("Error general"+e.getMessage());}
        return lista;
        
}
        public ArrayList Buscar_reg(String nombre_cr){
        ArrayList lista = new ArrayList<>();    
        comuna_region g = null;
        String nombre;
        nombre = ("'"+nombre_cr+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "select nombre_region from REGION where nombre_region =("+nombre+")"                            
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new comuna_region(                         
                rs.getString(1)        
                );
                
            lista.add(g);   
            }        
        }
    catch(ClassNotFoundException e){
        System.out.println("Clase no encontrada"+e.getMessage());}
    catch(SQLException e){        
        System.out.println(String.format("Error de SQL mensaje %s y codigo %s ",e.getMessage(),e.getSQLState()));}
    catch(Exception e){         
        System.out.println("Error general"+e.getMessage());}
        return lista;
        
}
        public ArrayList listar_reg(){
        ArrayList lista = new ArrayList<>();    
        comuna_region g = null;
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "select nombre_region from REGION"
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new comuna_region(                         
                rs.getString(1)        
                );
                
            lista.add(g);   
            }        
        }
    catch(ClassNotFoundException e){
        System.out.println("Clase no encontrada"+e.getMessage());}
    catch(SQLException e){        
        System.out.println(String.format("Error de SQL mensaje %s y codigo %s ",e.getMessage(),e.getSQLState()));}
    catch(Exception e){         
        System.out.println("Error general"+e.getMessage());}
        return lista;
        
}
  //G R A F I C O S ******************************************************************* by Edmundo Flores      
        public ArrayList Grafico_Sex_Com(String nombre_votacion,String nombre_comuna){
        ArrayList lista = new ArrayList<>();    
        votacion g = null;
        String nombre, nombre2;
        nombre = ("'"+nombre_votacion+"'");
        nombre2 =("'"+nombre_comuna+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_cant_sex_com("+nombre+","+nombre2+")"
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new votacion(
                        
                rs.getInt(2), 
                rs.getString(1),
                rs.getString(3)         
                );
                
            lista.add(g);
   
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
        public ArrayList Grafico_edad_Reg(String nombre_votacion,String nombre_region){
        ArrayList lista = new ArrayList<>();    
        votacion g = null;
        String nombre, nombre2;
        nombre = ("'"+nombre_votacion+"'");
        nombre2 =("'"+nombre_region+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_cant_edad_reg("+nombre+","+nombre2+")"
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new votacion(
                        
                rs.getInt(3), 
                rs.getString(2),
                rs.getString(1)  
      
                );
                
            lista.add(g);
   
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
        public ArrayList Grafico_Sex_Reg(String nombre_votacion,String nombre_region){
        ArrayList lista = new ArrayList<>();    
        votacion g = null;
        String nombre, nombre2;
        nombre = ("'"+nombre_votacion+"'");
        nombre2 =("'"+nombre_region+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_cant_sex_reg("+nombre+","+nombre2+")"
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new votacion(
                        
                rs.getInt(2), 
                rs.getString(1),
                rs.getString(3)         
                );
                
            lista.add(g);
   
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
        public ArrayList Grafico_edad_Com(String nombre_votacion,String nombre_region){
        ArrayList lista = new ArrayList<>();    
        votacion g = null;
        String nombre, nombre2;
        nombre = ("'"+nombre_votacion+"'");
        nombre2 =("'"+nombre_region+"'");
        try{
            PreparedStatement pstm = this.getConexion().obtenerConexion()
                    .prepareStatement(
                    "call sql_cant_edad_com("+nombre+","+nombre2+")"
                    );           
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                g = new votacion(
                        
                rs.getInt(3), 
                rs.getString(2),
                rs.getString(1)  
      
                );
                
            lista.add(g);
   
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


  
        
    
    

