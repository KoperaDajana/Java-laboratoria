package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.image.BufferedImage;
import static javafx.scene.paint.Color.*;


// klasa implementująca interfejs PoinListener
public class Controller implements PointListener
{
    public Canvas canvas;           // "płótno" rastrowe rysowanie javaFX
    public ProgressBar progressBar; // pasek progresu
    public Label result;            // dla wyniku
    public TextField point;         // wpisywanie ilości punktów
    private AsyncTask taskGraph;    // stworzenie osobnego zadania dla rysowania
    private GraphicsContext gc;
    BufferedImage bi;
    private double hight, width;
    int pointsNumber;


    private void drawShapes(GraphicsContext gc) throws IllegalArgumentException
    {
        try {
            gc.setFill(BLACK);
            gc.fillRect(gc.getCanvas().getLayoutX(),
                    gc.getCanvas().getLayoutY(),
                    gc.getCanvas().getWidth(),
                    gc.getCanvas().getHeight());
            bi = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);

            if (point.getText().equals(""))
            {
                pointsNumber = 100000;
            } else {
                pointsNumber = Integer.parseInt(point.getText());
            }

            if (pointsNumber >= 100 && pointsNumber >= 0)
            {
                progressBar.setVisible(true);

                taskGraph = new AsyncTask(pointsNumber, gc);
                taskGraph.setListener(this);
                // powiązanie wątku z paskiem progresu
                progressBar.progressProperty().bind(taskGraph.progressProperty());

                // utworzenie nowego wątku
                new Thread(taskGraph).start();
                // pobranie danych z utworzonego wątku
                taskGraph.setOnSucceeded(event -> result.setText("Wynik: " + taskGraph.getValue()));
                handleStopBtnAction(ta)
                // )));
            } else if (pointsNumber < 100 && pointsNumber >=0) {
                //gc.clearRect(0, 0, 400, 400);
                gc.setStroke(BLACK);
                gc.setFill(BLACK);
                result.setText("Wynik: - ");
                progressBar.setVisible(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uwaga!");
                alert.setContentText("Wprowadzono ujemną liczbę punktów, spróbuj ponownie.");
                alert.showAndWait();
                point.setText("");
                result.setText("Wynik: ");
                progressBar.setVisible(false);
            }
        } catch(IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wykryto błąd!");
            alert.setContentText("Nieprawidłowe dane wejściowe, spróbuj ponownie.");

            alert.showAndWait();
            point.setText("");
            result.setText("Wynik: ");
            progressBar.setVisible(false);
        }
    }


    @FXML
    private void handleRunBtnAction(){
        gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        width=(int)gc.getCanvas().getWidth();
        hight=(int)gc.getCanvas().getHeight();
    }

    @FXML
    private void handleStopBtnAction()
    {
        taskGraph.cancel();
    }


    // metoda rysująca wykres
    @Override
    public void onPointCalculated(Point event)
    {
        int a = 1;
        int b = (int)hight - 1;

        // granice całek
        int dotX = (int)((b - a) * (event.getX() + 8) / (8 + 8) + a);
        int dotY = (int)((b - a) * (event.getY() + 8) / (8 + 8) + a);

        // odbicie lustrzane dla układu współrzędnych dla Y
        dotY = (int)hight - dotY;

        //
        if(event.isInside())
            bi.setRGB(dotX, dotY, Color.yellow.getRGB());
        else
            bi.setRGB(dotX, dotY, Color.blue.getRGB());
        if(event.getCounter() % (pointsNumber/100) == 0)
            gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
    }
}

