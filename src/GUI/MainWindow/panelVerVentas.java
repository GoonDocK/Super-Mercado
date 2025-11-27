package GUI.MainWindow;
import AssetsHandler.IconScalling;
import DATA.locate;
import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class panelVerVentas extends JPanel {
    private DefaultTableModel modelo;
    private JTable tablaVentas;
    public panelVerVentas(Window ventana){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(Color.WHITE);

        modelo = new DefaultTableModel();
        tablaVentas = new JTable(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("Cajero");
        modelo.addColumn("Fecha y hora de venta");
        modelo.addColumn("Cantidad de productos vendidos");
        modelo.addColumn("Total");


        JScrollPane panelTabla= new JScrollPane(tablaVentas);

        add(panelTabla, BorderLayout.CENTER);

        JPanel panelBotones= new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setLayout(new GridLayout(2,2));

        JButton regresar= new JButton("Regresar");
        regresar.setBackground(Colores.Rojo);
        regresar.setForeground(Color.WHITE);
        regresar.setFont(Fuentes.SourceSansPro18);
        regresar.setIcon(IconScalling.scale("/Assets/Iconos/Salida.png",30,30));
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ventana.volverInicio();
            }
        });

        JButton actualizar = new JButton("Actualizar");
        actualizar.setBackground(Colores.Azul);
        actualizar.setForeground(Color.WHITE);
        actualizar.setFont(Fuentes.SourceSansPro18);
        actualizar.setIcon(IconScalling.scale("/Assets/Iconos/Actualizar.png",30,30));
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                actualizarTabla();
            }
        });
        panelBotones.add(actualizar);
        panelBotones.add(regresar);



        add(panelBotones, BorderLayout.SOUTH);



    }
    private void actualizarTabla(){
        modelo.setRowCount(0);
        try{
            ArrayList<Object[]> lista=locate.ventas();
            for(Object[] row: lista){
                modelo.addRow(row);
            }
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,"No se encontró el registro");
        }
    }
}
