package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class toSerialize implements Serializable {
    // cast1-3 przechowują osobno rzutowane obserwablelists na arraylistę do tuDu, in, done
    public ArrayList<Item> castToDo;
    public ArrayList<Item> castIn;
    public ArrayList<Item> castDone;
}
