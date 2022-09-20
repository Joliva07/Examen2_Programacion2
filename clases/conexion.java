/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Josue
 */
public class conexion {
    public Connection conexionBD;
    public final String bd="db_examen";
    public final String urlConexion=String.format("jdbc:mysql://localhost:3306/%s",bd);
    public final String usuario="usr_examen";
    public final String contra="examen123.";
    public final String jdbc="conm.mysql.cj.jdbc.Driver";
    
    
    public void ab_con(){
        try{
            Class.forName(jdbc);
            conexionBD=DriverManager.getConnection(urlConexion,usuario,contra);
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            System.out.println("error "+ex.getMessage());
        }
    }
    public void ce_con(){
        try{
            conexionBD.close();
        }catch(SQLException ex){
            System.out.println("Error..."+ex.getMessage());
        }
    }
    
}
