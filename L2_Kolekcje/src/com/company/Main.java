import java.util.List;

//LAB2 JAVA I SYSTEMY MOBILNE: prosty symulator usługi magazynowej
public class Main
{

    public static void main(String[] args)
    {
        //-1-
        //tworzenie produktów
        //System.out.println("> Użycie konstruktora Item (2b.i)");
	    Item product1 = new Item("Drewniane trzewiki", ItemCondition.REFURBISHED, 5865);
        Item product2 = new Item("Okulary", ItemCondition.NEW, 400);
        Item product3 = new Item("Lampy", ItemCondition.USED, 105);
        Item product4 = new Item("Lodówka", ItemCondition.NEW, 35);
        Item product5 = new Item("Torba", ItemCondition.USED, 100);
        Item product6 = new Item("Okna dachowe", ItemCondition.NEW, 65);

        System.out.println("> Użycie metody print(2b.ii) dla " + product1.name);
        product1.print();

        //tworzenie magazynów
        FulfillmentCenter fulfillmentCenter1 = new FulfillmentCenter("Nowy Sącz", 60000);
        FulfillmentCenter fulfillmentCenter2 = new FulfillmentCenter("Pekin", 500);
        FulfillmentCenter fulfillmentCenter3 = new FulfillmentCenter("Berlin", 1500);
        FulfillmentCenter fulfillmentCenter4 = new FulfillmentCenter("Sosnowiec", 29000);

        //tworzenie kontenera (mapy)
        FulfillmentCenterContainer fulfillmentCC = new FulfillmentCenterContainer();

        //-2-
        //dodanie produktów do poszczególnych magazynów-----------------------------------------------------------------
        System.out.println("> Użycie metody addProduct(2c.i) dla wszystkich magazynów");
        fulfillmentCenter1.addProduct(product1);
        fulfillmentCenter1.addProduct(product2);
        fulfillmentCenter1.addProduct(product3);
        fulfillmentCenter1.addProduct(product4);
        fulfillmentCenter2.addProduct(product2);
        fulfillmentCenter2.addProduct(product5);
        fulfillmentCenter3.addProduct(product2);
        fulfillmentCenter3.addProduct(product6);
        fulfillmentCenter3.addProduct(product5);
        fulfillmentCenter4.addProduct(product4);
        fulfillmentCenter4.addProduct(product1);


        //zmiana lub usunięcie produktu---------------------------------------------------------------------------------
        System.out.println("\n> Użycie metody getProduct(2c.ii) dla magazynu " + fulfillmentCenter3.nameOfWarehouse +
                "," + " produktu: " + product6.name);
        fulfillmentCenter3.getProduct(product6);


        //usunięcie produktu--------------------------------------------------------------------------------------------
        System.out.println("\n> Użycie metody removeProduct(2c.iii) dla magazynu " +
                fulfillmentCenter2.nameOfWarehouse + ", produktu " +product2.name);
        fulfillmentCenter2.removeProduct(product6);
        fulfillmentCenter2.removeProduct(product6);


        //wyszukanie produktu-------------------------------------------------------------------------------------------
        System.out.println("\n> Użycie metody search(2c.iv) dla magazynu " + fulfillmentCenter3.nameOfWarehouse);
        System.out.println("\tSzukana: " + fulfillmentCenter3.search("Okulary").name + "\n\tliczba magazynów: "
                + fulfillmentCenter3.search("Okulary").quantity);


        //wyszukiwanie po fragmencie tekstu-----------------------------------------------------------------------------
        System.out.println("\n> Użycie metody searchPartial(2c.v) dla magazynu " + fulfillmentCenter3.nameOfWarehouse);
        List<Item> fragment = fulfillmentCenter2.searchPartial("or");
        for (int i = 0; i<fulfillmentCenter3.searchPartial("or").size(); i++)
        {
            System.out.println("\tWyszukiwane po fragmencie 'or':\n\tZnalezniona nazwa: " + fragment.get(i).name);
        }
        //wypisanie w ilu magazynach znaleziono powyższy fragment
        for (Item product: fulfillmentCenter2.listOfProducts)
            System.out.println("\tW " + product.quantity + " magazynach\n");


        //sortowanie za pomocą stanu------------------------------------------------------------------------------------
        System.out.println("> Użycie metody countByCondition(2c.vi) dla magazynu " + fulfillmentCenter1.nameOfWarehouse);
        System.out.println("\tLiczba produktow w stanie NEW: " + fulfillmentCenter1.countByCondition(ItemCondition.NEW)
                + "w magazynie " + fulfillmentCenter1.nameOfWarehouse + "\n");


        //wypisanie informacji o wszystkich produktach------------------------------------------------------------------
        System.out.println("> Użycie metody summary(2c.vii) dla magazynu: " +
                fulfillmentCenter4.nameOfWarehouse);
        fulfillmentCenter4.summary();


        //sortuje produkty po nazwie alfabetycznie, zwraca posortowaną listę--------------------------------------------
        System.out.println("> Użycie metody sortByName(2c.viii) dla magazynu " + fulfillmentCenter1.nameOfWarehouse);
        List<Item> sorted;
        sorted = fulfillmentCenter1.sortByName();
        for (Item product: sorted)
        {
            System.out.println("\tNazwa: " + product.name + ", stan: " + product.itemCondition + ", masa: " + product
                    .mass);
        }


        //sortuje produkty po ilości------------------------------------------------------------------------------------
        System.out.println("\n> Użycie metody sortByAmount(2c.ix) dla magazynu " +
                fulfillmentCenter1.nameOfWarehouse);
        List<Item> sortedByAmount;
        sortedByAmount = fulfillmentCenter1.sortByAmount();
        for (Item product: sortedByAmount)
        {
            System.out.println("\tNazwa: " + product.name + ", stan: " + product.itemCondition + ", ilość: " + product
                    .quantity);
        }


        //zwraca produkt którego jest najwięcej-------------------------------------------------------------------------
        System.out.println("\n> Użycie metody max(2c.x) dla magazynu " + fulfillmentCenter1.nameOfWarehouse);
        System.out.println("\tNajwięcej produktów: " + fulfillmentCenter1.max().name + "\n");


        //-3-
        //dodanie magazynów do kontenera--------------------------------------------------------------------------------
        System.out.println("> Użycie metody addCenter(2d.i) dodanie magazynu " +
                fulfillmentCenter1.nameOfWarehouse + " do kontenera\n");
        fulfillmentCC.addCenter(fulfillmentCenter1);
        fulfillmentCC.addCenter("Co", 6006);
        fulfillmentCC.addCenter(fulfillmentCenter3);


        //usunięcie magazynu o podanej nazwie---------------------------------------------------------------------------
        System.out.println("> Użycie metody removeCenter(2d.ii)");
        fulfillmentCC.removeCenter("Pekin");


        //zwrot listy pustych magazynów---------------------------------------------------------------------------------
        System.out.println("\n> Użycie metody findEmpty(2d.iii)");
        List<FulfillmentCenter> emptyFC = fulfillmentCC.findEmpty();
        System.out.println("Puste magazyny: ");
        for (FulfillmentCenter fulfillmentCenter : emptyFC)
        {
            System.out.println("\t" + fulfillmentCenter.nameOfWarehouse);
        }


        //wypisanie nazwy magazynu oraz jego procentowe zapełenienie----------------------------------------------------
        System.out.println("\n> Użycie metody summary(2d.iv)");
        fulfillmentCC.summary();

    }
}
