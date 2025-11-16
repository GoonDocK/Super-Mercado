package DATA;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/supermercado";
    private static final String USER = "root";
    private static final String PASSWORD = "312540987";
    public static Connection Conectar(){
        try{
            Connection conexion=DriverManager.getConnection(URL,USER,PASSWORD);
            return conexion;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos: "+e.getMessage());
            return null;
        }
    }
}
