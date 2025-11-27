package GUI.MainWindow.Calculadora;
import AssetsHandler.IconScalling;
import AssetsHandler.audioPlayer;
import DATA.Insert;
import DATATYPES.Ventas;
import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;
import GUI.MainWindow.BarcodeRead.BarcodeReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Cambio extends JDialog {
    private final CardLayout centro;
    private Double Cambio=0.0;
    public Cambio(Double total, String nombrecajero, int Cantidad, DefaultTableModel modelo, BarcodeReader BCR){
        setSize( 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Cambio");
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel();
        setModal(true);
        centro = new CardLayout();
        panelCentral.setLayout(centro);
        AssetsHandler.audioPlayer sonido= new audioPlayer();
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelSuperior.setBackground(Colores.Verde);
        panelSuperior.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Seleccione el método de pago del cliente", JLabel.CENTER);
        titulo.setFont(Fuentes.SourceSansPro25);
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo,BorderLayout.CENTER);

        JPanel panelBotones= new JPanel();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.Y_AXIS));

        JButton efectivo= new JButton("Efectivo");
        efectivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        efectivo.setMaximumSize(new Dimension(300,50));
        efectivo.setIcon(IconScalling.scale("/Assets/Iconos/dinero.png",30,30));
        efectivo.setBackground(Colores.Azul);
        efectivo.setForeground(Color.WHITE);
        efectivo.setFont(Fuentes.SourceSansPro18);
        efectivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(600,350);
                centro.show(panelCentral,"efectivo");
                titulo.setText("Pago en efectivo");
            }
        });
        panelBotones.add(efectivo);
        panelBotones.add(Box.createRigidArea(new Dimension(0,10)));

        JButton targeta = new JButton("Pago con tarjeta");
        targeta.setAlignmentX(Component.CENTER_ALIGNMENT);
        targeta.setIcon(IconScalling.scale("/Assets/Iconos/targeta.png",30,30));
        targeta.setMaximumSize(new Dimension(300,50));
        targeta.setBackground(Colores.Azul);
        targeta.setForeground(Color.WHITE);
        targeta.setFont(Fuentes.SourceSansPro18);
        targeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(JOptionPane.showConfirmDialog(null, "Desea confirmar el pago por tarjeta?")==JOptionPane.YES_OPTION){
                    Ventas nuevaVenta= new Ventas(nombrecajero, total, Cantidad, "Tarjeta");
                    if(Insert.Venta(nuevaVenta)){
                        JOptionPane.showMessageDialog(null,"Venta realizada con exito");
                        sonido.playCompra();
                        modelo.setRowCount(0);
                        BCR.limpiar();
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al registrar la venta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
        panelBotones.add(targeta);

        panelBotones.add(Box.createRigidArea(new Dimension(0,10)));

        JButton Nequi= new JButton("Nequi");
        Nequi.setAlignmentX(Component.CENTER_ALIGNMENT);
        Nequi.setIcon(IconScalling.scale("/Assets/Iconos/Nequi.png",30,30));
        Nequi.setMaximumSize(new Dimension(300,50));
        Nequi.setBackground(Colores.Azul);
        Nequi.setForeground(Color.WHITE);
        Nequi.setFont(Fuentes.SourceSansPro18);
        Nequi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(JOptionPane.showConfirmDialog(null, "Desea confirmar el pago por Nequi?")==JOptionPane.YES_OPTION){
                    Ventas nuevaVenta= new Ventas(nombrecajero, total, Cantidad, "Nequi");
                    if(Insert.Venta(nuevaVenta)){
                        JOptionPane.showMessageDialog(null,"Venta realizada con éxito");
                        sonido.playCompra();
                        modelo.setRowCount(0);
                        BCR.limpiar();
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Error al registrar la venta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panelBotones.add(Nequi);
        panelBotones.add(Box.createRigidArea(new Dimension(0,10)));

        JButton cancelar= new JButton("Cancelar");
        cancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelar.setIcon(IconScalling.scale("/Assets/Iconos/cancear.png",30,30));
        cancelar.setMaximumSize(new Dimension(300,50));
        cancelar.setBackground(Colores.Rojo);
        cancelar.setForeground(Color.WHITE);
        cancelar.setFont(Fuentes.SourceSansPro18);
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelBotones.add(cancelar);
        panelCentral.add(panelBotones,"Pagos");

        JPanel pagoEfectivo = new JPanel();
        pagoEfectivo.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pagoEfectivo.setBackground(Color.WHITE);
        pagoEfectivo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.insets=new Insets(0,0,20,0);
        JLabel pago=new JLabel("Ingrese el pago del cliente: ",JLabel.CENTER);
        pago.setFont(Fuentes.SourceSansPro18);
        pagoEfectivo.add(pago,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        JLabel pagoCliente= new JLabel("Pago del cliente: ");
        pagoCliente.setFont(Fuentes.SourceSansPro18);
        pagoEfectivo.add(pagoCliente,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        JTextField pagoClienteTextField= new JTextField();
        pagoEfectivo.add(pagoClienteTextField,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=2;

        JLabel totalPago= new JLabel("Total a pagar: "+total);
        totalPago.setFont(Fuentes.SourceSansPro18);
        pagoEfectivo.add(totalPago,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        JLabel cambio= new JLabel("Cambio: "+Cambio);
        cambio.setFont(Fuentes.SourceSansPro18);
        pagoEfectivo.add(cambio,gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        JButton pagar= new JButton("Pagar");
        pagar.setIcon(IconScalling.scale("/Assets/Iconos/Aceptar.png",30,30));
        pagar.setFont(Fuentes.SourceSansPro18);
        pagar.setBackground(Colores.Azul);
        pagar.setForeground(Color.WHITE);
        pagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Double pagoCliente=Double.parseDouble(pagoClienteTextField.getText());
                    if(pagoCliente<total){
                        JOptionPane.showMessageDialog(null,"Cantidad Inválida");
                    }else{
                        Cambio=pagoCliente-total;
                        Ventas nuevaVenta= new Ventas(nombrecajero, total, Cantidad, "Efectivo");
                        if(Insert.Venta(nuevaVenta)){
                            JOptionPane.showMessageDialog(null,"Venta realizada con exito \n Cambio:"+Cambio);
                            sonido.playCompra();
                            modelo.setRowCount(0);
                            BCR.limpiar();
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error al registrar la venta", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Digite un precio válido","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        pagoEfectivo.add(pagar,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        JButton cancelarPago= new JButton("Cancelar");
        cancelarPago.setIcon(IconScalling.scale("/Assets/Iconos/cancear.png",30,30));
        cancelarPago.setFont(Fuentes.SourceSansPro18);
        cancelarPago.setBackground(Colores.Azul);
        cancelarPago.setForeground(Color.WHITE);
        cancelarPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(600, 400);
                centro.show(panelCentral,"Pagos");
                titulo.setText("Seleccione el método de pago del cliente");
            }
        });
        pagoEfectivo.add(cancelarPago,gbc);


        panelCentral.add(pagoEfectivo,"efectivo");




        centro.show(panelCentral,"Pagos");
        add(panelSuperior,BorderLayout.NORTH);
        add(panelCentral,BorderLayout.CENTER);
        setVisible(true);
    }
}
