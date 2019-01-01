import javafx.scene.control.Tooltip;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        panelBut.setBackground(Color.WHITE);
        createButton();

        frameCli.add(panelPos,BorderLayout.NORTH);
        frameCli.add(panelBut,BorderLayout.SOUTH);

        frameCli.setVisible(true);
    }

    private void createButton(){
        panelBut.setLayout(new GridLayout(1,4));

        //tao filter color - Jlist
        JList listColor = new JList(Client.colour);
        listColor.setFont(new Font("Arial",Font.ITALIC, 18));

        JPanel listCol = new JPanel();
        listCol.add(listColor);
        setTitle(listCol,"filter by color");

        //tao filter dien tich - Jtexfield
        JTextField arealist = new JTextField();
        arealist.setColumns(10);

        JPanel area = new JPanel();
        setTitle(area,"filter bye area");
        area.add(arealist,BorderLayout.CENTER);

        //tao nut start
        JPanel start = new JPanel();
        JButton startButton = new JButton();
        startButton.setText("Start");
        start.add(startButton,BorderLayout.CENTER);

        //tao nut End
        JPanel end = new JPanel();
        JButton endButton = new JButton();
        endButton.setText("End");
        //end.add(endButton,BorderLayout.CENTER);
        end.add(endButton,BorderLayout.CENTER);

        panelBut.add(listCol);
        panelBut.add(area);
        panelBut.add(start);
        panelBut.add(end);

    }

    private void setTitle(JPanel duc, String title){
        TitledBorder border = new TitledBorder(title);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        duc.setBorder(border);
    }

    private void createElement(){
        Rectangle duc;
        for(int i=0;i<arr.size();i++){
            duc = arr.get(i);
            panelPos.add(duc);
            duc.setToolTipText("x = " + duc.getCorX() +" "
                    +"y = " + duc.getCorY() + " "
                    +"width = " + duc.getWidth() + " "
                    +"height = " + duc.getHeight() + " "
                    +"people = " +duc.getPeople()
            );
            duc.draw();
        }
    }
}
