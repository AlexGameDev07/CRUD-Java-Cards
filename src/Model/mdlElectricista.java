/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Cards.cardElectricistas;
import View.JfrmDashBoardElectricista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Lenovo V-14
 */
public class mdlElectricista {
    int id;
    String Nombre;
    String Apellido;
    int edad;
    float peso;
    String correo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void Mostrar(JfrmDashBoardElectricista view){
        var panelCards = view.jpCards;
        panelCards.removeAll();
        List<mdlElectricista> electricistasList = new ElectricistaDAO().CargarElectricista();
        for(mdlElectricista objElectricista: electricistasList){
            cardElectricistas card = new cardElectricistas(objElectricista, view); // Pasar panelCards
            panelCards.add(card);
        }
        panelCards.revalidate(); // Actualizar el panel visualmente
        panelCards.repaint();    // Redibujar el panel
    }

    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.Connect();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbElectricistas(Nombre_Electricista, Apellido_Electricista, Edad_Electricista, Peso_Electricista, Correo_Electricista)" +
                "VALUES (?, ?, ?, ?,?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement statement = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            statement.setString(1, getNombre());
            statement.setString(2, getApellido());
            statement.setInt(3, getEdad());
            statement.setDouble(4, getPeso());
            statement.setString(5, getCorreo());

            //Ejecutar la consulta
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }    
    
    public void Actualizar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.Connect();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "UPDATE tbElectricistas "
                    + "SET Nombre_Electricista = ?, "
                    + "Apellido_Electricista = ?, "
                    + "Edad_Electricista = ?, "
                    + "Peso_Electricista = ?, "
                    + "Correo_Electricista = ? "
                    + "WHERE ID_Electricista = ?";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement statement = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            statement.setString(1, getNombre());
            statement.setString(2, getApellido());
            statement.setInt(3, getEdad());
            statement.setDouble(4, getPeso());
            statement.setString(5, getCorreo());
            statement.setInt(6, getId());

            //Ejecutar la consulta
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo: metodo actualizar " + ex);
        }
    }  
    
    public void Eliminar(){
        Connection conexion = ClaseConexion.Connect();
        try{
            String sql = "DELETE FROM tbElectricistas WHERE ID_Electricista = ?";
            String commit = "COMMIT";
            PreparedStatement statement = conexion.prepareStatement(sql);
            PreparedStatement stmcommit = conexion.prepareStatement(commit);
            statement.setInt(1, getId());
            statement.executeUpdate();
            stmcommit.executeUpdate();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void Limpiar(JfrmDashBoardElectricista view){
        view.jbtnAgregar.setText("Agregar");
        view.jsEdad.setValue(0);
        view.jsPeso.setValue(0);
        view.txtApellido.setText("");
        view.txtEmail.setText("");
        view.txtNombre.setText("");
        
    }
}
