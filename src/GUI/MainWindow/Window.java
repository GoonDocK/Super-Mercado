package GUI.MainWindow;
import AssetsHandler.IconScalling;
import DATATYPES.Usuario;
import FuenteYTipografia.*;
import GUI.LoginWindow;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Window extends JFrame{
    private CardLayout centro;
    private JPanel panelCentral;
    public Window(Usuario user){
        setTitle("Ventas");
        setSize(800,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        try{
            setIconImage(IconScalling.scale("/Assets/Iconos/5465865.png",64,64).getImage());
        }catch(NullPointerException e){
            System.out.println("No se pudo cargar el icono");
        }
        setLayout(new BorderLayout());

        //Implementacion del metodo para recibir el focus del teclado
        requestFocus();
        setFocusable(true);

        //Panel Superior
        JPanel panelSuperior = new JPanel(new GridLayout(1,3));
        panelSuperior.setBackground(Colores.Azul);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel UsuarioLabel= new JLabel("Cajero: "+user.getNombre()+" "+user.getApellido());
        UsuarioLabel.setIcon(IconScalling.scale("/Assets/Iconos/user.png",64,64));
        UsuarioLabel.setFont(Fuentes.SourceSansPro18);
        UsuarioLabel.setForeground(Color.WHITE);
        panelSuperior.add(UsuarioLabel);

        JLabel Titulo= new JLabel("Point of Selling UIS");
        Titulo.setFont(Fuentes.SourceSansPro18Bold);
        Titulo.setForeground(Color.WHITE);
        Titulo.setIcon(IconScalling.scale("/Assets/Iconos/5465865.png",64,64));
        panelSuperior.add(Titulo);

        JButton salir= new JButton("Cerrar Sesión");
        salir.setFont(Fuentes.SourceSansPro18);
        salir.setIcon(IconScalling.scale("/Assets/Iconos/Salida.png",64,64));
        salir.setBackground(Colores.Azul);
        salir.setForeground(Color.WHITE);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(LoginWindow::new);
                dispose();
            }
        });
        panelSuperior.add(salir);

        //Panel Central CardLayaout
        centro = new CardLayout();
        panelCentral = new JPanel(centro);
        panelCentral.setBackground(Color.WHITE);

        //Menu de inicio

        JPanel panelInicio = new JPanel();
        panelInicio.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelInicio.setLayout(new BoxLayout(panelInicio,BoxLayout.Y_AXIS));
        panelInicio.setBackground(Color.WHITE);

        JButton abrirVentas = new JButton("Iniciar Ventas");
        abrirVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                iniciarVentas();
            }
        });
        abrirVentas.setFont(Fuentes.SourceSansPro18);
        abrirVentas.setIcon(IconScalling.scale("/Assets/Iconos/Cesta.png",50,50));
        abrirVentas.setBackground(Colores.Azul);
        abrirVentas.setForeground(Color.WHITE);
        abrirVentas.setMaximumSize(new Dimension(500,50));
        abrirVentas.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInicio.add(abrirVentas);

        panelInicio.add(Box.createRigidArea(new Dimension(0,20)));

        JButton Ventas = new JButton("Ventas");
        Ventas.setFont(Fuentes.SourceSansPro18);
        Ventas.setIcon(IconScalling.scale("/Assets/Iconos/dinero.png",50,50));
        Ventas.setBackground(Colores.Azul);
        Ventas.setForeground(Color.WHITE);
        Ventas.setMaximumSize(new Dimension(500,50));
        Ventas.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInicio.add(Ventas);

        panelInicio.add(Box.createRigidArea(new Dimension(0,20)));

        JButton Registrar = new JButton("Registrar producto");
        Registrar.setFont(Fuentes.SourceSansPro18);
        Registrar.setIcon(IconScalling.scale("/Assets/Iconos/Caja.png",40,40));
        Registrar.setBackground(Colores.Azul);
        Registrar.setForeground(Color.WHITE);
        Registrar.setMaximumSize(new Dimension(500,50));
        Registrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                iniciarRegistro();
            }
        });
        panelInicio.add(Registrar);

        panelInicio.add(Box.createRigidArea(new Dimension(0,20)));

        JButton CambiarRegistro = new JButton("Cambiar registro de un producto");
        CambiarRegistro.setFont(Fuentes.SourceSansPro18);
        CambiarRegistro.setIcon(IconScalling.scale("/Assets/Iconos/Llave.png",40,40));
        CambiarRegistro.setBackground(Colores.Azul);
        CambiarRegistro.setForeground(Color.WHITE);
        CambiarRegistro.setMaximumSize(new Dimension(500,50));
        CambiarRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInicio.add(CambiarRegistro);

        panelInicio.add(Box.createRigidArea(new Dimension(0,20)));

        JButton opciones = new JButton("Opciones de usuario");
        opciones.setFont(Fuentes.SourceSansPro18);
        opciones.setIcon(IconScalling.scale("/Assets/Iconos/tuerca.png",40,40));
        opciones.setBackground(Colores.Azul);
        opciones.setForeground(Color.WHITE);
        opciones.setMaximumSize(new Dimension(500,50));
        opciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInicio.add(opciones);

        panelInicio.add(Box.createRigidArea(new Dimension(0,20)));

        JButton info = new JButton("Información de versión");
        info.setFont(Fuentes.SourceSansPro18);
        info.setIcon(IconScalling.scale("/Assets/Iconos/dev.png",40,40));
        info.setBackground(Colores.Azul);
        info.setForeground(Color.WHITE);
        info.setMaximumSize(new Dimension(500,50));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInicio.add(info);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(getContentPane(), "Programa desarrollado por: David Alejandro Garcia Monguí \n Universidad Industrial de Santander \n Source Code: https://github.com/GoonDocK/Super-Mercado \n Versión: Alpha 2025.11.1 ");
            }
        });


        panelCentral.add(panelInicio,"Inicio");
        centro.show(panelCentral,"Inicio");

        //Inciar Ventas
        JPanel panelVentas = new panelVentas(this);
        panelCentral.add(panelVentas,"Ventas");

        JPanel panelRegistro= new panelRegistrar(this);
        panelCentral.add(panelRegistro,"Registro");

        add(panelCentral,BorderLayout.CENTER);
        add(panelSuperior,BorderLayout.NORTH);

        setVisible(true);


    }
    public void iniciarVentas(){
        setSize(900,650);
        centro.show(panelCentral,"Ventas");
    }
    public void iniciarRegistro(){
        setSize(900,650);
        centro.show(panelCentral,"Registro");
    }

    public void volverInicio(){
        setSize(800,650);
        centro.show(panelCentral,"Inicio");
    }



}
