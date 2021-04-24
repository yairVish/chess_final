import java.awt.Color;
import javax.swing.JFrame;
public class RunApp {
    public static void main(String[] args) throws Exception {
        JFrame f=new JFrame("game");
        Main main=new Main("192.168.5.59",f);
        f.add(main);
        //x:490
        //y:605
        f.setBounds(500,100,376,400);
        f.setBackground(Color.WHITE);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.play();
    }
}