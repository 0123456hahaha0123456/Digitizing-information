import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class frameClient  {
    ArrayList<Rectangle> arr;
    JFrame frameCli;
    JPanel panelPos;
    JPanel panelBut;

    public frameClient(ArrayList<Rectangle> arr){
        this.arr = arr;
        createFrame();
    }

    private void createFrame(){
        frameCli = new JFrame("Client1");
        frameCli.getContentPane().setBackground(Color.white);
        //frameCli.getContentPane().setVisible(false);
        frameCli.setLayout(null);


        frameCli.setSize(1000,1000);
        frameCli.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelPos = new JPanel();
        panelBut = new JPanel();

        //use for elements
        panelPos.setSize(1000,800);
        panelPos.setLayout(null);
        panelPos.setBackground(Color.PINK);
        createElement();

        //use for button
        panelBut.setLocation(0,800);
        panelBut.setSize(1000,200);
        panelBut.setLayout(null);
        panelBut.setBackground(Color.BLACK);

        frameCli.add(panelPos);
        frameCli.add(panelBut);

        frameCli.setVisible(true);
    }


    private void createElement(){
        Rectangle duc;
        for(int i=0;i<arr.size();i++){
            duc = arr.get(i);
            panelPos.add(duc);
            duc.draw();

        }
    }
}
