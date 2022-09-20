/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Josue
 */
public class estudiante extends Persona {
    conexion cn = new conexion();
    
      private String carnet;
    private int id;

    public estudiante() {
    }
    public estudiante(String carnet, int id, String nombres, String apellidos, String direccion, String telefono, String genero, String email, String fecha_n) {
        super(nombres, apellidos, direccion, telefono, genero, email, fecha_n);
        this.carnet = carnet;
        this.id = id;
    }
    
    public String getCarnet() {
        return carnet;
    }
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            String cons="INSERT INTO estudiantes (carnet, nombres, apellidos, direccion, telefono, genero, email, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            cn.ab_con();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(cons);
            parametro.setString(1, this.getCarnet());
            parametro.setString(2,this.getNombres());
            parametro.setString(3,this.getApellidos());
            parametro.setString(4,this.getDireccion());
            parametro.setString(5,this.getTelefono());
            parametro.setString(6,this.getGenero());
            parametro.setString(7,this.getEmail());
            parametro.setString(8,this.getFecha_n());
            int ejecutar = parametro.executeUpdate();
            cn.ce_con();
            JOptionPane.showMessageDialog(null,Integer.toString(ejecutar)+ " registro ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }
    }
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn.ab_con();
            String query="SELECT id_estudiante as id,carnet,nombres, apellidos,direccion,telefono,case when genero=1 then \"hombre\" when genero=0 then \"mujer\" end as genero,email,fecha_nacimiento FROM estudiantes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String columna[]={"Id","Carnet","Nombres","Apellidos","Direccion","Telefono","Genero","Email","Fecha de Nacimiento"};
            tabla.setColumnIdentifiers(columna);
            String datos[]=new String[9];
            
            while(consulta.next()){
                datos[0]=consulta.getString("id");
                datos[1]=consulta.getString("carnet");
                datos[2]=consulta.getString("nombres");
                datos[3]=consulta.getString("apellidos");
                datos[4]=consulta.getString("direccion");
                datos[5]=consulta.getString("telefono");
                datos[6]=consulta.getString("genero");
                datos[7]=consulta.getString("email");
                datos[8]=consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
            cn.ce_con();
            
        }catch(SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }
        return tabla;
    }
    @Override
    public void actualizar(){
        try{
            PreparedStatement parametro;
            String cons="UPDATE estudiantes SET carnet = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, genero = ?, email = ?, fecha_nacimiento = ?"+"WHERE id_estudiante = ?;";
            cn.ab_con();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(cons);
            parametro.setString(1, this.getCarnet());
            parametro.setString(2, this.getNombres());
            parametro.setString(3, this.getApellidos());
            parametro.setString(4, this.getDireccion());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getGenero());
            parametro.setString(7, this.getEmail());
            parametro.setString(8, this.getFecha_n());
            parametro.setInt(9, this.getId());
            int ejecutar = parametro.executeUpdate();
            cn.ce_con();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Actualizado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }
    }
    @Override
    public void eliminar(){
        try{
            PreparedStatement parametro;
            String query="delete from estudiantes where id_estudiante = ?;";
            cn.ab_con();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getId());
            int ejecutar = parametro.executeUpdate();
            cn.ce_con();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Eliminado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex){
            System.out.println("Error"+ex.getMessage());
        }
    }
}
