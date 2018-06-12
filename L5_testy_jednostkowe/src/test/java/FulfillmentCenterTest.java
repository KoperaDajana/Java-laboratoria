import org.junit.Test;
import static org.junit.Assert.*;

public class FulfillmentCenterTest
{
    FulfillmentCenter wareHouse = new FulfillmentCenter("wareHouse", 100);

    @Test
    public void addProduct()
    {
        // dodanie do magazynu produktów
        wareHouse.addProduct(new Item("Produkt", ItemCondition.NEW, 1, 1));
        wareHouse.addProduct(new Item("Produkt", ItemCondition.USED, 1, 6));


        // sprawdzanie czy takie same oraz czy równe
        assertSame("Produkt", wareHouse.search("Produkt").name);
        assertSame(ItemCondition.NEW, wareHouse.search("Produkt").itemCondition);
        assertEquals(1, wareHouse.search("Produkt").mass, 0);
        assertEquals(2, wareHouse.search("Produkt").quantity, 0);
    }

    @Test
    public void searchId()
    {
        // dodanie produktów do magazynu
        wareHouse.addProduct(new Item("Produkt1", ItemCondition.NEW, 2, 2));
        wareHouse.addProduct(new Item("Produkt2", ItemCondition.USED, 1, 2));
        wareHouse.addProduct(new Item("Produkt3", ItemCondition.NEW, 2, 2));
        wareHouse.addProduct(new Item("Produkt4", ItemCondition.REFURBISHED, 3, 3));

        assertEquals(3, wareHouse.searchId(new Item("Produkt4", ItemCondition.REFURBISHED, 3, 2)));
    }

    @Test
    public void getProduct()
    {
        wareHouse.addProduct(new Item("Produkt1", ItemCondition.REFURBISHED, 1, 2));
        assertTrue(wareHouse.getProduct(new Item("Produkt1", ItemCondition.REFURBISHED, 1, 2)));
    }

    @Test
    public void removeProduct()
    {
        wareHouse.addProduct(new Item("Produkt1", ItemCondition.REFURBISHED, 1, 2));
        assertTrue(wareHouse.removeProduct(new Item("Produkt1", ItemCondition.REFURBISHED, 1, 2)));
    }

    @Test
    public void search()
    {
        wareHouse.addProduct(new Item("Produkt", ItemCondition.USED, 1, 2));

        assertSame("Produkt", wareHouse.search("Produkt").name);
        assertSame(ItemCondition.USED, wareHouse.search("Produkt").itemCondition);
        assertEquals(1, wareHouse.search("Produkt").mass, 0);
        assertEquals(4, wareHouse.search("Produkt").quantity, 0);
    }

    @Test
    public void searchPartial()
    {
        wareHouse.addProduct(new Item("Produkt1", ItemCondition.USED, 1, 0));
        wareHouse.addProduct(new Item("Produkt2", ItemCondition.USED, 1, 1));

        assertSame("Produkt1", wareHouse.searchPartial("o").get(0).name);
        assertSame(ItemCondition.USED, wareHouse.searchPartial("o").get(0).itemCondition);
        assertEquals(1, wareHouse.searchPartial("o").get(0).mass, 0);
        assertEquals(0, wareHouse.searchPartial("o").get(0).quantity, 0);

        assertSame("Produkt2", wareHouse.searchPartial("o").get(1).name);
        assertSame(ItemCondition.USED, wareHouse.searchPartial("o").get(1).itemCondition);
        assertEquals(1, wareHouse.searchPartial("o").get(1).mass, 0);
        assertEquals(2, wareHouse.searchPartial("o").get(1).quantity, 0);
    }

    @Test
    public void countByCondition()
    {
        wareHouse.addProduct(new Item("Produkt", ItemCondition.NEW, 1, 2));
        wareHouse.addProduct(new Item("Produkt2", ItemCondition.REFURBISHED, 2, 1));
        wareHouse.addProduct(new Item("Produkt3", ItemCondition.USED, 2, 3));
        wareHouse.addProduct(new Item("Produkt4", ItemCondition.NEW, 1, 4));
        wareHouse.addProduct(new Item("Produkt5", ItemCondition.REFURBISHED, 5, 1));
        wareHouse.addProduct(new Item("Produkt6", ItemCondition.USED, 1, 2));

        assertEquals(2, wareHouse.countByCondition(ItemCondition.NEW));
        assertEquals(2, wareHouse.countByCondition(ItemCondition.REFURBISHED));
        assertEquals(2, wareHouse.countByCondition(ItemCondition.USED));
    }

    @Test
    public void sortByName()
    {
        wareHouse.addProduct(new Item("Produkt1", ItemCondition.USED, 3, 4));
        wareHouse.addProduct(new Item("Produkt2", ItemCondition.NEW, 1, 2));
        wareHouse.addProduct(new Item("Produkt3", ItemCondition.REFURBISHED, 2, 3));

        assertEquals("Produkt1", wareHouse.sortByName().get(0).name);
        assertEquals("Produkt2", wareHouse.sortByName().get(1).name);
        assertEquals("Produkt3", wareHouse.sortByName().get(2).name);
    }

    @Test
    public void sortByAmount()
    {
        wareHouse.addProduct(new Item("Produkt1", ItemCondition.USED,3,2));
        wareHouse.addProduct(new Item("Produkt2", ItemCondition.NEW,1,3));
        wareHouse.addProduct(new Item("Produkt3", ItemCondition.REFURBISHED,2,4));


        assertEquals("Produkt1", wareHouse.sortByAmount().get(0).name);
        assertEquals("Produkt2", wareHouse.sortByAmount().get(1).name);
        assertEquals("Produkt3", wareHouse.sortByAmount().get(2).name);
    }

    @Test
    public void max()
    {
        wareHouse.addProduct(new Item("Produkt", ItemCondition.NEW,1,2));
        wareHouse.addProduct(new Item("Produkt2", ItemCondition.REFURBISHED,2,1));
        wareHouse.addProduct(new Item("Produkt3", ItemCondition.USED,2,10));
        wareHouse.addProduct(new Item("Produkt4", ItemCondition.NEW,1,2));

        assertSame("Produkt3", wareHouse.max().name);
        assertSame(ItemCondition.USED, wareHouse.max().itemCondition);
        assertEquals(2, wareHouse.max().mass,0);
        assertEquals(20, wareHouse.max().quantity,0);
    }
}