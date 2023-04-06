import javax.net.ssl.SSLContext;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class calculator extends JFrame {
    private JPanel calculator;
    private JPanel valuePanel;
    private JPanel buttonPanel;
    private JTextField expression;
    private JTextField input;
    String fullExpression="";
    boolean plusB = true;
    boolean minusB = true;
    boolean divideB = true;
    boolean multiplyB = true;
    boolean equal =false;
    String equation="";
    int number=0;

    public calculator() {
        setLayout(new BorderLayout());
        //setContentPane(calculator);
        int endResult=0;
        setTitle("My calculator");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buildValuePanel();
        buildButtonPanel();
        add(valuePanel, BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);
        setVisible(true);

    }

    private void buildValuePanel() {
        valuePanel = new JPanel();
        int row=2;
        int col=1;
        valuePanel.setLayout(new GridLayout(row, col));
        expression = new JTextField("",15);
        input = new JTextField("",15);
        input.addKeyListener(new NumberKeysListener());
        valuePanel.add(expression);
        valuePanel.add(input);

    }

    private void buildButtonPanel() {
        buttonPanel = new JPanel();
        int row=5;
        int col=4;
        JButton[][] btns = new JButton[row][col];
        buttonPanel.setLayout(new GridLayout(row, col));
        for (int i = 0; i < row;i++) {
            for (int j = 0; j < col; j++) {
                btns[i][j] = new JButton("");
                btns[4][0] = new JButton(" ");
                //backspace
                btns[0][0] = new JButton("<--");

                //clear whole equation
                btns[0][1] = new JButton("CL");
                btns[0][1].addActionListener(new clearListener());
                //clear number
                btns[0][2] = new JButton("C");
                btns[0][2].addActionListener(new clearListener());

                //sign
                btns[4][3] = new JButton("+");
                btns[4][3].addActionListener(new signListener());

                btns[2][3] = new JButton("*");
                btns[2][3].addActionListener(new signListener());

                btns[1][3] = new JButton("/");
                btns[1][3].addActionListener(new signListener());

                btns[3][3] = new JButton("-");
                btns[3][3].addActionListener(new signListener());

                btns[0][3] = new JButton("=");
                btns[0][3].addActionListener(new signListener());

                //numbers
                btns[1][2] = new JButton("9");
                btns[1][2].addActionListener(new numberListener());

                btns[1][0] = new JButton("7");
                btns[1][0].addActionListener(new numberListener());

                btns[1][1] = new JButton("8");
                btns[1][1].addActionListener(new numberListener());

                btns[2][2] = new JButton("6");
                btns[2][2].addActionListener(new numberListener());

                btns[2][1] = new JButton("5");
                btns[2][1].addActionListener(new numberListener());

                btns[2][0] = new JButton("4");
                btns[2][0].addActionListener(new numberListener());

                btns[3][2] = new JButton("3");
                btns[3][2].addActionListener(new numberListener());

                btns[3][1] = new JButton("2");
                btns[3][1].addActionListener(new numberListener());

                btns[3][0] = new JButton("1");
                btns[3][0].addActionListener(new numberListener());

                btns[4][1] = new JButton("0");
                btns[4][1].addActionListener(new numberListener());

                btns[4][2] = new JButton(".");
                btns[4][2].addActionListener(new numberListener());


                buttonPanel.add(btns[i][j]);

            }

        }
    }
    private class signListener implements ActionListener{
        public void actionPerformed (ActionEvent e)
        {
           String buttonClicked =  e.getActionCommand();
            //System.out.println(buttonClicked);

            String plus = "+";
            String minus = "-";
            String divide = "/";
            String multiply = "*";



            if (buttonClicked.equals("+")){
                //add to overall equation
                //compute endResult
                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                number += i;

                equation = equation.concat(plus);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = true;
                minusB = false;
                divideB = false;
                multiplyB = false;
                equal= false;
            }
            else if (buttonClicked.equals("-"))
            {

                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                if (number==0)
                {
                    number =i;
                }
                else {
                    number -= i;
                }


                equation = equation.concat(minus);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = false;
                minusB = true;
                divideB = false;
                multiplyB = false;
                equal= false;
            }
            else if (buttonClicked.equals("*"))
            {

                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                if (number==0)
                {
                    number++;
                }
                number = number * i;
                System.out.println(number);
                equation = equation.concat(multiply);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = false;
                minusB = false;
                divideB = false;
                multiplyB = true;
                equal= false;
            }
            else if (buttonClicked.equals("/"))
            {

                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                System.out.println(number);
                if(number==0)
                {
                    number++;
                    number = i/number;
                    System.out.println(number);
                }
                else
                {
                    number=number/i;
                    System.out.println(number);
                }


                equation = equation.concat(divide);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = false;
                minusB = false;
                divideB = true;
                multiplyB = false;
                equal= false;
            }
            else if (buttonClicked.equals("="))
            {
                equal=true;
                int i = Integer.parseInt(input.getText());
                if(plusB){
                    number+=i;
                }
                else if(minusB){
                    number-=i;
                }
                else if(divideB){
                    System.out.println(number);
                    number=number/i;
                    System.out.println(number);
                }
                else if(multiplyB){
                    number*=i;
                }
                equation=String.valueOf(number);
                expression.setText(equation);
                input.setText("Welcome");
                fullExpression="";
                number=0;


            }
        }
    }
    private class clearListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String buttonClicked =  e.getActionCommand();
            if (buttonClicked.equals("C")){
                //clear whole board
                expression.setText("");
                input.setText("");
                fullExpression="";
                equation="";
            }
            else if (buttonClicked.equals("CL")) {
                input.setText("");
                fullExpression="";
            }
        }
    }
    private class numberListener implements ActionListener{
        public void actionPerformed (ActionEvent e) {
            if(equal)
            {
                expression.setText("");
                equation="";
            }
            String buttonClicked =  e.getActionCommand();
            fullExpression=fullExpression.concat(buttonClicked);
            input.setText(fullExpression);
        }
        }
    private class NumberKeysListener implements KeyListener{
        String plus = "+";
        String minus = "-";
        String divide = "/";
        String multiply = "*";
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if(code==KeyEvent.VK_DELETE){
                input.setText("");
                fullExpression="";
            }

        }


        public void keyReleased(KeyEvent e) {

        }


        public void keyTyped(KeyEvent e) {
            char s = e.getKeyChar();
            int keycode= e.getKeyCode();
            String key = String.valueOf(s);
            //System.out.println(keycode);
            if(s=='+')
            {
                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                number += i;

                equation = equation.concat(plus);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = true;
                minusB = false;
                divideB = false;
                multiplyB = false;
                equal= false;
            }
            if(s=='-')
            {
                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                if (number==0)
                {
                    number =i;
                }
                else {
                    number -= i;
                }

                equation = equation.concat(minus);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = false;
                minusB = true;
                divideB = false;
                multiplyB = false;
                equal= false;
            }
            if(s=='/')
            {
                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                System.out.println(number);
                if(number==0)
                {
                    number++;
                    number = i/number;
                    System.out.println(number);
                }
                else
                {
                    number=number/i;
                    System.out.println(number);
                }


                equation = equation.concat(divide);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = false;
                minusB = false;
                divideB = true;
                multiplyB = false;
                equal= false;
            }
            if(s=='*')
            {
                equation = equation.concat(input.getText());

                int i = Integer.parseInt(input.getText());
                if (number==0)
                {
                    number++;
                }
                number = number * i;
                System.out.println(number);
                equation = equation.concat(multiply);
                expression.setText(equation);

                input.setText("");
                fullExpression="";
                plusB = false;
                minusB = false;
                divideB = false;
                multiplyB = true;
                equal= false;
            }
            if(s=='=')
            {
                equal=true;
                int i = Integer.parseInt(input.getText());
                if(plusB){
                    number+=i;
                }
                else if(minusB){
                    number-=i;
                }
                else if(divideB){
                    System.out.println(number);
                    number=number/i;
                    System.out.println(number);
                }
                else if(multiplyB){
                    number*=i;
                }
                equation=String.valueOf(number);
                expression.setText(equation);
                input.setText("");
                fullExpression="";
                number=0;
            }
            //if (keycode==U+007F);
            //input.setText("");
        }
    }
    public static void main(String[] args) {
        new calculator();
    }
}