package GUI;
import AssetsHandler.IconScalling;
import DATA.locate;
import DATATYPES.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;
import GUI.MainWindow.Window;


public class LoginWindow extends JFrame{
    private JTextField Cedula;
    private static JPasswordField Contrasena;
    private static JCheckBox Mostrar;
    public LoginWindow(){
        //Configuración básica de la ventana
        setTitle("Iniciar Sesión");
        setSize(500,350);
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
        panelSuperior.setBackground(Colores.Azul);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel Iniciar= new JLabel("Iniciar Sesión");
        Iniciar.setFont(Fuentes.SourceSansPro18);
        Iniciar.setForeground(Color.WHITE);
        panelSuperior.add(Iniciar);

        add(panelSuperior,BorderLayout.NORTH);

        //Panel Central
        JPanel panelCentral = new JPanel(new GridLayout(4,2,20,20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelCentral.setBackground(Color.WHITE);

        JLabel CedulaLabel = new JLabel("Cédula:");
        CedulaLabel.setFont(Fuentes.SourceSansPro18);
        panelCentral.add(CedulaLabel);

        Cedula = new JTextField();
        Cedula.setFont(Fuentes.SourceSansPro18);
        panelCentral.add(Cedula);

        JLabel ContraLabel= new JLabel("Contraseña: ");
        ContraLabel.setFont(Fuentes.SourceSansPro18);
        panelCentral.add(ContraLabel);

        Contrasena = new JPasswordField();
        Contrasena.setFont(Fuentes.SourceSansPro18);
        panelCentral.add(Contrasena);

        JButton Ingresar = new JButton("Ingresar");
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(verificarCampos()){
                    validarUsuario(locate.User(Double.parseDouble(Cedula.getText()),Contrasena.getText()));
                }
            }
        });
        Ingresar.setIcon(IconScalling.scale("/Assets/Iconos/59802.png",30,30));
        Ingresar.setBackground(Colores.Azul);
        Ingresar.setForeground(Color.WHITE);
        Ingresar.setFont(Fuentes.SourceSansPro18);
        panelCentral.add(Ingresar);

        JButton Registrar = new JButton("Registrarse");
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(signInWindow::new);
                dispose();
            }
        });
        Registrar.setIcon(IconScalling.scale("/Assets/Iconos/304579.png",30,30));
        Registrar.setBackground(Colores.Azul);
        Registrar.setForeground(Color.WHITE);
        Registrar.setFont(Fuentes.SourceSansPro18);
        panelCentral.add(Registrar);

        Mostrar = new JCheckBox("Mostrar contraseña");
        Mostrar.setFont(Fuentes.SourceSansPro18);
        Mostrar.setBackground(Color.WHITE);
        panelCentral.add(Mostrar);
        char echo=Contrasena.getEchoChar();
        Mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MostrarContrasena(echo);
            }
        });

        add(panelCentral,BorderLayout.CENTER);

        //Visualizacion de la ventana
        setVisible(true);
    }
    //Metodo para mostrar/Ocultar la contraseña
    private static void MostrarContrasena(char echo){
        if(Mostrar.isSelected()){
            Contrasena.setEchoChar((char)0);
        }else{
            Contrasena.setEchoChar(echo);
        }
    }
    private boolean verificarCampos(){
        try{
            double cedula = Double.parseDouble(Cedula.getText());
            if(!Cedula.getText().isBlank() && !Contrasena.getText().isBlank()){
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"Por favor complete todos los campos");
                return false;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Por favor digite un número de cedula válido");
            return false;
        }
    }
    private void validarUsuario(Usuario user){
        if(user!=null){
            SwingUtilities.invokeLater(()->new Window(user));
            dispose();
        }else{
            JOptionPane.showMessageDialog(getContentPane(),"Usuario o contraseña incorrectos");
        }
    }
}
