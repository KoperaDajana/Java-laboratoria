
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FulfillmentCenterContainer
{
    HashMap<String, FulfillmentCenter> fulfillmentCenterHashMap = new HashMap();

    public void addCenter(String nameOfWarehouse, double maxCapacity)       //nazwa, maksymalna ilosc w magazynie
    {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(nameOfWarehouse, maxCapacity);
        fulfillmentCenterHashMap.put(nameOfWarehouse, fulfillmentCenter);       //umieszcza na mapie stworzony obiekt
    }

    void removeCenter (String name)
    {
        if(fulfillmentCenterHashMap.get(name) == null) {
            throw new IllegalArgumentException("Exception!");
        }
        fulfillmentCenterHashMap.remove(name);
    }



    List<FulfillmentCenter> findEmpty()
    {
        List<FulfillmentCenter> listLocal = new ArrayList<FulfillmentCenter>();

        for(Map.Entry key:fulfillmentCenterHashMap.entrySet()) {
            if(fulfillmentCenterHashMap.get(key.getKey()).listOfProducts.size() == 0) {
                listLocal.add(fulfillmentCenterHashMap.get(key.getKey()));
            }
        }
        return listLocal;
    }

}