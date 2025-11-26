package GUI.MainWindow;
import AssetsHandler.IconScalling;
import DATA.locate;
import DATATYPES.Producto;
import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;
import GUI.MainWindow.BarcodeRead.BarcodeReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class panelVentas extends JPanel {
    private double Total=0;
    private DefaultTableModel modelo;
    private JTable tablaVentas;
    private JLabel impuesto, total;
    public panelVentas(Window ventana){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(Color.WHITE);

        //Tabla de ventas

        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==5){
                    return true;
                }
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex==5){
                    return Boolean.class;
                }
                return Object.class;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
        modelo.addColumn("Seleccionar");

        tablaVentas = new JTable(modelo);
        tablaVentas.setFont(Fuentes.SourceSansPro18);
        tablaVentas.getTableHeader().setBackground(Colores.Azul);
        tablaVentas.getTableHeader().setForeground(Color.WHITE);
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
        btneliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                quitarProducto();
            }
        });

        panelBotones.add(btneliminar);

        JButton agregar = new JButton("Agregar manualmente");
        agregar.setBackground(Colores.Azul);
        agregar.setForeground(Color.WHITE);
        agregar.setFont(Fuentes.SourceSansPro18);
        agregar.setIcon(IconScalling.scale("/Assets/Iconos/añadir.png",30,30));
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                anadirManualmente();
            }
        });

        panelBotones.add(agregar);

        JButton descuento = new JButton("Agregar descuento producto");
        descuento.setBackground(Colores.Azul);
        descuento.setForeground(Color.WHITE);
        descuento.setFont(Fuentes.SourceSansPro18);
        descuento.setIcon(IconScalling.scale("/Assets/Iconos/descuento.png",30,30));
        descuento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                descuento();
            }
        });

        panelBotones.add(descuento);

        JButton cancelar= new JButton("Cancelar Venta");
        cancelar.setFont(Fuentes.SourceSansPro18);
        cancelar.setIcon(IconScalling.scale("/Assets/Iconos/cancear.png",30,30));
        cancelar.setBackground(Colores.Azul);
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                limpiarTabla();
            }
        });

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
                ventana.Ventas=false;
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
        total = new JLabel("Total: "+Total);
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
        impuesto = new JLabel("IVA 19%: "+Total*0.19);
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
    private void buscarProducto(String codigo){
        Producto producto= locate.Producto(Long.parseLong(codigo));
        if(producto!=null ){
            boolean encontrado=false;
            for(int i=0;i<modelo.getRowCount();i++){
                if(producto.getId()==(Long)modelo.getValueAt(i,0)){
                    int cantidad=(int)modelo.getValueAt(i,2);
                    cantidad++;
                    modelo.setValueAt((Object)cantidad,i,2);
                    modelo.fireTableCellUpdated(i,2);
                    double preciototal=(Double)modelo.getValueAt(i,3)*cantidad;
                    modelo.setValueAt((Object) preciototal,i,4);
                    modelo.fireTableCellUpdated(i,4);
                    Total+=producto.getPrecio();
                    impuesto.setText("IVA 19%: "+Total*0.19);
                    total.setText("Total: "+Total);
                    encontrado=true;
                    break;
                }
            }
            if(!encontrado){
                Object[] row = {producto.getId(),producto.getNombre(),1,producto.getPrecio(),producto.getPrecio(), false};
                Total+=producto.getPrecio();
                modelo.addRow(row);
                modelo.fireTableDataChanged();
                total.setText("Total: "+Total);
                impuesto.setText("IVA 19%: "+Total*0.19);
            }

        }else{
            JOptionPane.showMessageDialog(null,"Producto no encontrado");
        }
    }
    public void setListener(BarcodeReader BCR){
        BCR.setListener(this::buscarProducto);
    }
    private void limpiarTabla(){
        if(JOptionPane.showConfirmDialog(null,"Esta seguro que desea cancelar la venta?","Cancelar venta",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            modelo.setRowCount(0);
            Total=0;
            total.setText("Total: "+Total);
            impuesto.setText("IVA 19%: "+Total*0.19);
        }
    }
    private void quitarProducto(){
        for(int i=modelo.getRowCount()-1;i>=0;i--){
            boolean seleccionado=(boolean)modelo.getValueAt(i,5);
            if(seleccionado){
                Total= Total- (Double) modelo.getValueAt(i,4);
                total.setText("Total: "+Total);
                impuesto.setText("IVA 19%: "+Total*0.19);
                modelo.removeRow(i);
            }
        }
    }
    private void modificarProducto(){
    }
    private void anadirManualmente(){
        JDialog dialogo =dialogoCustom();

        JPanel panelCentro= new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelCentro.setLayout(new GridBagLayout());
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
        JLabel codigo= new JLabel("Ingrese los datos del producto manualmente: ", JLabel.CENTER);
        panelCentro.add(codigo,gbc);

        gbc.insets=new Insets(0,0,5,0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        JLabel LabelCodigo= new JLabel("Codigo: ");
        panelCentro.add(LabelCodigo,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        JTextField codigoTextField= new JTextField();
        panelCentro.add(codigoTextField,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        JLabel cantidad= new JLabel("Ingrese la cantidad:");
        panelCentro.add(cantidad,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        JTextField cantidadTextField= new JTextField();
        panelCentro.add(cantidadTextField,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        JLabel precio= new JLabel("Ingrese el precio:");
        panelCentro.add(precio,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        JTextField precioTextField= new JTextField();
        panelCentro.add(precioTextField,gbc);

        dialogo.add(panelCentro,BorderLayout.CENTER);

        JPanel panelBotones= new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setLayout(new GridLayout(1,2));
        JButton cancelar= new JButton("Cancelar");
        cancelar.setIcon(IconScalling.scale("/Assets/Iconos/cancear.png",30,30));
        cancelar.setFont(Fuentes.SourceSansPro18);
        cancelar.setBackground(Colores.Rojo);
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e->{dialogo.dispose();});

        JButton agregar= new JButton("Aceptar");
        agregar.setIcon(IconScalling.scale("/Assets/Iconos/añadir.png",30,30));
        agregar.setFont(Fuentes.SourceSansPro18);
        agregar.setBackground(Colores.Verde);
        agregar.setForeground(Color.WHITE);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    Producto producto= locate.Producto(Long.parseLong(codigoTextField.getText()));
                    if(producto!=null){
                        Object[] row= {producto.getId(),producto.getNombre(),Integer.parseInt(cantidadTextField.getText()),producto.getPrecio(),producto.getPrecio()*Integer.parseInt(cantidadTextField.getText()), false};
                        modelo.addRow(row);
                        modelo.fireTableDataChanged();
                        Total+=producto.getPrecio()*Integer.parseInt(cantidadTextField.getText());
                        total.setText("Total: "+Total);
                        impuesto.setText("IVA 19%: "+Total*0.19);
                        dialogo.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Producto no encontrado");
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Codigo no valido");
                }
            }
        });
        panelBotones.add(cancelar);
        panelBotones.add(agregar);
        dialogo.add(panelBotones,BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    private void terminarVenta(){
    }
    private void descuento(){
        if(modelo.getRowCount()>0){
            for(int i=modelo.getRowCount()-1;i>=0;i--){
                boolean seleccionado=(boolean)modelo.getValueAt(i,5);
                if(seleccionado){
                    JDialog dialogo =dialogoCustom();
                    dialogo.setTitle("Agregar producto descuento");
                    JPanel panelCentro= new JPanel();
                    panelCentro.setBackground(Color.WHITE);
                    panelCentro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                    panelCentro.setLayout(new GridBagLayout());
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
                    JLabel codigo= new JLabel("Ingrese el porcentaje de descuento del producto ", JLabel.CENTER);
                    panelCentro.add(codigo,gbc);

                    gbc.insets=new Insets(0,0,5,0);
                    gbc.gridx=0;
                    gbc.gridy=1;
                    gbc.gridwidth=1;
                    gbc.gridheight=1;
                    gbc.anchor=GridBagConstraints.CENTER;
                    gbc.fill=GridBagConstraints.HORIZONTAL;
                    gbc.weightx=1;
                    gbc.weighty=0;
                    JLabel LabelCodigo= new JLabel("Descuento: ");
                    panelCentro.add(LabelCodigo,gbc);

                    gbc.gridx=1;
                    gbc.gridy=1;
                    gbc.gridwidth=1;
                    gbc.gridheight=1;
                    gbc.anchor=GridBagConstraints.CENTER;
                    gbc.fill=GridBagConstraints.HORIZONTAL;
                    gbc.weightx=1;
                    gbc.weighty=0;
                    JTextField codigoTextField= new JTextField();
                    panelCentro.add(codigoTextField,gbc);

                    dialogo.add(panelCentro,BorderLayout.CENTER);

                    JPanel panelBotones= new JPanel();
                    panelBotones.setBackground(Color.WHITE);
                    panelBotones.setLayout(new GridLayout(1,2));
                    JButton cancelar= new JButton("Cancelar");
                    cancelar.setIcon(IconScalling.scale("/Assets/Iconos/cancear.png",30,30));
                    cancelar.setFont(Fuentes.SourceSansPro18);
                    cancelar.setBackground(Colores.Rojo);
                    cancelar.setForeground(Color.WHITE);
                    cancelar.addActionListener(e->{dialogo.dispose();});
                    panelBotones.add(cancelar);

                    JButton aceptar= new JButton("Aceptar");
                    aceptar.setBackground(Colores.Verde);
                    aceptar.setForeground(Color.WHITE);
                    aceptar.setFont(Fuentes.SourceSansPro18);
                    aceptar.setIcon(IconScalling.scale("/Assets/Iconos/Aceptar.png",30,30));
                    double precioOriginal=(Double) modelo.getValueAt(i,3);
                    int finalI = i;
                    aceptar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                if(codigoTextField.getText().length()>0 && Double.parseDouble(codigoTextField.getText())<=100){
                                    Double descuento=Double.parseDouble(codigoTextField.getText())/100*precioOriginal;
                                    modelo.setValueAt((Object)descuento, finalI,3 );
                                    modelo.fireTableCellUpdated(finalI,3);
                                    double total=descuento*(Integer)modelo.getValueAt(finalI,2);
                                    modelo.setValueAt((Object)total, finalI,4);
                                    modelo.fireTableCellUpdated(finalI,4);
                                    dialogo.dispose();
                                }else{
                                    JOptionPane.showMessageDialog(null,"EL porcentaje debe ser entre 0 y 100","Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }catch (NumberFormatException ex){
                                JOptionPane.showMessageDialog(null,"Porcentaje no válido", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    panelBotones.add(aceptar);

                    dialogo.add(panelBotones,BorderLayout.SOUTH);

                    dialogo.setVisible(true);


                }
            }

        }
    }
    private JDialog dialogoCustom(){
        JDialog dialogo = new JDialog();
        dialogo.setResizable(false);
        dialogo.setSize(400,200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());
        dialogo.setModal(true);
        dialogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogo.setIconImage(IconScalling.scale("/Assets/Iconos/5465865.png",64,64).getImage());
        return dialogo;
    }
}
