package DATATYPES;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ventas {
    private String cajero;
    private LocalDateTime fecha;
    private double total;
    private ArrayList<Producto> productos;
    private long id;
    public Ventas(){}
    public Ventas(String cajero, LocalDateTime fecha, double total, ArrayList<Producto> productos, long id){
        this.cajero = cajero;
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.productos = productos;
        this.fecha = LocalDateTime.now();
    }
    public String getCajero() {
        return cajero;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public double getTotal() {
        return total;
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public void setCajero(String cajero) {
        this.cajero = cajero;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
