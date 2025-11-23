package GUI.MainWindow;

import AssetsHandler.IconScalling;
import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelVentas extends JPanel{
    public panelVentas(Window ventana){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(Color.WHITE);

        //Tabla de ventas

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");

        JTable tablaVentas = new JTable(modelo);
        tablaVentas.setFont(Fuentes.SourceSansPro18);

        JScrollPane panelTabla= new JScrollPane(tablaVentas);

        //Panel de botones
        JPanel panelBotones= new JPanel(new GridLayout(2,6));
        panelBotones.setBackground(Color.WHITE);


        JButton btnmodificar= new JButton("Modificar producto");
        btnmodificar.setFont(Fuentes.SourceSansPro18);
        btnmodificar.setIcon(IconScalling.scale("/Assets/Iconos/Llave.png",30,30));
        btnmodificar.setBackground(Colores.Azul);
        btnmodificar.setForeground(Color.WHITE);

        panelBotones.add(btnmodificar);

        JButton btneliminar= new JButton("Eliminar producto");
        btneliminar.setFont(Fuentes.SourceSansPro18);
        btneliminar.setIcon(IconScalling.scale("/Assets/Iconos/Borrar.png",30,30));
        btneliminar.setBackground(Colores.Azul);
        btneliminar.setForeground(Color.WHITE);

        panelBotones.add(btneliminar);

        JButton agregar = new JButton("Agregar manualmente");
        agregar.setBackground(Colores.Azul);
        agregar.setForeground(Color.WHITE);
        agregar.setFont(Fuentes.SourceSansPro18);
        agregar.setIcon(IconScalling.scale("/Assets/Iconos/añadir.png",30,30));

        panelBotones.add(agregar);

        JButton descuento = new JButton("Agregar descuento producto");
        descuento.setBackground(Colores.Azul);
        descuento.setForeground(Color.WHITE);
        descuento.setFont(Fuentes.SourceSansPro18);
        descuento.setIcon(IconScalling.scale("/Assets/Iconos/descuento.png",30,30));

        panelBotones.add(descuento);

        JButton cancelar= new JButton("Cancelar Venta");
        cancelar.setFont(Fuentes.SourceSansPro18);
        cancelar.setIcon(IconScalling.scale("/Assets/Iconos/cancear.png",30,30));
        cancelar.setBackground(Colores.Azul);
        cancelar.setForeground(Color.WHITE);

        panelBotones.add(cancelar);

        JButton botonAtras = new JButton("Atras");
        botonAtras.setFont(Fuentes.SourceSansPro18);
        botonAtras.setIcon(IconScalling.scale("/Assets/Iconos/Salida.png",30,30));
        botonAtras.setBackground(Colores.Rojo);
        botonAtras.setForeground(Color.WHITE);
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ventana.volverInicio();
            }
        });
        panelBotones.add(botonAtras);

        //Panel info general
        JPanel panelInfo = new JPanel(new GridBagLayout());
        GridBagConstraints gdc = new GridBagConstraints();
        panelInfo.setBackground(Color.WHITE);

        gdc.gridx=0;
        gdc.gridy=0;
        gdc.gridwidth=1;
        gdc.gridheight=1;
        gdc.anchor=GridBagConstraints.CENTER;
        gdc.fill=GridBagConstraints.HORIZONTAL;
        gdc.weightx=1;
        gdc.weighty=0;
        JLabel total = new JLabel("Total: ");
        total.setFont(Fuentes.SourceSansPro18Bold);
        panelInfo.add(total,gdc);

        gdc.gridx=0;
        gdc.gridy=1;
        gdc.gridwidth=1;
        gdc.gridheight=1;
        gdc.anchor=GridBagConstraints.CENTER;
        gdc.fill=GridBagConstraints.HORIZONTAL;
        gdc.weightx=1;
        gdc.weighty=0;
        gdc.insets=new Insets(0,0,150,0);
        JLabel impuesto = new JLabel("Impuesto: ");
        impuesto.setFont(Fuentes.SourceSansPro18Bold);
        panelInfo.add(impuesto,gdc);

        gdc.gridx=0;
        gdc.gridy=2;
        gdc.gridwidth=1;
        gdc.gridheight=1;
        gdc.anchor=GridBagConstraints.CENTER;
        gdc.fill=GridBagConstraints.HORIZONTAL;
        gdc.weightx=1;
        gdc.weighty=0;
        gdc.insets=new Insets(136,0,0,0);

        JButton terminar = new JButton("Terminar Venta");
        terminar.setFont(Fuentes.SourceSansPro18);
        terminar.setIcon(IconScalling.scale("/Assets/Iconos/Cesta.png",30,30));
        terminar.setBackground(Colores.Verde);
        terminar.setForeground(Color.WHITE);

        panelInfo.add(terminar,gdc);



        add(panelInfo,BorderLayout.WEST);
        add(panelBotones,BorderLayout.SOUTH);
        add(panelTabla,BorderLayout.CENTER);

    }
}
