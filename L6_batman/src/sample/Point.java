package sample;

public class Point
{
    double x;
    double y;                   // współrzędne
    boolean inside;             // czy w środku wykresu, czy poza
    private int counter;        // licznik

    public Point(double x, double y, boolean inside, int counter)
    {
        this.x = x;
        this.y = y;
        this.inside = inside;
        this.counter = counter;
    }

    // wyciąganie współrzędnych punktu
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    // jest w środku
    public boolean isInside() {
        return inside;
    }

    // ustawienie licznika
    public void setCounter(int counter) {
        this.counter = counter;
    }
    // wyciąganie licznika
    public int getCounter() {
        return counter;
    }
}