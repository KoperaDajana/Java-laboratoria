package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;

import javafx.scene.control.Alert.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.layout.HBox;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Calendar;
import java.io.Serializable;


public class Main extends Application implements Serializable
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("LAB_7: Serializable");
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 600, 335);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        //(3.a)-----------------------------------------------------------------------------------------------------MENU
        MenuBar menuBar = new MenuBar();            //dodanie do paska zadan
        Menu menuFile = new Menu("File");      //zamykanie aplikacji
        Menu menuAbout = new Menu("About");    //wyświetlanie informacji o autorze

        menuBar.getMenus().addAll(menuFile, menuAbout);

        //tworzeni opcji close dla "File" -> zamykanie aplikacji
        MenuItem close = new MenuItem("Close");
        close.setOnAction((ActionEvent t) ->
        {
            System.exit(0);
        });


        MenuItem menuSaveFile = new MenuItem("Save file (serializable)");
        MenuItem menuReadFile = new MenuItem("Read file (serializable)");

        menuFile.getItems().addAll(menuSaveFile, menuReadFile, close);          //dodanie close do "File"


        MenuItem infoAbout = new MenuItem("Informations about Author");
        infoAbout.setOnAction((ActionEvent t) ->
        {
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Informations about author: ");
            infoAlert.setHeaderText("Name: Dajana\nSurname: Kopera\n");
            infoAlert.setContentText("Inżynieria Obliczeniowa, gr. I");
            infoAlert.showAndWait();
        });

        menuAbout.getItems().addAll(infoAbout);     //dodanie do "About"

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);



        //dodanie trzech list i ustawienie ich nazw itp-----------------------------------------------------------------
        ListView<Item> listToDo = new ListView<>();                                //do zrobienia
        ObservableList<Item> itemsToDo = FXCollections.observableArrayList ();

        ListView<Item> listIn = new ListView<>();                                  //w trakcie
        ObservableList<Item> itemsIn = FXCollections.observableArrayList ();

        ListView<Item> listDone = new ListView<>();                                //zrobione
        ObservableList<Item> itemsDone = FXCollections.observableArrayList ();

        listToDo.setItems(itemsToDo);
        listIn.setItems(itemsIn);
        listDone.setItems(itemsDone);

        //(3.d) kolorowanie priorytetów---------------------------------------------------------------------------------
        //(3.e) tooltip z opisem zadania oraz (3.f) po najechaniu myszą edycja i elo------------------------------------
        cellOpions(listToDo);
        cellOpions(listIn);
        cellOpions(listDone);

        //dodanie po trzy zadania do pól
        itemsToDo.add(new Item("Niski", new Date("10/01/00"), Priority.LOW, "Co"));
        itemsToDo.add(new Item("TestWysokiP", new Date("27/04/18"), Priority.HIGH, "Test high priority"));
        itemsToDo.add(new Item("Mycie naczyń", new Date("07/05/2018"), Priority.HIGH, "Obowiazek"));

        itemsIn.add(new Item("Sredni", new Date("10/05/10"), Priority.MEDIUM, "Zdanie srednio wazne"));
        itemsIn.add(new Item("TestNiskiP", new Date("31/03/05"), Priority.LOW, "Test low priority"));
        itemsIn.add(new Item("Ocena", new Date("01/01/14"), Priority.MEDIUM, "Wystawienie oceny sprzedającemu"));

        itemsDone.add(new Item("Wysoki", new Date("12/12/12"), Priority.HIGH, "Brak opisu"));
        itemsDone.add(new Item("TestM", new Date("05/04/18"), Priority.MEDIUM, "Test medium priority"));
        itemsDone.add(new Item("Rachunek", new Date("08/03/17"), Priority.LOW, "Opłacony rachunek"));

        //ustawienie wielkosci list
        listToDo.setPrefWidth(200);
        listToDo.setPrefHeight(250);

        listIn.setPrefWidth(200);
        listIn.setPrefHeight(250);

        listDone.setPrefWidth(200);
        listDone.setPrefHeight(250);

        //ustawienie nazw, czcionki oraz miejsc podpisów dla list zadań
        Label label1 = new Label("DO ZROBIENIA");
        label1.setFont(new Font("Arial",22));
        label1.setTranslateX(20);

        Label label2 = new Label("W TRAKCIE");
        label2.setFont(new Font("Arial",22));
        label2.setTranslateX(80);

        Label label3 = new Label("ZROBIONE");
        label3.setFont(new Font("Arial",22));
        label3.setTranslateX(170);
       // label3.setTextFill(Color.web("#0043a2"));



        //(3.b) dodawanie nowego zadania "Add new task"-----------------------------------------------------------------
        Button addNewTask = new Button("Dodaj zadanie");
        addNewTask.setTranslateX(2);            //ustawienie współrzędnych buttona
        addNewTask.setTranslateY(5);
        addNewTask.setStyle("-fx-border-color: blue");
        addNewTask.setStyle("-fx-background-color: lightskyblue");

        addNewTask.setOnAction((ActionEvent e) ->
        {
            Stage addTask = new Stage();
            addTask.setTitle("Dodawanie nowego zadania");
            VBox vBoxNewTask = new VBox();
            Scene sceneBut = new Scene(vBoxNewTask, 400, 250);          //wielkość nowego okna
            addTask.setResizable(false);

            //wnętrze nowo utworzonego okna "Dodaj zadanie"
            HBox hBox1 = new HBox();                    //ustawienie nazwy nowego zadania
            TextField textField = new TextField();
            textField.setPrefWidth(200);
            hBox1.getChildren().addAll(new Label(" Nazwa: "), textField);
            hBox1.setSpacing(65);

            HBox hBox2 = new HBox();                   //ustawienie daty ważności nowego zadania
            DatePicker datePicker = new DatePicker();
            datePicker.setValue(LocalDate.now());
            hBox2.getChildren().addAll(new Label(" Data ważności: "), datePicker);
            hBox2.setSpacing(25);

            HBox hBox3 = new HBox();                //ustawienie priorytetu
            ComboBox<Priority> comboBoxPriority = new ComboBox<>();
            comboBoxPriority.getItems().addAll(Priority.LOW, Priority.MEDIUM, Priority.HIGH);
            comboBoxPriority.setValue(Priority.LOW);
            hBox3.getChildren().addAll(new Label(" Priorytet: "), comboBoxPriority);
            hBox3.setSpacing(55);

            HBox hBox4 = new HBox();                //ustawienie opisu nowego zadania
            TextArea textAreaDescription = new TextArea();
            textAreaDescription.setPrefWidth(400);
            textAreaDescription.setPrefHeight(100);
            textAreaDescription.setWrapText(true);
            textAreaDescription.setText("Dodaj opis zadania...");
            hBox4.getChildren().addAll(textAreaDescription);

            Button addButton = new Button("Dodaj zadanie");
            addButton.setTranslateX(300);
            addButton.setTranslateY(-5);

            //dodanie przycisku kalendarza koło priorytetu i dodanie do listy do zrobienia
            addButton.setOnAction((ActionEvent e1) ->
            {
                LocalDate localDate = datePicker.getValue();
                Calendar calendar = Calendar.getInstance();
                calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
                Date date = calendar.getTime();

                //(3.c)-------------------------------------------------------------------------------------------------
                itemsToDo.add(new Item(textField.getText(), date,
                        comboBoxPriority.getValue(), textAreaDescription.getText() ));
                addTask.close();
            });

            vBoxNewTask.getChildren().addAll(new HBox(), hBox1, hBox2, hBox3, hBox4, addButton);
            vBoxNewTask.setSpacing(10);
            addTask.setScene(sceneBut);
            addTask.show();
        });



        //utworzenie oraz ustawienie przycisków przenoszących zadania---------------------------------------------------
        Button nextToDo = new Button("->");
        nextToDo.setTranslateX(70);
        nextToDo.setTranslateY(5);
        nextToDo.setStyle("-fx-border-color: green");
        nextToDo.setStyle("-fx-background-color: greenyellow");

        Button backToDo = new Button("<-");
        backToDo.setTranslateX(82);
        backToDo.setTranslateY(5);
        backToDo.setStyle("-fx-border-color: darkred");
        backToDo.setStyle("-fx-background-color: orangered");

        Button nextDone = new Button("->");
        nextDone.setTranslateX(215);
        nextDone.setTranslateY(5);
        nextDone.setStyle("-fx-border-color: green");
        nextDone.setStyle("-fx-background-color: greenyellow");

        Button backIn = new Button("<-");
        backIn.setTranslateX(225);
        backIn.setTranslateY(5);
        backIn.setStyle("-fx-border-color: darkred");
        backIn.setStyle("-fx-background-color: orangered");


        //przesuwanie z do zrobienia, na zrobione
        nextToDo.setOnAction((ActionEvent e) ->
        {
            moveItem(listToDo, listIn);
        });

        //przesuwanie z zrobione, na do zrobienia
        backToDo.setOnAction((ActionEvent e) ->
        {
            moveItem(listIn, listToDo);
        });

        //przesuwanie z do zrobienia na zrobione
        nextDone.setOnAction((ActionEvent e) ->
        {
            moveItem(listIn, listDone);
        });

        //przesuwanie na z zrobionego na w trakcie robienia
        backIn.setOnAction((ActionEvent e) ->
        {
            moveItem(listDone, listIn);
        });


        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();

        //dodanie separatorów pomiędzy listami zadań
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.HORIZONTAL);
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.HORIZONTAL);

        //dodanie w odpowiednie miejsca podpisów, list oraz przycisków do głównego okna
        box1.getChildren().addAll(label1, label2, label3);
        box2.getChildren().addAll(listToDo, separator1, listIn, separator2, listDone);
        box3.getChildren().addAll(addNewTask, nextToDo, backToDo, nextDone, backIn);

        //dodanie wszystkich elementów do głownego okna
        vBox.getChildren().addAll(box2, box1, box3);

    }


    //metoda, która umożliwia przenoszenie jednego elementu listy do drugiej
    void moveItem(ListView list1, ListView list2)
    {
        if(!list1.getItems().isEmpty() && !list1.getSelectionModel().isEmpty())     //jeśli obie listy nie są puste
        {
            list2.getItems().addAll(list1.getSelectionModel().getSelectedItem());
            list1.getItems().remove(list1.getSelectionModel().getSelectedItem());
        }
    }


    // metoda kolorująca wybrane elementy listy w zależności od priorytetu
    // tooltip -> wyświetlanie opisu po najeździe myszką
    // contextMenu -> po kliknięciu można usunąć lub edytować dane zadanie
    void cellOpions(ListView<Item> list)
    {
        list.setCellFactory(column ->
        {
            return new ListCell<Item>()
            {
                private Tooltip tooltip = new Tooltip();            //tooltip - zawiera opis wybranego tasku

                public void updateItem(Item item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (item == null)
                    {
                        setTooltip(null);
                        setText(null);
                        setStyle(null);
                    }
                    else
                    {
                        //---------------------------------------------------------------------------------------TOOLTIP
                        tooltip.setText(
                                "Data utworzenia: "+item.getDate()+
                                "\nPriorytet: "+item.getPriority()+
                                "\nOpis:\n"+item.getDescription()
                        );
                        setTooltip(tooltip);


                        //----------------------------------------------------------------------------------CONTEXT-MENU
                        ListCell<Item> cell = new ListCell<>();
                        ContextMenu contextMenu = new ContextMenu();

                        MenuItem editItem = new MenuItem("Edytuj");//----------------------------------------edit--
                        editItem.setOnAction(event ->
                        {
                            Stage addTask = new Stage();
                            addTask.setTitle("Edytuj zadanie");
                            VBox vBox = new VBox();
                            Scene sceneBut = new Scene(vBox, 400, 250);
                            addTask.setResizable(false);

                            HBox hBox1 = new HBox();                    //ustawienie nazwy nowego zadania
                            TextField textField = new TextField();
                            textField.setPrefWidth(200);
                            textField.setText(item.getName());
                            hBox1.getChildren().addAll(new Label(" Nazwa: "), textField);
                            hBox1.setSpacing(65);

                            HBox hBox2 = new HBox();                   //ustawienie daty ważności nowego zadania
                            DatePicker datePicker = new DatePicker();
                            datePicker.setValue(LocalDate.of(item.getDate().getYear()+1900,
                                    item.getDate().getMonth()+1,item.getDate().getDate()));
                            hBox2.getChildren().addAll(new Label(" Data ważności: "), datePicker);
                            hBox2.setSpacing(25);

                            HBox hBox3 = new HBox();                //ustawienie priorytetu
                            ComboBox<Priority> comboBoxPriority = new ComboBox<>();
                            comboBoxPriority.getItems().addAll(Priority.LOW, Priority.MEDIUM, Priority.HIGH);
                            comboBoxPriority.setValue(item.getPriority());
                            hBox3.getChildren().addAll(new Label(" Priorytet: "), comboBoxPriority);
                            hBox3.setSpacing(55);

                            HBox hBox4 = new HBox();                //ustawienie opisu nowego zadania
                            TextArea textAreaDescription = new TextArea();
                            textAreaDescription.setPrefWidth(400);
                            textAreaDescription.setPrefHeight(100);
                            textAreaDescription.setWrapText(true);
                            textAreaDescription.setText(item.getDescription());
                            hBox4.getChildren().addAll(textAreaDescription);

                            Button addBut = new Button("Zapisz zmiany");
                            addBut.setTranslateX(300);
                            addBut.setTranslateY(-5);

                            addBut.setOnAction((ActionEvent e1) ->
                            {
                                item.setName(textField.getText());
                                item.setPrio(comboBoxPriority.getValue());
                                item.setDate(new Date(datePicker.getValue().toEpochDay()));
                                item.setDescription(textAreaDescription.getText());
                                addTask.close();
                            });

                            vBox.getChildren().addAll(new HBox(), hBox1, hBox2, hBox3, hBox4, addBut);
                            vBox.setSpacing(10);
                            addTask.setScene(sceneBut);
                            addTask.show();
                        });


                        MenuItem deleteItem = new MenuItem("Usuń");//--------------------------------------delete--
                        deleteItem.setOnAction(event -> list.getItems().remove(item));
                        contextMenu.getItems().addAll(editItem, deleteItem);
                        setContextMenu(contextMenu);


                        //------------------------------------------------------------------------KOLOROWANIE-PRIORYTETU
                        if (item.getPriority() == Priority.LOW)
                        {
                            setStyle("-fx-text-fill: green");
                        }
                        else
                        {
                            if(item.getPriority() == Priority.HIGH)
                            {
                                setStyle("-fx-text-fill: red");
                            }
                            else
                            {
                                setStyle("-fx-text-fill: orange");
                            }
                        }
                        setText(item.getName());
                    }
                }
            };
        });
    }
}
