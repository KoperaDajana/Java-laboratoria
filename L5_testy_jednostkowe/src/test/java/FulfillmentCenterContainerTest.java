import org.junit.Test;
import static org.junit.Assert.*;

public class FulfillmentCenterContainerTest
{
    FulfillmentCenterContainer container = new FulfillmentCenterContainer();

    @Test
    public void addCenter()
    {
        container.addCenter("Magazyn", 100);
        assertSame("Magazyn", container.fulfillmentCenterHashMap.get("Magazyn").nameOfWarehouse);
        assertEquals(100.0, container.fulfillmentCenterHashMap.get("Magazyn").maxCapacity, 0);
    }

    // z wyrzucaniem wyjątku
           @Test (expected = IllegalArgumentException.class)
        public void removeCenter()
        {
            boolean thrown = false;
            container.addCenter("Magazyn1", 100);
            container.addCenter("Magazyn2", 100);
            container.removeCenter("NOTEXISTED");

            container.removeCenter("Magazyn1");

        assertTrue(container.fulfillmentCenterHashMap.get("Magazyn1") == null);
    }

    @Test
    public void findEmpty() {
        container.addCenter("Magazyn1",100);
        container.addCenter("Magazyn2",150);
        container.addCenter("Magazyn3",200);
        container.addCenter("Magazyn4",250);


        container.fulfillmentCenterHashMap.get("Magazyn1").addProduct(new Item("Produkt", ItemCondition.NEW,10,2));
        container.fulfillmentCenterHashMap.get("Magazyn3").addProduct(new Item("Produkt", ItemCondition.NEW,10,2));
        container.fulfillmentCenterHashMap.get("Magazyn1").addProduct(new Item("Produkt2", ItemCondition.NEW,20,5));
        container.fulfillmentCenterHashMap.get("Magazyn3").addProduct(new Item("Produkt2", ItemCondition.NEW,20,5));

        assertEquals("Magazyn1", container.findEmpty().get(0).nameOfWarehouse);
        container.fulfillmentCenterHashMap.get("Magazyn1").addProduct(new Item("Produkt3", ItemCondition.NEW,500,
                3));
        container.fulfillmentCenterHashMap.get("Magazyn1").addProduct(new Item("Produkt4", ItemCondition.NEW,5,3));

        assertEquals("Magazyn4", container.findEmpty().get(0).nameOfWarehouse);
        assertEquals("Magazyn2", container.findEmpty().get(1).nameOfWarehouse);
    }
}