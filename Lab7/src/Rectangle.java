import java.awt.*;

public class Rectangle {
    private int width;
    private int height;
    private Color color;
    private double corX;
    private double corY;
    private People people;
    public Rectangle(int width,int height,String color, double corX, double corY, People people){
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
    public void setCorX(double corX){
        this.corX = corX;
    }
    public void setCorY(double corY){
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

    public double getCorX() {
        return corX;
    }

    public double getCorY() {
        return corY;
    }

    public People getPeople() {
        return people;
    }
}
