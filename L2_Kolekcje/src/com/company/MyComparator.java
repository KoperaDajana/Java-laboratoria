import java.util.Comparator;

//C. IX. metoda sortByAmound, dla sortowania według ilości
public class MyComparator implements Comparator<Item>
{
    public int compare(Item name1, Item name2)              //dostaje dwa produkty
    {
        int result = name1.quantity - name2.quantity;       //wynik = ilość1 - ilość 2, jeśli = 0 porównuje nazwy
        if (result == 0)
        {
            return name1.compareTo(name2);
        }
        return result;
    }
}
