package DATA;
import DATATYPES.Usuario;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
public class Insert {
    public static boolean usuario(Usuario usuario){
        Connection conexion=DBConnection.Conectar();
        if(conexion!=null){
            try(conexion){
                String sql="INSERT INTO usuarios (id,nombre,apellido,passwordhash) VALUES(?,?,?,?)";
                PreparedStatement ps=conexion.prepareStatement(sql);
                ps.setDouble(1,usuario.getCedula());
                ps.setString(2,usuario.getNombre());
                ps.setString(3,usuario.getApellido());
                ps.setString(4,usuario.getPassword());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Usuario creado con éxito, por favor inicie sesión");
                return true;
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Error al crear el usuario: "+e.getMessage());
            }
        }
        return false;
    }


}
