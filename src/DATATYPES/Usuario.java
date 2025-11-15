package DATATYPES;
import org.mindrot.jbcrypt.BCrypt;
public class Usuario {
    private String cedula;
    private String nombre;
    private String apellido;
    private String password;
    public Usuario(String cedula, String nombre, String apellido, String password){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public String getCedula() {
        return cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getPassword() {
        return password;
    }
}
