package GUI.MainWindow.BarcodeRead;
import DATA.locate;
import DATA.Insert;
import DATATYPES.Producto;

public class BarcodeProcess {
    public static boolean CheckDB(long code, Producto item){
        Producto producto=locate.Producto(code);
        if(producto==null){
            InsertDB(item);
            return true;
        }else{
            return false;
        }
    }
    private static void InsertDB(Producto item){
        Insert.Producto(item);
    }
}
