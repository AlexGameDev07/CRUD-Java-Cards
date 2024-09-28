package Model;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ElectricistaDAO {
        
    List<mdlElectricista> electricistas = new ArrayList<>();
    public List<mdlElectricista> CargarElectricista(){
        Connection connection = new ClaseConexion().Connect();
        
        try{
            String sql = "SELECT ID_ELECTRICISTA AS ID, NOMBRE_ELECTRICISTA AS Nombre, APELLIDO_ELECTRICISTA AS Apellido, EDAD_ELECTRICISTA AS Edad, (PESO_ELECTRICISTA) AS Peso, CORREO_ELECTRICISTA AS Correo FROM TBELECTRICISTAS";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultset =   statement.executeQuery();
            
            while(resultset.next()){
                mdlElectricista elec = new mdlElectricista();
                elec.setId(resultset.getInt("Id"));
                elec.setNombre(resultset.getString("Nombre"));
                elec.setApellido(resultset.getString("Apellido"));
                elec.setEdad(resultset.getInt("Edad"));
                elec.setCorreo(resultset.getString("Correo"));
                elec.setPeso(resultset.getFloat("Peso"));
                
                System.out.println("id obtenido" + resultset.getInt("Id"));
                
                electricistas.add(elec);
            }
            
            resultset.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return electricistas;
    }
    
    
}