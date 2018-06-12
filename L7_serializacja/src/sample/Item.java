package sample;
import java.io.Serializable;
import java.util.Date;

// klasa dla utworzenia priorytetu (gettery/settery)
public class Item implements Serializable
{
    String name;                //nazwa
    Date date;                  //data utworzenia
    Priority priority;          //priorytet z trybu wyliczeniowego
    String description;         //opis

    public Item(String name, Date date, Priority priority, String description)
    {
        if(name == "")
        {
            this.name = "Brak nazwy";
        }

        this.name = name;
        this.date = date;
        this.priority = priority;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }


    public Priority getPriority()
    {
        return priority;
    }

    public void setPrio(Priority prio)
    {
        this.priority = priority;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String des)
    {
        this.description = description;
    }


    @Override
    public String toString()
    {
        return this.name;
    }
}
