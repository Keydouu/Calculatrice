import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class Calculator extends JFrame {
	
	//BACK END

	public String doTheMath(String input){//later add something that check syntax
		try{// i still have to add the parsing of ( and ) and ^
			return String.valueOf(parseByAddition(input));
		}
		catch(Exception e){
			return "Syntax error";
		}
	}
	public double parseByAddition(String input){
		double i=0;
		if(input.contains("+")){
			String[] strarray = input.split("\\+");
			for(String elm: strarray)
				i+=parseBySubstraction(elm);
		}
		else
			i=parseBySubstraction(input);
		return i;
	}
	public double parseBySubstraction(String input){
		double i;
		if(input.contains("-")){
			String[] strarray = input.split("-");
			i=parseByMultiplication(strarray[0]);
			strarray[0]="0";
			for(String elm: strarray)
				i-=parseByMultiplication(elm);
		}
		else
			i=parseByMultiplication(input);
		return i;
	}
	public double parseByMultiplication(String input){
		double i=1;
		if(input.contains("*")){
			String[] strarray = input.split("\\*");
			for(String elm: strarray)
				i*=parseByDivision(elm);
		}
		else
			i=parseByDivision(input);
		return i;
	}
	public double parseByDivision(String input){
		double i;
		if(input.contains("/")){
			String[] strarray = input.split("/");
			i=Double.parseDouble(strarray[0]);
			strarray[0]="1";
			for(String elm: strarray)
				i/=Double.parseDouble(elm);
		}
		else
			i=Double.parseDouble(input);
		return i;
	}

	// FRONT END

	JPanel p_affichage, p_buttons;
	JLabel tf;
	JButton equal, delete;
	public Calculator(){
		setTitle("Calculatrice");
        p_buttons= new JPanel();
        p_buttons.setLayout(new GridLayout(5, 4, 0, 0));
        p_affichage=new JPanel();
        setSize(500,500);

        tf=new JLabel("");
        tf.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        p_affichage.add(tf);
        p_affichage.setPreferredSize(new Dimension(400, 70));

        delete=new JButton("Del");
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if(tf.getText().length()>0)
                	tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
        }});

        equal=new JButton("=");
        equal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	tf.setText(doTheMath(tf.getText()));
        }});

        p_buttons.add(new CalculatorButton("(",tf));
        p_buttons.add(new CalculatorButton(")",tf));//prevent from adding one if there's no ( ?
        p_buttons.add(new CalculatorButton("^",tf));
        p_buttons.add(delete);
        p_buttons.add(new CalculatorButton("7",tf));
        p_buttons.add(new CalculatorButton("8",tf));
        p_buttons.add(new CalculatorButton("9",tf));
        p_buttons.add(new CalculatorButton("+",tf));
        p_buttons.add(new CalculatorButton("4",tf));
        p_buttons.add(new CalculatorButton("5",tf));
        p_buttons.add(new CalculatorButton("6",tf));
        p_buttons.add(new CalculatorButton("-",tf));
        p_buttons.add(new CalculatorButton("1",tf));
        p_buttons.add(new CalculatorButton("2",tf));
        p_buttons.add(new CalculatorButton("3",tf));
        p_buttons.add(new CalculatorButton("*",tf));
        p_buttons.add(new CalculatorButton("0",tf));
        p_buttons.add(new CalculatorButton(",",tf));
        p_buttons.add(equal);
        p_buttons.add(new CalculatorButton("/",tf));

        setLayout(new BorderLayout());
        add(p_affichage,BorderLayout.PAGE_START);
        add(p_buttons,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	public static void main(String[] args){
        new Calculator();
    }
}
class CalculatorButton extends JButton{
	public CalculatorButton(String s,JLabel tf){
		this.setText(s);
		addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf.setText(tf.getText()+s);
        }});
	}
}
