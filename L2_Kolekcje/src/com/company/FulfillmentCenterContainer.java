
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FulfillmentCenterContainer
{
    private HashMap<String, FulfillmentCenter> fulfillmentCenterHashMap = new map HashMap();


    //I. metoda, która dodaje nowy kontener o podanej nazwie i zadanej pojemności
    public void addCenter(String nameOfWarehouse, double maxCapacity)       //nazwa, maksymalna ilosc w magazynie
    {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(nameOfWarehouse, maxCapacity);
        fulfillmentCenterHashMap.put(nameOfWarehouse, fulfillmentCenter);       //umieszcza na mapie stworzony obiekt
    }


    //I. metod, która dodaje nowy kontener za pomoca nazwy magazynu
    public void addCenter(FulfillmentCenter fulfillmentCenter)
    {
        fulfillmentCenterHashMap.put(fulfillmentCenter.nameOfWarehouse, fulfillmentCenter);
    }


    //II. usuwa magazyn o podanej nazwie
    public void removeCenter(String nameOfWarehouse)
    {
        fulfillmentCenterHashMap.remove(nameOfWarehouse);
    }


    ///III. metoda szuka, a następnie zwraca puste magazyny
    public List<FulfillmentCenter> findEmpty()
    {
        List<FulfillmentCenter> emptyFC = new ArrayList<FulfillmentCenter>(); //stworzenie listy tablicowej emptyFC
        System.out.println("\tWypisanie: ");
        for(String key: fulfillmentCenterHashMap.keySet())          //keySet - zwraca zbiór kluczy
        {
            FulfillmentCenter value = fulfillmentCenterHashMap.get(key); //get(key) zwraca wartosc przypisana do klucza
            if(value.listOfProducts.size() == 0)
            {
                emptyFC.add(value);
            }
            System.out.println("\t" + key + " - przechowuje: " + value.listOfProducts.size());
        }
        return emptyFC;
    }


    //VI. metoda wypisująca nazwę oraz % zapełenienie magazynu
    public void summary()
    {
        System.out.println("\tZapełnienie magazynów: ");
        for(String key: fulfillmentCenterHashMap.keySet())
        {
            FulfillmentCenter value = fulfillmentCenterHashMap.get(key);
            System.out.println("\t" + key + " zapełnienie [%]: " + ((value.currentQuantity/value.maxCapacity)*100.0));
            //aktualna liczba/maxymalna pojemnosc magazynu * 100
        }
    }
}
