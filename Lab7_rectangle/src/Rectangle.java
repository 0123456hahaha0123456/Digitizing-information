import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Rectangle extends JPanel{
    private int width;
    private int height;
    private Color color;
    private int corX;
    private int corY;
    private People people;
    public Rectangle(int width,int height,String color, int corX, int corY, People people){
        this.width = width;
        this.height = height;
        this.corY = corY;
        this.corX = corX;
        this.people = people;
        setColor(color);
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

    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(this.getColor());
        //g.fillRect(this.getCorX(),this.getCorY(),this.getWidth(), this.getHeight());
        g.fillRect(0,0,this.getWidth(), this.getHeight());
    }
    public static void main(String[] args){
        System.out.println("Tran Trung DUC");
    }
}
