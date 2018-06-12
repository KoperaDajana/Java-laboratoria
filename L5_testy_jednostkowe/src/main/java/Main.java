import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
/*
        // Tworzenie produktow
        System.out.println("\t Użycie konstruktora Item.");
        Item product1 = new Item("Książka", ItemCondition.NEW, 1,3);
        Item product2 = new Item("Buty", ItemCondition.USED, 2,5);
        Item product3 = new Item("Deska do prasowania", ItemCondition.REFURBISHED, 3,2);
        Item product4 = new Item("Torebka", ItemCondition.NEW, 1,1);
        Item product5 = new Item("Szafa", ItemCondition.REFURBISHED, 5,3);
        Item product6 = new Item("Podręcznik biologii", ItemCondition.USED, 3,2);
        System.out.println("Produkt 1: " + product1.name);
        product1.print();

        // Tworzenie magazynow
        System.out.println("\t Tworzenie magazynów.");
        FulfillmentCenter fulfillmentCenter1 = new FulfillmentCenter("U Bolka",456);
        FulfillmentCenter fulfillmentCenter2 = new FulfillmentCenter("Nowy Dom",246);
        FulfillmentCenter fulfillmentCenter3 = new FulfillmentCenter("Nowości",986);

        // Tworzenie kontenera - mapy
        System.out.println("\t Tworzenie kontenera.");
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        fulfillmentCenter3.addProduct(product6);

        // Dodanie produktów do kontenerow
        System.out.println("\t Dodanie produktów do kontenera.");
        fulfillmentCenter1.addProduct(product3);
        fulfillmentCenter2.addProduct(product4);
        fulfillmentCenter1.addProduct(product2);
        fulfillmentCenter2.addProduct(product6);
        fulfillmentCenter3.addProduct(product4);
        fulfillmentCenter1.addProduct(product5);
        fulfillmentCenter2.addProduct(product5);
        fulfillmentCenter3.addProduct(product6);
        fulfillmentCenter2.addProduct(product1);

        // Zamiana lub usuniecie produktu
        System.out.println("\t Zmiana lub usunięcie produktu dla magazynu " + fulfillmentCenter3.nameOfWarehouse + " produktu " + product6.name + ".");
        fulfillmentCenter3.getProduct(product6);

        // Usuniecie produktu
        System.out.println("\t Usunięcie produktu " + product4.name + " dla magazynu " + fulfillmentCenter2.nameOfWarehouse + ".");
        System.out.println(" Ilość produktów w magazynie " + fulfillmentCenter2.nameOfWarehouse + " : " + fulfillmentCenter2.listOfProducts.size());
        fulfillmentCenter2.removeProduct(product4);
        System.out.println(" Ilość produktów w magazynie " + fulfillmentCenter2.nameOfWarehouse + " : " + fulfillmentCenter2.listOfProducts.size());

        // Wyszukanie produktu
        System.out.println("\t Wyszukanie produktu dla magazynu " + fulfillmentCenter1.nameOfWarehouse);
        System.out.println("Szukany Produkt to Deska do prasowania:");
        fulfillmentCenter1.search("Deska do prasowania").print();
//        System.out.println("\t Szukana: " + fulfillmentCenter1.search("Deska do prasowania").name + "\n\t liczba magazynów: " + fulfillmentCenter1.search("Deska do prasowania").amount);

        // Wyszukiwanie po fragmencie tekstu
        System.out.println("\t Wyszukanie produktu po fragmencie nazwy dla magazynu " + fulfillmentCenter2.nameOfWarehouse);
        List<Item> fragment = fulfillmentCenter2.searchPartial("af");
        for(int i=0; i<fulfillmentCenter2.searchPartial("af").size(); i++) {
            System.out.println("Wyszukanie po fragmencie 'af': \t znaleziona nazwa: " + fragment.get(i).name);
        }
        // w ilu magazynach znaleziono fragment
        for (Item product: fulfillmentCenter2.listOfProducts)
            System.out.println("\t W " + product.amount + " magazynie.");

        System.out.println("\t Wyszukanie produktu po fragmencie nazwy dla magazynu " + fulfillmentCenter1.nameOfWarehouse);
        List<Item> fragment2 = fulfillmentCenter1.searchPartial("ut");
        for(int i=0; i<fulfillmentCenter1.searchPartial("ut").size(); i++) {
            System.out.println("Wyszukanie po fragmencie 'ut': \t znaleziona nazwa: " + fragment2.get(i).name);
        }
        // w ilu magazynach znaleziono fragment
        for (Item product: fulfillmentCenter1.listOfProducts)
            System.out.println("\t W " + product.amount + " magazynie.");

        // Sortowanie za pomoca stanu
        System.out.println("\t Sortowanie za pomocą stanu dla magazynu " + fulfillmentCenter3.nameOfWarehouse);
        System.out.println("Liczba produktów w stanie NEW: " + fulfillmentCenter3.countByCondition((ItemCondition.NEW)));
        System.out.println("Liczba produktów w stanie USED: " + fulfillmentCenter3.countByCondition((ItemCondition.USED)));
        System.out.println("Liczba produktów w stanie REFURBISHED: " + fulfillmentCenter3.countByCondition((ItemCondition.REFURBISHED)));

        // Sortowanie po nazwie
        System.out.println("\t Sortowanie po nazwie dla magazynu :" + fulfillmentCenter3.nameOfWarehouse);
        List<Item> sorted;
        sorted = fulfillmentCenter3.sortByName();
        for (Item product: sorted) {
            System.out.println("\t Nazwa produktu : " + product.name + ", stan: " + product.condition + ", masa: " + product.mass + ", ilość: " + product.amount);
        }
        System.out.println("\t Sortowanie po nazwie dla magazynu :" + fulfillmentCenter2.nameOfWarehouse);
        List<Item> sorted2;
        sorted2 = fulfillmentCenter2.sortByName();
        for (Item product: sorted2) {
            System.out.println("\t Nazwa produktu: " + product.name + ", stan: " + product.condition + ", masa: " + product.mass + ", ilość: " + product.amount);
        }

        // Sortowanie po ilosci
        System.out.println("\t Sortowanie po ilości dla magazynu :" + fulfillmentCenter1.nameOfWarehouse);
        List<Item> sortedByAmount;
        sortedByAmount = fulfillmentCenter1.sortByAmount();
        for (Item product: sortedByAmount) {
            System.out.println("\t Nazwa produktu: " + product.name + ", stan: " + product.condition + ", ilość: " + product.amount);
        }
//        List<Item> sortedByAmount2;
//        sortedByAmount2 = fulfillmentCenter1.sortByAmount();
//        for (Item product: sortedByAmount2) {
//            System.out.println("\t Nazwa: " + product.name + ", stan: " + product.condition + ", ilość: " + product.amount);
//        }

        // Zwraca produkt, ktorego jest najwiecej
        System.out.println("\t Którego produktu w magazynie " + fulfillmentCenter3.nameOfWarehouse + " jest najwiecej?");
        System.out.println("\t Najwiecej produktow w magazynie " + fulfillmentCenter3.nameOfWarehouse + " jest : " + fulfillmentCenter3.max().name + "\n");

        // Wypisanie informacji o wszystkich produktach w magazynie
        System.out.println("\t Informacja o wszystkich produktach w magazynie " + fulfillmentCenter1.nameOfWarehouse);
        fulfillmentCenter1.summary();

        // Dodanie magazynow do kontenera
        System.out.println("\t Dodanie magazynu " + fulfillmentCenter1.nameOfWarehouse + " do kontenera.");
        fulfillmentCenterContainer.addCenter("Starocie", 100);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter3);

        // Usuniecie magazynu o podanej nazwie
        System.out.println("\t Usunięcie magazynu: " + fulfillmentCenter2.nameOfWarehouse);
        fulfillmentCenterContainer.removeCenter("Nowy Dom");

        // lista pustych magazynow
        System.out.print("\n\t Lista pustych magazynów:");
        List<FulfillmentCenter> emptyFC = fulfillmentCenterContainer.findEmpty();
        System.out.println("\n Puste magazyny: ");
        for(FulfillmentCenter fulfillmentCenter : emptyFC) {
            System.out.println(fulfillmentCenter.nameOfWarehouse);
        }

        // wypisanie nazwy magazynu i jego procentowe zapelnienie
        System.out.println("\t Nazwa magazynu i jego procentowe zapełnienie:");
        fulfillmentCenterContainer.summary();

        */
    }
}