package sample;

import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;


// dla asynchronicznych zadań
public class AsyncTask extends Task
{
    private int pointsNumber;
    private GraphicsContext gc;
    private PointListener pointListener;

    public AsyncTask(int pointsNumber, GraphicsContext graphicsContext)
    {
        this.pointsNumber = pointsNumber;
        this.gc = graphicsContext;
    }

    // metoda call, uruchamiana w sposób nieblokujący
    @Override
    protected Object call()
    {
        int n = pointsNumber;
        int k = 0;                      // ile razy trafia

        for (int i = 0; i < n; ++i)
        {
            // losowa liczba rzeczywista z zakresu <min, max>
            Random random = new Random();
            // double x = min + (max - min) * random.nextDouble();
            double x = -8 + (8 + 8) * random.nextDouble();
            double y = -8 + (8 + 8) * random.nextDouble();

            if(Equation.calc(x, y))
            {
                pointListener.onPointCalculated(new Point(x, y, true, i));       // i = counter (licznik)
                k++;
            } else {
                pointListener.onPointCalculated(new Point(x, y, false, i));
            }
            if(i % (pointsNumber / 100) == 0)
            {
                // uaktualnia proces, gdzie i to aktualna wartość postępu, a pointsNumber to maksymalny postęp
                updateProgress(i, pointsNumber);
                getI(i);
            }
            if(isCancelled())
                break;
        }

        // powierzchnia przestrzeni rozwiązania p (tu było poszukiwane rozwiązanie)
        return (16.0 * 16.0 * k / n);
    }
    public int getI(int i) {
        return i;
    }

    public void setListener(PointListener pointListener)
    {
        this.pointListener = pointListener;
    }
}


