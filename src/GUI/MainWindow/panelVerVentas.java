package GUI.MainWindow;
import AssetsHandler.IconScalling;
import FuenteYTipografia.Colores;
import FuenteYTipografia.Fuentes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class panelVerVentas extends JPanel {
    private DefaultTableModel modelo;
    private JTable tablaVentas;
    public panelVerVentas(Window ventana){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(Color.WHITE);

        modelo = new DefaultTableModel();
        tablaVentas = new JTable(modelo);

        modelo.addColumn("Cajero");
        modelo.addColumn("Fecha y hora de venta");
        modelo.addColumn("Total");
        modelo.addColumn("Cantidad de productos vendidos");

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
        panelBotones.add(regresar);



        add(panelBotones, BorderLayout.SOUTH);



    }
}
