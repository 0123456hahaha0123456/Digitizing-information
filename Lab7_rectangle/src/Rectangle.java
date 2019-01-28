import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

public class Rectangle extends JPanel implements MouseMotionListener,Serializable{
    private int tmp = -1; // for drawtext in jpanel
    private int width;
    private int height;
    private Color color;
    private int corX;
    private int corY;
    private People people;
    public Rectangle(int corX,int corY,String color, int width, int height, People people){
        this.width = width;
        this.height = height;
        this.corY = corY;
        this.corX = corX;
        this.setLocation(corX,corY);
        this.setSize(width,height);
        this.people = people;
        setColor(color);
        this.addMouseMotionListener(this);
    }

    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setColor(String color){
        switch (color){
            case "red" : this.color = Color.RED;break;
            case "black" : this.color = Color.BLACK;break;
            case "green" : this.color = Color.GREEN;break;
            case "magenta" : this.color = Color.MAGENTA;break;
            case "yellow" : this.color = Color.YELLOW;break;
        }
    }
    public void setCorX(int corX){
        this.corX = corX;
    }
    public void setCorY(int corY){
        this.corY = corY;
    }
    public void setPeople(People people){
        this.people = people;
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public int getCorX() {
        return this.corX;
    }

    public int getCorY() {
        return this.corY;
    }

    public People getPeople() {
        return this.people;
    }

    public void moveUp(){
        this.setCorY(this.corY-1);
        setLocation(this.corX,this.corY);
        draw();
    }

    public void moveDown(){
        this.setCorY(this.corY+1);
        setLocation(this.corX,this.corY);
        draw();
    }

    public void setToolTip(){
        this.setToolTipText("x = " + this.getCorX() +" "
                +"y = " + this.getCorY() + " "
                +"width = " + this.getWidth() + " "
                +"height = " + this.getHeight() + " "
                +"people = " +this.getPeople()
        );
    }
    public void draw(){
        setToolTip();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(this.color);
        g.fillRect(0,0,this.width, this.height);
    }

    @Override
    public void mouseMoved (MouseEvent me)
    {
        repaint();
    }
    @Override
    public void mouseDragged (MouseEvent me) {
    }
    public static void main(String[] args){

        System.out.println("Tran Trung DUC");
    }
}
