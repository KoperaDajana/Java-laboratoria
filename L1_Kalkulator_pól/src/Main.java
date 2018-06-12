import java.util.InputMismatchException;    //dla wyjątków
import java.util.Scanner;   //dla wypisywania


public class Main
{
    static Scanner scanner = new Scanner(System.in); //pozwala na wprowadzanie danych z klawiatury

    public static void main(String[] args)
    {
        double a, b, c, h, r = 0;
        boolean exit = false;   //dla pętli while, dopóki nie true to program działa

        while(exit != true)
        {
            System.out.println("\nKALKULATOR PÓL I OBWODÓW FIGUR DWUWYMIAROWYCH\nWybierz figure:");
            System.out.println("-1- trójkąt\n-2- kwadrat\n-3- koło\n-0- koniec programu\n");
            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.println("Wybrano trójkąt");
                    try
                    {
                        System.out.println("Podaj długości boków (pierwszy wymiar = podstawa trojkata) " +
                                "oraz jego wysokość:\n");
                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        c = scanner.nextDouble();
                        h = scanner.nextDouble();

                        if((a <= 0) || (b <= 0) || (c <= 0) || (h <= 0))
                        {
                            throw new IllegalArgumentException("Błędnie wprowadzone dane, spróbuj ponownie");
                        } else {
                            Triangle triangle = new Triangle(a, b, c, h);
                            triangle.print();
                        }
                    } catch (InputMismatchException expection) {
                        System.out.println("Błędnie wprowadzone dane, znak musi być liczbą");
                    }
                    break;

                case 2:
                    System.out.println("Wybrano kwadrat");
                    try
                    {
                        System.out.println("Podaj długość boku:\n");
                        a = scanner.nextDouble();

                        if (a <= 0) {
                            throw new IllegalArgumentException("Błędnie wprowadzone dane, spróbuj ponownie");
                        } else {
                            Square square = new Square(a);
                            square.print();
                        }
                    } catch (InputMismatchException exception) {
                        System.out.println("Błędnie wprowadzone dane, znak musi być liczbą");

                    }
                    break;

                case 3:
                    System.out.println("Wybrano koło");
                    try {
                        System.out.println("Podaj długość promienia:\n");
                        r = scanner.nextDouble();

                        if (r <= 0) {
                            throw new IllegalArgumentException("Błędnie wprowadzone dane, spróbuj ponownie");
                        } else {
                            Circle circle = new Circle(r);
                            circle.print();
                        }
                    } catch (InputMismatchException exception){
                        System.out.println("Błędnie wprowadzone dane, znak musi być liczbą");
                    }
                    break;

                case 0:
                    System.out.println("Wybrano wyjście z programu\n");
                    exit = true;
                    break;

                default:
                    System.out.println("Błędnie wprowadzone dane, spróbuj ponownie");
                    break;
            }

        }
    }
}
