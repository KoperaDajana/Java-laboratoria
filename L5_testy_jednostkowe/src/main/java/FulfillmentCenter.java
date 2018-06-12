import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.List;

//C. klasa FulfillmentCenter (centrala magazynu)
public class FulfillmentCenter
{
    public String nameOfWarehouse;                                  //nazwa magazynu
    public double maxCapacity = 1000000;                            //maksymalna masa wszystkich produktów w magazynie
    public double currentQuantity = 0;                              //aktualna ilość produktów w magazynie PRESENTMAS
    public List<Item> listOfProducts = new LinkedList<Item>();         //lista produktów

    //konstruktor
    public FulfillmentCenter(String nameOfWarehouse, double maxCapacity)
    {
        this.nameOfWarehouse = nameOfWarehouse;
        this.maxCapacity = maxCapacity;
    }

    int searchId (Item item) {
        /* szukanie pozycji przedmiotow w liscie po nazwie
            zwroci -1 jezeli nie znajdzie produktu
         */
        for (int i=0; i<listOfProducts.size(); i++) {
            // szukanie czy istnieje obiekt o takiej nazwie
            if(listOfProducts.get(i).name.equals(item.name)) {
                // jezeli istniej to zwraca jego ID
                // listOfProducts.get(i).amount += item.amount;
                return i; // wychodzimy
            }
        }
        return -1; // zwraca -1 jezeli nie ma takiego produktu
    }

    public int compare(String name1, String name2)  //na rzecz metody search
    {
        int result = 1;
        if (name1.intern() == name2.intern())   //przytrzymuję nazwy i je porównuje, jeśli równe ustawiam result na 0
        {
            result = 0;
        }
        return result;
    }

    public int compareToName(Item product)        //na rzecz metod: addProduct, removeProduct
    {
        int result = 1;
        Iterator<Item> iterator = listOfProducts.iterator(); //przechodzi po kolejnych elementach listy
        while (result != 0 && iterator.hasNext())            //jeśli wynik różny od 0 i lista ma następny node
        {
            result = product.name.compareTo(iterator.next().name);      //nazwe argumentu z nazwą kolejnego
        }
        return result;
    }


    //I. metoda, dodaje produkt, jeśli obecny -> sumuje, produkt może być dodany jeśli nie przekroczy max pojemności
    public void addProduct(Item product)
    {
        if (currentQuantity + product.mass > maxCapacity)   //sprawdzenie, czy masa nie przekracza max dopuszczalnej
        {
            System.err.println("Magazyn jest pełen!");
        } else {                                            //jeśli nie przekracza
            listOfProducts.add(product);
            currentQuantity += product.mass;
            if (compareToName(product) == 0)                //sumowanie jeśli takie same nazwy
            {
                product.quantity+= product.quantity;        //++;
            }
        }
    }

    //II. metoda, zmniejsza ilość-- lub całkowicie usuwa produkt, jeśli po zmianie = 0
    boolean getProduct(Item product)
    {
        /* zmnijszenie ilosci o 1 lub usuniecie obiektu z listy jezeli jest ostatni */

        int i = searchId(product);

        if(i == -1) {
            return false; // daje info, ze nie ma takiego
        }
        else {
            if(listOfProducts.get(i).quantity > 1) {
                listOfProducts.get(i).quantity -= 1;
            }
            else {
                listOfProducts.remove(i);
            }
        }
        return true; // daje info, że jest ok (przeszlo)
    }

    //III. metoda usuwa całkowicie
    boolean removeProduct (Item product)
    {
        /* usuwa calkowicie produkt */

        int i = searchId(product);

        if(i == -1) {
            /* jak nie ma produktu na liscie to zwraca false */
            return false;
        }

        listOfProducts.remove(i);
        return true;
    }

    //IV. metoda przyjmuje nazwę produktu i go zwraca, COMPARATOR
    public Item search(String productName)
    {
        Iterator<Item> iterator = listOfProducts.iterator();    //przechodzi po kolejnych elementach listy
        while (iterator.hasNext())                              //dopóki jest kolejny element na liście
        {
            Item now = iterator.next();                         //ustawia na następny produkt "now"
            if(compare(productName, now.name) == 0)             //porównuje nazwy argumentu z nastepnym, jeśli równe
            {
                return now;                                     //zwraca
            }
        }
        return null;
    }


    //V. metoda przyjmuje fragment nazwy i zwraca wszystkie, które do niego pasują
    public List<Item> searchPartial(final String partProductName)
    {
        Iterator<Item> iterator = listOfProducts.iterator(); //przechodzi po kolejnych elementach listy
        ArrayList<Item> matchedProductName = new ArrayList<Item>();//dla teraźniejszego itemu, będzie sprawdzany fragm
        while (iterator.hasNext())                           //dopóki ma następnego
        {
            Item now = iterator.next();
            if (now.name.contains(partProductName))         //jeśli zawiera
            {
                matchedProductName.add(now);
            }
        }
        return matchedProductName;                          //zwraca listę do której pasuje fragment
    }


    //VI. metoda zwraca ilość produktów o danym stanie
    public int countByCondition(ItemCondition iCondition)
    {
        Iterator<Item> iterator = listOfProducts.iterator(); //przechodzi po kolejnych elementach listy
        int numberOfElements = 0;
        while (iterator.hasNext())
        {
            if (iterator.next().itemCondition.equals(iCondition))       //equals -> =, porównuje dwie tablice
            {                               //jeśli następny itemCond = argument to zwiększa licznik
                numberOfElements++;
            }
        }
        return numberOfElements;            //zwraca liczbę produktów o podanym stanie w arg
    }


    //VII. metoda wypisuje informacje o wszystkich produktach
    public void summary()
    {
        for(Item product: listOfProducts)           //dla całej listy produktów wypisz
        {
            System.out.println("\tNAZWA PRODUKTU: " + product.name + "\n\tMagazyn: " + nameOfWarehouse);
            System.out.println("\tStan produktu: " + product.itemCondition + ", Ilość: " + product.quantity);
            System.out.println("\tMasa produktu: " + product.mass + "\n");
        }
    }


    //VIII. metoda sortuje produkty po nazwie alfabetycznie, zwraca posortowaną listę
    public List<Item> sortByName()
    {
        Collections.sort(listOfProducts);           //sortowanie listy
        return listOfProducts;
    }


    //IX. metoda sortuje malejąco po ilości produktów, zwraca posortowaną listę, COMPARATOR WŁASNY: MyComparator
    public List<Item> sortByAmount()
    {
        Collections.sort(listOfProducts, new MyComparator());   //sortuje liste według metody w MyComparator
        return listOfProducts;
    }

    //X. metoda zwraca produkt którego jest najwięcej
    public Item max()
    {   //zwraca maksymalny element danej kolekcji, zgodnie z kolejnością wywołaną przez określony komparator
        return Collections.max(listOfProducts, new MyComparator());
    }

}
