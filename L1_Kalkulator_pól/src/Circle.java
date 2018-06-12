//klasa trójkąt dziedzicząca po Figure implementująca interfejs Print
public class Circle extends Figure implements Print
{
    private double r = 0;   //promień koła

    public Circle (double r)    //konstruktor kołą
    {
        this.r = r;
    }

    public double calculateArea()      //pole koła pi*r^2
    {
        return ((Math.PI)*(r*r));
    }

    public double calculatePerimeter()  //obwod kola 2*pi*r
    {
        return (2*(Math.PI)*r);
    }

    public void print()
    {
        System.out.println("KOŁO\nPromień: " + r + "\n");
        System.out.println("Pole: " + calculateArea() + "\nObwód: " + calculatePerimeter() + "\n");
    }
}
