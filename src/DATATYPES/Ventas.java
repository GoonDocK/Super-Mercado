package DATATYPES;
import java.time.LocalDate;
public class Ventas {
    private String cajero;
    private LocalDate fecha;
    private double total;
    private int cantidad;
    private String tipoDePago;
    public Ventas(){}
    public Ventas(String cajero, double total, int cantidad, String tipoDePago){
        this.cajero = cajero;
        this.total = total;
        this.fecha = LocalDate.now();
        this.cantidad = cantidad;
        this.tipoDePago = tipoDePago;
    }
    public String getCajero() {
        return cajero;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public double getTotal() {
        return total;
    }
    public void setCajero(String cajero) {
        this.cajero = cajero;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getTipoDePago() {
        return tipoDePago;
    }
    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

}
