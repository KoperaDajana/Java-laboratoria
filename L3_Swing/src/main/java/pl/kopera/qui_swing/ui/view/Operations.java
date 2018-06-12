package pl.kopera.qui_swing.ui.view;

import org.mariuszgromada.math.mxparser.Expression;

//gettery, settery, dla operacji matematycznych
public class Operations {
    private String mathOperation;
    private int arg;

    public String getMathOperation()
    {
        return mathOperation;
    }


    public void setMathOperation(String mathOp)
    {
        this.mathOperation = mathOp;
    }


    public int getArg()
    {
        return arg;
    }


    public void setArg(int arg)
    {
        this.arg = arg;
    }


    public Operations(String mathOp)
    {
        this.mathOperation = mathOp;
    }

    @Override
    public String toString()
    {
        return mathOperation;
    }

    //do switcha przy konwertowaniu dla dwuargumentowych funkcji
    public double evaluateString(String givenString)
    {
        Expression expression = new Expression(givenString);
        return expression.calculate();
    }
}
