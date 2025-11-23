package DATA;
import DATATYPES.*;
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
    public static boolean Producto(Producto producto){
        Connection conexion=DBConnection.Conectar();
        if(conexion!=null){
            try(conexion){
                String sql="INSERT INTO productos (id,nombre,precio) VALUES(?,?,?)";
                PreparedStatement ps=conexion.prepareStatement(sql);
                ps.setLong(1,producto.getId());
                ps.setString(2,producto.getNombre());
                ps.setDouble(3,producto.getPrecio());
                ps.executeUpdate();
                return true;
            }catch (SQLException e){
                return false;
            }
        }
        return false;
    }



}
