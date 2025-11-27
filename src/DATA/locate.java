package DATA;
import DATATYPES.Producto;
import DATATYPES.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class locate {
    public static Usuario User(double Cedula, String password){
        String sql="SELECT id, nombre, apellido, passwordhash FROM usuarios WHERE id=?";
        try(Connection conexion=DBConnection.Conectar(); PreparedStatement ps= conexion.prepareStatement(sql)){
                ps.setDouble(1,Cedula);
                try(ResultSet rs=ps.executeQuery()){
                    if(!rs.next()){
                      return null;
                    }
                    String hash=rs.getString("passwordhash");
                    if(hash==null) return null;
                    if(BCrypt.checkpw(password, hash)){
                        return new Usuario(rs.getDouble("id"),rs.getString("nombre"),rs.getString( "apellido"), rs.getString("passwordhash"));
                    }else{
                        return null;
                    }
                }
            }catch(SQLException e){
                return null;
            }
        }
    public static Producto Producto(double id) {
        String sql = "SELECT id, nombre, precio FROM productos WHERE id=?";
        try (Connection conexion = DBConnection.Conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                if (rs.getLong("id") == id) {
                    return new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"));
                } else {
                    return null;
                }

            } catch (SQLException e) {
                return null;
            }
        } catch (Exception e){
        return null;
        }
    }
    public static ArrayList<Object[]> ventas(){
        String sql="SELECT * FROM ventas";
        try(Connection conexion=DBConnection.Conectar(); PreparedStatement ps=conexion.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            ArrayList<Object[]> lista=new ArrayList<>();
            while(rs.next()){
                lista.add(new Object[]{rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5)});
            }
            return lista;
        }catch(SQLException e){
            return null;
        }
    }

}


