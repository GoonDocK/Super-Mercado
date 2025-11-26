package GUI.MainWindow.BarcodeRead;
import java.awt.*;
import java.awt.event.KeyEvent;
public class BarcodeReader {
    private final StringBuilder buffer=new StringBuilder();
    private String codigo;
    private BarcodeListener listener;
    private KeyEventDispatcher dispatcher;
    public void start() {
       if(dispatcher==null){
           dispatcher=this::dispatchKeyEvent;
           KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
       }
    }
    public void setListener(BarcodeListener listener) {
        this.listener = listener;
    }
    public void stop() {
        if(dispatcher!=null){
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
            dispatcher=null;
        }
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
    public void limpiar(){
        buffer.setLength(0);
    }

}
