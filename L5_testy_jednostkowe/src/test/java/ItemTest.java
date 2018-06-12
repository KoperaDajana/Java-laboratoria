import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class ItemTest
{
    @Test
    public void print()
    {
        Item produkt = new Item("Co", ItemCondition.NEW, 1, 1);

        // utworzenie drugiego strumienia wyjścia
//        PrintStream out2 = System.out;
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//        produkt.printName();
//
//        System.setOut(out2);
//
//        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream1));
//        produkt.printName();
        //assertSame("\tNazwa: Co\n\tStan: Nowy\n\tMasa: 1.0\n\tLiczba: 1.0\n", outputStream1.toString());

        assertSame( "Co", produkt.name);
        assertSame( ItemCondition.NEW, produkt.itemCondition.NEW);
    }

    @Test
    public void printName()
    {
        Item produkt2 = new Item("Co2");
        assertSame("Co2", produkt2.name);
    }
}