package GUI.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import AssetsHandler.IconScalling;
import DATATYPES.Producto;
import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;
import GUI.MainWindow.BarcodeRead.BarcodeProcess;
import GUI.MainWindow.BarcodeRead.BarcodeReader;
public class panelRegistrar extends JPanel {
    private boolean LCDBActive=true;
    private JTextField CodigoTextField, Precio, Nombre;
    private JButton desactivar;
    public panelRegistrar(Window ventanaPrincipal){
        setLayout(new BorderLayout());
        JPanel panelCentro=new JPanel();
        panelCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelCentro.setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,0,0);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        JLabel NombreLabel= new JLabel("Nombre del producto: ");
        NombreLabel.setFont(Fuentes.SourceSansPro18);
        panelCentro.add(NombreLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,0,0);
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        Nombre= new JTextField();
        Nombre.setFont(Fuentes.SourceSansPro18);
        panelCentro.add(Nombre, gbc);


        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,0,0);
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        JLabel PrecioLabel= new JLabel("Precio: ");
        PrecioLabel.setFont(Fuentes.SourceSansPro18);
        panelCentro.add(PrecioLabel, gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,0,0);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=1;
        Precio= new JTextField();
        Precio.setFont(Fuentes.SourceSansPro18);
        panelCentro.add(Precio,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,0,0);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        JLabel Codigo= new JLabel("Codigo: ");
        Codigo.setFont(Fuentes.SourceSansPro18);
        panelCentro.add(Codigo, gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(0,0,0,0);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=1;
        CodigoTextField= new JTextField();
        CodigoTextField.setFont(Fuentes.SourceSansPro18);
        CodigoTextField.setEditable(false);
        BarcodeReader bcr=new BarcodeReader();
        bcr.setListener(code -> {CodigoTextField.setText(code);});
        bcr.start();
        panelCentro.add(CodigoTextField, gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        gbc.gridheight=2;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(20,0,0,0);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        JTextArea Descripcion= new JTextArea();
        Descripcion.setFont(Fuentes.SourceSansPro18);
        Descripcion.setLineWrap(true);
        Descripcion.setWrapStyleWord(true);
        Descripcion.setOpaque(false);
        Descripcion.setEditable(false);
        Descripcion.setText("Para agregar un nuevo producto use el escáner de código de barras o ingrese los datos manualmente");
        panelCentro.add(Descripcion, gbc);


        add(panelCentro,BorderLayout.CENTER);

        JPanel panelBotones= new JPanel(new GridLayout(2,2));
        panelBotones.setBackground(Color.WHITE);

        JButton registrar = new JButton("Registrar");
        registrar.setIcon(IconScalling.scale("/Assets/Iconos/Registrar.png",30,30));
        registrar.setFont(Fuentes.SourceSansPro18);
        registrar.setForeground(Color.WHITE);
        registrar.setBackground(Colores.Azul);
        registrar.setFocusPainted(false);

        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                comprobarDatos();
            }
        });
        panelBotones.add(registrar);

        desactivar= new JButton("Desactivar LCDB");
        desactivar.setFont(Fuentes.SourceSansPro18);
        desactivar.setIcon(IconScalling.scale("/Assets/Iconos/Codigo de barras.png",30,30));
        desactivar.setBackground(Colores.Azul);
        desactivar.setForeground(Color.WHITE);
        desactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                desactivarLDCB(bcr);
            }
        });
        panelBotones.add(desactivar);


        JButton atras = new JButton("Atras");
        atras.setFont(Fuentes.SourceSansPro18);
        atras.setIcon(IconScalling.scale("/Assets/Iconos/Salida.png",30,30));
        atras.setBackground(Colores.Rojo);
        atras.setForeground(Color.WHITE);
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ventanaPrincipal.volverInicio();
            }
        });
        panelBotones.add(atras);
        add(panelBotones,BorderLayout.SOUTH);

        JPanel panelinfo= new JPanel(new GridBagLayout());
        panelinfo.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelinfo.setBackground(Color.WHITE);
        GridBagConstraints gdc = new GridBagConstraints();
        gdc.gridx=0;
        gdc.gridy=0;
        gdc.gridwidth=2;
        gdc.gridheight=2;
        gdc.anchor=GridBagConstraints.CENTER;
        gdc.insets=new Insets(0,0,0,0);
        JLabel info= new JLabel();
        info.setIcon(IconScalling.scale("/Assets/Iconos/Barras.png",100,100));
        panelinfo.add(info,gdc);


        add(panelinfo,BorderLayout.WEST);

    }
    private void comprobarDatos(){
        try{
            Producto producto= new Producto();
            if(Nombre.getText().isBlank() || Precio.getText().isBlank() || CodigoTextField.getText().isBlank()){
                JOptionPane.showMessageDialog(null,"Por favor complete todos los campos");
            }else{
                producto.setNombre(Nombre.getText());
               try{
                   producto.setPrecio(Double.parseDouble(Precio.getText()));
               }catch (NumberFormatException ex){
                   JOptionPane.showMessageDialog(null,"Precio no valido");
                   Precio.setText(null);
                   return;
               }
                producto.setid(Long.parseLong(CodigoTextField.getText()));
                if(!BarcodeProcess.CheckDB(Long.parseLong(CodigoTextField.getText()), producto)){
                    JOptionPane.showMessageDialog(null,"Este producto ya fue registrado");
                    CodigoTextField.setText(null);
                    Nombre.setText(null);
                    Precio.setText(null);
                }else{
                    CodigoTextField.setText(null);
                    Nombre.setText(null);
                    Precio.setText(null);
                    JOptionPane.showMessageDialog(null,"Producto registrado correctamente");
                }
            }

        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Código de barras no valido");
            CodigoTextField.setText(null);
        }
    }
    private void desactivarLDCB(BarcodeReader reader){
        if(LCDBActive){
            LCDBActive=false;
            CodigoTextField.setEditable(true);
            CodigoTextField.setText(null);
            reader.stop();
            desactivar.setText("Activar LCDB");
        }else{
            LCDBActive=true;
            CodigoTextField.setEditable(false);
            reader.start();
            desactivar.setText("Desactivar LCDB");
        }
    }
}
