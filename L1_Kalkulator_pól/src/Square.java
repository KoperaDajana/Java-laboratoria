
//klasa kwadrat dziedzicząca po Figure implementująca interfejs Print
public class Square extends Figure implements Print
{
    private double a = 0;    //bok kwadratu

    public Square(double a)
    {
        this.a = a;
    }

    public double calculateArea()   //obliczanie pola kwadratu
    {
        return (a*a);
    }

    public double calculatePerimeter()  //obliczanie obwodu kwadratu
    {
        return (4*a);
    }

    public void print()
    {
        System.out.println("KWADRAT\nBok kwadratu: " + a + "\n");
        System.out.println("Pole: " + calculateArea() + "\nObwód: " + calculatePerimeter() + "\n");
    }


}
