import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class frameClient  {
    ArrayList<Rectangle> arr;
    JFrame frameCli;
    JPanel panelPos;
    JPanel panelBut;

    public frameClient(ArrayList<Rectangle> arr){
        frameCli = new JFrame("Client1");
        this.arr = arr;
        createFrame();
    }

    private void createFrame(){

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
        listColor = new JList(Client.colour);
        listColor.setFont(new Font("Arial",Font.ITALIC, 18));
        listColor.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(listColor);
        listScroller.setPreferredSize(new Dimension(250, 100));

        JPanel listCol = new JPanel();
        listCol.add(listScroller);
        setTitle(listCol,"filter by color");

        //tao filter dien tich - Jtexfield
        arealist = new JTextField();
        arealist.setColumns(10);

        JPanel area = new JPanel();
        setTitle(area,"filter bye area");
        area.add(arealist,BorderLayout.CENTER);

        //tao nut start
        JPanel start = new JPanel();
        JButton startButton = new JButton("Start");
        //startButton.setText("Start");
        startButton.addActionListener(this::actionPerformed);
        start.add(startButton,BorderLayout.CENTER);

        //tao nut End
        JPanel end = new JPanel();
        JButton endButton = new JButton("End");
        endButton.addActionListener(this::actionPerformed);
        end.add(endButton,BorderLayout.CENTER);

        panelBut.add(listCol);
        panelBut.add(area);
        panelBut.add(start);
        panelBut.add(end);
    }

    //the filter rec
    private ArrayList<Rectangle> recFilter(String listColorString, String areaString){
        int area;
        if (areaString!=null) area = Integer.parseInt(areaString);
        else area = 0;

        Color listColor = Color.YELLOW;
        if (listColorString!= null) {
            switch (listColorString) {
                case "red":
                    listColor = Color.RED;
                    break;
                case "black":
                    listColor = Color.BLACK;
                    break;
                case "green":
                    listColor = Color.GREEN;
                    break;
                case "magenta":
                    listColor = Color.MAGENTA;
                    break;
                case "yellow":
                    listColor = Color.YELLOW;
                    break;
            }
        }

        //find which rec will acceptable condition
        ArrayList<Rectangle> _arr = new ArrayList<Rectangle>(){};
        arrCorY = new int[100];
        for(int i=0;i<arr.size();i++){
            Rectangle tmp = arr.get(i);
            if ((tmp.getHeight() * tmp.getWidth() >area) && ((tmp.getColor() == listColor) || (listColorString==null))){
                arrCorY[_arr.size()] = tmp.getCorY();
                _arr.add(tmp);

            }
        }
        return _arr;
    }

    private void actionPerformed(ActionEvent e){
        String listColorString;
        String areaString;
        if (e.getActionCommand().equals("Start")){
            try{
                listColorString  = listColor.getSelectedValue().toString();
                if (!arealist.getText().equals("")) areaString =  arealist.getText() ;
                else areaString = "0";

                if (listColorString!=null || areaString!=null) {
                    //System.out.println(listColorString + " " + areaString);
                    ArrayList<Rectangle> _arr = recFilter(listColorString,areaString);
                    timer = new Timer(100, new ActionListener() {
                        long start1 = System.currentTimeMillis();
                        public void actionPerformed(ActionEvent e) {

                            for(int i = 0 ;i<_arr.size();i++){
                                long elapsedTimeMillis = System.currentTimeMillis()-start1;
                                if (elapsedTimeMillis > 3000){
                                    if (elapsedTimeMillis <6000) _arr.get(i).moveDown();
                                    else
                                    {
                                        _arr.get(i).setCorY(arrCorY[i]);
                                        _arr.get(i).setLocation(_arr.get(i).getCorX(), _arr.get(i).getCorY());
                                        _arr.get(i).draw();
                                        timer.stop();
                                    }
                                }
                                else _arr.get(i).moveUp();
                            }
                        }
                    });
                    timer.start();
                }
            }catch (NullPointerException exp){
                System.out.println("hihi");
            }
        }
        else if (e.getActionCommand().equals("End")) {
            timer.stop();
        }
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
    public void changeElements(ArrayList<Rectangle> _arr){
        Rectangle duc;
        for(int i=0;i<arr.size();i++){
            duc = arr.get(i);
            panelPos.remove(duc);
        }
        panelPos.repaint();
        arr = _arr;
        createElement();
    }

    private JTextField arealist;
    private JList listColor;
    private Timer timer = null;
    private int[] arrCorY;
}
