package AssetsHandler;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.InputStream;

public class audioPlayer {
    private Clip sonidoCaja;
    private Clip sonidoCompra;
    public audioPlayer(){
        try{
            InputStream is = getClass().getResourceAsStream("/Assets/Sonidos/Register.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
            sonidoCaja = AudioSystem.getClip();
            sonidoCaja.open(audioStream);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar el sonido"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        try{
            InputStream is = getClass().getResourceAsStream("/Assets/Sonidos/Pay.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
            sonidoCompra = AudioSystem.getClip();
            sonidoCompra.open(audioStream);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar el sonido: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void playCaja(){
        sonidoCaja.setFramePosition(0);
        sonidoCaja.start();
    }
    public void playCompra(){
        sonidoCompra.setFramePosition(0);
        sonidoCompra.start();
    }
}
