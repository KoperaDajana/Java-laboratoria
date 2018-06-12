//klasa trójkąt dziedzicząca po Figure implementująca interfejs Print
public class Triangle extends Figure implements Print
{
    private double a, b, c = 0; //boki trójkąta
    private double h = 0;       //wysokość trójkąta

    public Triangle(double a, double b, double c, double h) //konstruktor
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
    }

    public double calculateArea() //korzystanie z metody abstrakcyjnej klasy - pole powierzchni
    {
        return ((a*h)/2);
    }

    public double calculatePerimeter()
    {
        return (a+b+c);
    }

    public void print()
    {
        System.out.println("TRÓJKĄT\nBoki trójkąta (gdzie pierwszy wymiar to jego podstawa): " + a + ", " + b + ", "
                + c + "\nWysokość: " + h + "\n");
        System.out.println("Pole: " + calculateArea() + "\nObwód: " + calculatePerimeter() + "\n");
    }
}
