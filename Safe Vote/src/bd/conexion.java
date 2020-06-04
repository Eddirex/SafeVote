/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brian
 */
    public class conexion {
    
      private static conexion conexion;
      private Connection cone;

      public conexion() {
      }
   
    public static conexion nuevaInstancia(){
         if(conexion==null){
            return new conexion();
                        
         }
         
         return conexion;
    
    }
    
    private void abrirConexion() throws ClassNotFoundException, SQLException{
        if (this.cone == null || this.cone.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
        this.cone=DriverManager.getConnection(
                 "jdbc:mysql://168.138.149.15:3306/Safe_Vote",
                 "safe_vote",
                 "SafeVote__//1"
        );      
        }
    }
    
    public Connection obtenerConexion() throws ClassNotFoundException, SQLException{
        abrirConexion();
        return this.cone;
        
    }
    
    public void cerrarConexion(){
       try{
       
        if (!this.cone.isClosed()) {
            this.cone.close();           
       }
       
       }
       catch(SQLException e){
           System.out.println("Error de sql"+e.getMessage()+"Error de sql"+ e.getSQLState());
        
       }
            
    }
}
