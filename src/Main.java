import DATA.DBConnection;
import DATATYPES.Usuario;
import GUI.LoginWindow;
import GUI.MainWindow.Window;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
public class Main {
    public static void mai3n(String[] args){
        Connection conexion= DBConnection.Conectar();
        try(conexion){
            if(conexion!=null){
                SwingUtilities.invokeLater(LoginWindow::new);
            }else{
                throw new SQLException("No se pudo conectar con la base de datos");
            }
        }catch (SQLException e){
            if(JOptionPane.showConfirmDialog(null,"Esta seguro que desea continuar sin la base de datos?")==JOptionPane.YES_OPTION){
                SwingUtilities.invokeLater(LoginWindow::new);
            }else{
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        Usuario user=new Usuario(0,"Admin","123","Administrador");
        SwingUtilities.invokeLater(()->new Window(user));
    }
}
