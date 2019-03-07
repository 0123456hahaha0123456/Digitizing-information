import control.UTF8Control;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


public class frameClient  {


    public frameClient(ArrayList<Rectangle> arr){
        frameCli = new JFrame("Client");
        this.arr = arr;
        createFrame();
    }

    private void createFrame(){

        frameCli.getContentPane().setBackground(Color.white);
        //frameCli.getContentPane().setVisible(false);
        frameCli.setLayout(null);

        frameCli.setSize(1000,1050);
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

        //add menu to jframe
        JMenuBar menuBar = new JMenuBar();
        menu = new Menu(this);
        menuBar.add(menu);
        frameCli.setJMenuBar(menuBar);

        changeLanguage(Locale.getDefault());

        //add Time

        panelTime = new JPanel();
        panelTime.setSize(500,50);
        panelTime.setLocation(500,900);
        panelTime.setLayout(new FlowLayout());
        addTimeDate(panelTime,Locale.getDefault());
        frameCli.add(panelTime,BorderLayout.SOUTH);

        frameCli.setVisible(true);
    }

    private void createButton(){
        panelBut.setLayout(new GridLayout(1,4));

        //tao filter color - Jlist
        listColor = new JList();
        listColor.setListData(color);
        listColor.setFont(new Font("Arial",Font.ITALIC, 18));
        listColor.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(listColor);
        listScroller.setPreferredSize(new Dimension(250, 100));

        listCol = new JPanel();
        listCol.add(listScroller);
        setTitle(listCol,"filterByColor");

        //tao filter dien tich - Jtexfield
        arealist = new JTextField();
        arealist.setColumns(10);

        area = new JPanel();
        setTitle(area,"filterByArea");
        area.add(arealist,BorderLayout.CENTER);

        //tao nut start
        JPanel start = new JPanel();
        startButton = new JButton("Start");
        //startButton.setText("Start");
        startButton.addActionListener(this::actionPerformed);
        start.add(startButton,BorderLayout.CENTER);

        //tao nut End
        JPanel end = new JPanel();
        endButton = new JButton("End");
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
            listColorString = rbBoss.getString(listColorString);
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
                case "All":
                    listColorString = null;
                    break;
            }
        }

        //find which rec will acceptable condition
        ArrayList<Rectangle> _arr = new ArrayList<Rectangle>(){};
        arrCorY = new int[100];
        position = new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            Rectangle tmp = arr.get(i);
            if ((tmp.getHeight() * tmp.getWidth() >area) && ((tmp.getColor() == listColor) || (listColorString==null))){
                arrCorY[_arr.size()] = tmp.getCorY();
                position.add(tmp.getPeople());
                _arr.add(tmp);

            }
        }
        for(int i =0;i<_arr.size();i++) System.out.println(arrCorY[i]);
        return _arr;
    }

    private void actionPerformed(ActionEvent e){
        String listColorString;
        String areaString;
        ArrayList<Rectangle> fixArr = new ArrayList<>();
        ArrayList<Rectangle> _arr;// = new ArrayList<>();
        if (rbBoss.getString(e.getActionCommand()).equals("Start")){
            try{
                listColorString  = listColor.getSelectedValue().toString();
                if (!arealist.getText().equals("")) areaString =  arealist.getText() ;
                else areaString = "0";

                if (listColorString!=null || areaString!=null) {
                    //System.out.println(listColorString + " " + areaString);
                    _arr = recFilter(listColorString,areaString);
                    //fixArr = _arr;
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
        else if (rbBoss.getString(e.getActionCommand()).equals("End")) {
            fixPosition(position);
            timer.stop();
        }
    }

    //fix postition of rec when click End
    private void fixPosition(ArrayList<People> _fixArr){
        for(int i=0;i<arr.size();i++){
            for(int j=0;j<position.size();j++) if (arr.get(i).getPeople().equals(position.get(j))){
                arr.get(i).setCorY(arrCorY[j]);
                arr.get(i).setLocation(arr.get(i).getCorX(), arr.get(i).getCorY());
                arr.get(i).draw();
            }
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

    private void addTimeDate(JPanel tmp,Locale locale){
        changeTime(locale);

        tmp.add(timeDate);
        totalLocale = locale;
        Timer t1 = new Timer(1000,updateClockAction);
        t1.start();
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

    private void changeTime(Locale tmp){
        totalLocale = tmp;
        Date myDate = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,totalLocale);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG,totalLocale);

        String date = df.format(myDate);
        String time = tf.format(myDate);
        String pattern = date + " " + time;

        timeDate.setText(pattern);
    }

    ActionListener updateClockAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeTime(totalLocale);
        }
    };

    private void changeLanguageJList(ResourceBundle rb){
        String[] tmp = new String[6];
        for(int i=0;i<6;i++) tmp[i] = rb.getString(color[i]);
        this.listColor.setListData(tmp);
    }
    public void changeLanguage(Locale locale){
        ResourceBundle rb = ResourceBundle.getBundle("resources.Resources", locale, new UTF8Control());
        frameCli.setTitle(rb.getString("Client"));
        startButton.setText(rb.getString("Start"));
        endButton.setText(rb.getString("End"));
        setTitle(listCol,rb.getString("filterByColor"));
        setTitle(area,rb.getString("filterByArea"));
        changeLanguageJList(rb);
        menu.changeLanguage(locale);
        changeTime(locale);
    }

    private ArrayList<Rectangle> arr;
    private JFrame frameCli;
    private JPanel panelPos;
    private JPanel panelBut;
    private JTextField arealist;
    private JList listColor;
    private Timer timer = null;
    private int[] arrCorY;
    private ArrayList<People> position;
    private Menu menu;
    private JButton startButton;
    private JButton endButton;
    private JPanel listCol;
    private JPanel area;
    private JPanel panelTime;
    private String[] color = {"red", "green", "black", "magenta", "yellow", "All"};
    private Locale localeBoss = new Locale("en","EN");
    private ResourceBundle rbBoss = ResourceBundle.getBundle("resources.Resources", localeBoss, new UTF8Control());
    private JFormattedTextField timeDate = new JFormattedTextField();;
    private Locale totalLocale;
}
