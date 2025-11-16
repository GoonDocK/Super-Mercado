package GUI;
import AssetsHandler.IconScalling;
import DATA.Insert;
import DATATYPES.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class signInWindow extends JFrame{
    private JTextField Nombre, Apellido, Cedula;
    private static JPasswordField Contrasena, RepetirContrasena;
    private static JCheckBox mostrarContra;
    private JTextArea Texto;
    private  Usuario usuario;
    public signInWindow() {
        setTitle("Crear Usuario");
        setSize(500,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        try{
            setIconImage(IconScalling.scale("/Assets/Iconos/5465865.png",64,64).getImage());
        }catch(NullPointerException e){
            System.out.println("No se pudo cargar el icono");
        }
        setLayout(new BorderLayout());

        //Panel Superior

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBackground(LoginWindow.Azul);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel Labelinciar= new JLabel("Crear Usuario");
        Labelinciar.setForeground(Color.WHITE);
        Labelinciar.setFont(LoginWindow.SourceSansPro18);
        panelSuperior.add(Labelinciar);

        add(panelSuperior,BorderLayout.NORTH);

        //Panel Central

        JPanel panelCentral = new JPanel(new GridLayout(7,2,15,15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelCentral.setBackground(Color.WHITE);

        JLabel NombreLabel= new JLabel("Nombre: ");
        NombreLabel.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(NombreLabel);

        Nombre = new JTextField();
        Nombre.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(Nombre);

        JLabel ApellidoLabel= new JLabel("Apellido: ");
        ApellidoLabel.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(ApellidoLabel);

        Apellido = new JTextField();
        Apellido.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(Apellido);

        JLabel LabelCedula = new JLabel("Cédula: ");
        LabelCedula.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(LabelCedula);

        Cedula = new JTextField();
        Cedula.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(Cedula);

        JLabel contrasena= new JLabel("Contraseña: ");
        contrasena.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(contrasena);

        Contrasena = new JPasswordField();
        Contrasena.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(Contrasena);

        JLabel repetirContrasena= new JLabel("Confirmar contraseña:");
        repetirContrasena.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(repetirContrasena);

        RepetirContrasena = new JPasswordField();
        RepetirContrasena.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(RepetirContrasena);

        JButton Ingresar = new JButton("Ingresar");
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(verificarCampos()){
                    Insert newInsert = new Insert();
                    if (Insert.usuario(usuario)) {
                        SwingUtilities.invokeLater(LoginWindow::new);
                        dispose();
                    }else{
                        Texto.setText("El usuario ya existe");
                    }

                }
            }
        });
        Ingresar.setIcon(IconScalling.scale("/Assets/Iconos/flechaverde.png",30,30));
        Ingresar.setBackground(LoginWindow.Azul);
        Ingresar.setForeground(Color.WHITE);
        Ingresar.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(Ingresar);

        JButton Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(LoginWindow::new);
                dispose();
            }
        });
        Cancelar.setIcon(IconScalling.scale("/Assets/Iconos/Salida.png",30,30));
        Cancelar.setBackground(LoginWindow.Azul);
        Cancelar.setForeground(Color.WHITE);
        Cancelar.setFont(LoginWindow.SourceSansPro18);
        panelCentral.add(Cancelar);

        mostrarContra= new JCheckBox("Mostrar Contraseña");
        char echo=Contrasena.getEchoChar();
        mostrarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MostrarContrasena(echo);
            }
        });
        mostrarContra.setFont(LoginWindow.SourceSansPro18);
        mostrarContra.setBackground(Color.WHITE);
        panelCentral.add(mostrarContra);

        Texto = new JTextArea();
        Texto.setEditable(false);
        Texto.setOpaque(false);
        Texto.setLineWrap(true);
        Texto.setWrapStyleWord(true);
        Texto.setFont(LoginWindow.SourceSansPro18);
        Texto.setForeground(Color.RED);
        panelCentral.add(Texto);

        add(panelCentral,BorderLayout.CENTER);
        setVisible(true);
    }
    private static void MostrarContrasena(char echo){
        if(mostrarContra.isSelected()){
            Contrasena.setEchoChar((char)0);
            RepetirContrasena.setEchoChar((char)0);
        }else{
            Contrasena.setEchoChar(echo);
            RepetirContrasena.setEchoChar(echo);
        }
    }
    private boolean verificarCampos(){
        if(!Nombre.getText().isBlank() && !Apellido.getText().isBlank() && !Cedula.getText().isBlank() && !Contrasena.getText().isBlank() && !RepetirContrasena.getText().isBlank()){
            Texto.setText(null);
            if(Arrays.equals(Contrasena.getPassword(), RepetirContrasena.getPassword())){
                if(verificarLonContra(Contrasena.getText())){
                    try{
                        usuario = new Usuario(Double.parseDouble(Cedula.getText()),Nombre.getText(),Apellido.getText(),Contrasena.getText());
                        return true;
                    }catch(NumberFormatException e){
                        Texto.setText("Su ID debe ser un número");
                        return false;
                    }
                }
                else{
                    Texto.setText("La contraseña debe tener al menos 8 caracteres");
                    return false;
                }
            }else{
                Texto.setText("Las contraseñas no coinciden");
                return false;
            }
        }else{
            Texto.setText("Por favor complete todos los campos");
            return false;
        }
    }
    private boolean verificarLonContra(String contra){
        return contra.length() >= 8;
    }
}
