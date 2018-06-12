//B. klasa Item (przedmiot w magazynie)
public class Item implements Comparable<Item>
{
    String name;
    ItemCondition itemCondition;
    double mass;                //masa
    int quantity = 0;           //ilość elementów w magazynie ustawiona na 0

    public Item(String name)
    {
        this.name = name;
    }

    //I. konstruktor
    public Item (String name, ItemCondition itemCondition, double mass, int quantity)
    {
        this.name = name;
        this.itemCondition = itemCondition;
        this.mass = mass;
        this.quantity = quantity;
    }

    //II. metoda wypisująca pełne informacje o towarze
    public void print()
    {
        System.out.println("\tNazwa: " + name + "\n\tStan: " + itemCondition + "\n\tMasa: " + mass + "\n\tLiczba: " + quantity + "\n");
    }

    public void printName()
    {
        System.out.println(name);
    }

    //III. metoda na rzecz interfejsu Comparable(interfejs porównujący ze względu na nazwę)
    //przyjmuje dwa elementy i wg schematu traktuje je jako równe, musi zwracać liczbe całkowitą
    public int compareTo(Item o)
    {
        int result = name.compareTo(o.name);
        return result;                  //jeśli 0 - to takie same
    }
}