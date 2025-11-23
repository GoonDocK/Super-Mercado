package GUI.MainWindow.BarcodeRead;
import java.awt.*;
import java.awt.event.KeyEvent;
public class BarcodeReader {
    private final StringBuilder buffer=new StringBuilder();
    private String codigo;
    private BarcodeListener listener;
    public void start() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this::dispatchKeyEvent);
    }
    public void setListener(BarcodeListener listener) {
        this.listener = listener;
    }
    public void stop() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this::dispatchKeyEvent);
    }
    private boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID()==KeyEvent.KEY_PRESSED){
            int codigo=e.getKeyCode();
           if(codigo==KeyEvent.VK_ENTER){
               String barcode=buffer.toString();
               buffer.setLength(0);
               if (!barcode.isBlank() && listener != null) {
                   listener.onBarcodeRead(barcode);
               }
               return true;
           }
           char ch=e.getKeyChar();
           if(Character.isDefined(ch) && !Character.isISOControl(ch) && !Character.isWhitespace(ch) && Character.isDigit(ch)){
               buffer.append(ch);
               return true;
           }
        }
        return false;
    }
    private void setString(String codigo){
        this.codigo=codigo;
    }
}
