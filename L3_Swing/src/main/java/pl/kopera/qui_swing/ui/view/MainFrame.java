package pl.kopera.qui_swing.ui.view;

import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import javax.swing.DefaultListModel;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;


// klasa implementująca aplikacje
public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private JButton evalButton;
    private JList functionList;
    private JTextArea historyTextArea;
    private JTextField formulaInput;
    private JScrollPane scrollContainerPane;
    private JMenu options;
    private JMenuBar optionsMenuBar;
    private JMenuItem reset, exit;
    private String lastResult = "";
    private String lastInput = "";


    public MainFrame() {
        //stworzenie rozwijalnego menu
        optionsMenuBar.add(options);

        options.add(reset);
        options.add(exit);



        // (4.a) czyści zawartość formulaInput: JTextField i historyTextArea: JTextArea----------------------------RESET
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formulaInput.setText("");
                historyTextArea.setText("");
            }
        });



        // (4.b) zamyka aplikacje-----------------------------------------------------------------------------------EXIT
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
            }
        });



        // konfiguracja okna aplikacji
        JFrame frame = new JFrame("L3: CALCULATOR(swing)");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setLocation(100,100);
        frame.setVisible(true);



        // (4.c) historyTextArea: JTextArea - pole tekstowe, historia operacji------------------------------------------
        historyTextArea.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);                //super - odpowiednik konstruktora klasy z której dziedziczymy
                lastResult = formulaInput.getText();    //ustawienie ostataniego tekstu z fielda jako lastResult

                String printResult = MessageFormat.format("{0}\n\t = {1} \n- - - - - - - - - - - - - - - - - -\n",
                        lastInput);
                historyTextArea.append(printResult);
                formulaInput.setText(null);
            }
        });



        // (4.d) formulaInput: JTextField wpisywanie formuł matematycznych----------------------------------------------
        formulaInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastInput = formulaInput.getText();     //ustawienie ostataniego tekstu z fielda jako lastResult
                Expression expression = new Expression(lastInput);      //korzystanie z bilbioteki mXparser
                if(expression.checkSyntax())                            //sprawdzanie składni
                {
                    double result = expression.calculate();             //jeśli zgodne ze składnią
                    lastResult = Double.toString(result);

                    String printResult = MessageFormat.format("{0}\n\t = {1} \n- - - - - - - - - - - - - - - - - -\n",
                            lastInput, result);
                    historyTextArea.append(printResult);
                    formulaInput.setText(null);
                }
                else                                                    //wyświetlenie okna z komunikatem błędu
                {
                    String errorMessage = expression.getErrorMessage();
                    JOptionPane.showMessageDialog(null,errorMessage,"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        // (4.d.i) funkcja pozwalająca wyświetlić ostatni wpis po naciśnięciu strzałki w górę---------------------------
        checkUpKey();



        // (4.e) zawiera listę podstawowych funkcji matematycznych------------------------------------------------------
        // 3 x funkcje jednoargumentowe
        // 3 x funkcje dwuargumentowe
        // 3 x wybrane stałe
        functionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // (4.e.iii) jeśli kliknięcie myszy jest parzyste (==2) i większe od 0
                if (e.getClickCount() % 2 == 0 && e.getClickCount() > 0) {
                    Operations choice = (Operations) functionList.getSelectedValue();
                    switch (choice.getMathOperation()) {
                        case "- SINUS -":
                            formulaInput.setText(formulaInput.getText() + "sin()"); //ustawiam tekst w fieldzie
                            formulaInput.requestFocus();                            //rządam fokusa
                            formulaInput.setCaretPosition(formulaInput.getText().length() - 1); //ustawiam kursor
                            break;

                        case "- COSINUS -":
                            formulaInput.setText(formulaInput.getText() + "cos()");
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length() - 1);
                            break;

                        case "- NATURAL LOGARITHM -":
                            formulaInput.setText(formulaInput.getText() + "ln()");
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length() - 1);
                            break;

                        //dwuargumentowe
                        case "- LOGARITHM -":
                            formulaInput.setText(formulaInput.getText() + "log(,)");
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length() - 2); //-2 bo przed ','
                            break;

                        case "- MODULO -":
                            formulaInput.setText(formulaInput.getText() + "mod(,)");
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length() - 2);
                            break;

                        case "- EULER NUMBER -":
                            formulaInput.setText(formulaInput.getText() + "Euler(,)");  //nazwa musi być z dużej litery
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length() - 2);
                            break;

                        //stałe
                        case "- PI -":
                            double test1 = choice.evaluateString("pi");
                            String printResult1 = MessageFormat.format("{0}\n\t = {1} \n- - - - - - - - - - - - - - " +
                                            "- - - -\n",
                                    choice.getMathOperation(), test1);
                            historyTextArea.append(printResult1);
                            formulaInput.setText(String.valueOf(test1));
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length());
                            break;

                        case "- E -":
                            double test2 = choice.evaluateString("e");
                            String printResult2 = MessageFormat.format("{0}\n\t = {1} \n- - - - - - - - - - - - - - " +
                                            "- - - -\n",
                                    choice.getMathOperation(), test2);
                            historyTextArea.append(printResult2);
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length());
                            break;

                        case "- OMEGA CONST -":
                            double test3 = choice.evaluateString("[Om]");
                            String printResult3 = MessageFormat.format("{0}\n\t = {1} \n- - - - - - - - - - - - - - " +
                                            "- - - -\n",
                                    choice.getMathOperation(), test3);
                            historyTextArea.append(printResult3);
                            formulaInput.setText(formulaInput.getText());
                            formulaInput.requestFocus();
                            formulaInput.setCaretPosition(formulaInput.getText().length());
                            break;

                        case "- LAST RESULT -":
                            formulaInput.setText(formulaInput.getText()+lastResult);
                            formulaInput.requestFocus();

                    }
                }
            }
        });


        // (4.f) evaluate - analogiczne do enter w (4.d)----------------------------------------------------------------
        evalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastInput = formulaInput.getText();             //ustawienie ostatniego wejścia do fielda na lastInput
                Expression expression = new Expression(lastInput);
                if(expression.checkSyntax())                    //sprawdzenie skłądni
                {
                    double result = expression.calculate();     //jeśli składnia jest poprawna
                    lastResult = Double.toString(result);       //konwertuje result na stringa i przypisuje do lResult

                    String printResult = MessageFormat.format("{0}\n\t = {1} \n- - - - - - - - - - - - - - - - - -\n",
                            lastInput, result);
                    historyTextArea.append(printResult);
                    formulaInput.setText(null);
                }
                else                                            //wyświetlenie okna z komunikatem błędu
                {
                    String errorMessage = expression.getErrorMessage();
                    JOptionPane.showMessageDialog(null,errorMessage,"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    //-----------------------------------------------------------------------------------------------KONIEC-KONSTRUKTORA



    // metoda na potrzeby (4.d.i), która po wciśnięciu przez użytkownika strzałki w górę wypisuje ostatni wpis do fielda
    public void checkUpKey()
    {                                           //focus - jako stan komponenetu który nas interesuje
        formulaInput.setFocusable(true);        //czy można, ustawione na prawdę
        formulaInput.requestFocus();            //wysłanie rządania
        formulaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode())          //sprawdzenie kodu wciskanego przycisku z klawiatury
                {
                    case KeyEvent.VK_UP:        //jeśli to strzałka w górę to wyświetl w fieldzie ostatnie wejście
                        formulaInput.setText(lastInput);
                        break;
                }
            }
        });
    }



    //(4.e.i/ii) zbudowanie listy functionList: JList-------------------------------------------------------------------
    private void createUIComponents() {
        // TODO: place custom component creation code here
        //jednoargumentowe
        Operations sinus = new Operations("- SINUS -");             //sin(x)
        Operations cosinus = new Operations("- COSINUS -");         //cos(x)
        Operations ln = new Operations("- NATURAL LOGARITHM -");    //ln(x)

        //dwuargumentowe
        Operations log = new Operations("- LOGARITHM -");           //log(a,b)
        Operations mod = new Operations("- MODULO -");              //mod(a,b)
        Operations euler = new Operations("- EULER NUMBER -");      //Euler(n,k)

        //stałe
        Operations pi = new Operations("- PI -");
        Operations e = new Operations("- E -");
        Operations omega = new Operations("- OMEGA CONST -");

        //last result
        Operations lastResult = new Operations("- LAST RESULT -");


        // Custom Create musi być zaznaczone w MainFrame.form bo inaczej ich nie widzi
        // (4.e.i) zbudowanie listy przy użyciu DefaultListModel
        DefaultListModel<Operations> listModel = new DefaultListModel<Operations>();

        listModel.addElement(sinus);
        listModel.addElement(cosinus);
        listModel.addElement(ln);

        listModel.addElement(log);
        listModel.addElement(mod);
        listModel.addElement(euler);

        listModel.addElement(pi);
        listModel.addElement(e);
        listModel.addElement(omega);

        listModel.addElement(lastResult);

        functionList = new JList<>(listModel);
        functionList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        }


    //--------------------------------------------------------------------------------------------------------------main
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}