import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.lang.Math;

public class Calculator extends JFrame {
	
	//BACK END
	public String doTheMath(String input){//later add something that check syntax
		try{
			return String.valueOf(parseBrackets(input.replace('-', 'N')));
			/*
			it is to not confuse negative numbers resulting after calculations and the substraction operation in input
			without it something like 1+(2-3) will result in 1+-1 which have 2 operations next to each other wich will cause error
			and all of this shit is because i'm lazy to teach my calculator the difference between substraction operation and negative numbers
			*/
		}
		catch(Exception e){
			return "Syntax error";
		}
	}
	public double parseBrackets(String input){
		double i=0;
		if(input.contains("(")){
			String[] strarray = input.split("\\(");
			ArrayList<String> strList = new ArrayList<String>();
			for(String elm: strarray)
				strList.add(elm);
			for(int j=0;j<strList.size();j++){
				if(strList.get(j).contains(")")){
					String[] strarray2 = strList.get(j).split("\\)",2);
					strarray2[0]=String.valueOf(parseByAddition(strarray2[0]));
					if(j>0){
						strList.set(j-1,(strList.get(j-1)+strarray2[0]+strarray2[1]));
						strList.remove(j);
					}else
						strList.set(0,(strarray2[0]+strarray2[1]));
					j=-1;
				}
			}
			input="";
			for(int j=0;j<strList.size();j++)
				input+=strList.get(j);
			i=parseByAddition(input);
		}
		else
			i=parseByAddition(input);
		return i;
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
		if(input.contains("N")){
			String[] strarray = input.split("N");
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
			i=parsePower(strarray[0]);
			strarray[0]="1";
			for(String elm: strarray)
				i/=parsePower(elm);
		}
		else
			i=parsePower(input);
		return i;
	}
	public double parsePower(String input){
		double i;
		if(input.contains("^")){
			String[] strarray = input.split("\\^");
			i=Double.parseDouble(strarray[0]);
			strarray[0]="1";
			for(String elm: strarray)
				i=Math.pow(i, Double.parseDouble(elm));
		}
		else
			i=Double.parseDouble(input);
		return i;
	}

	// FRONT END

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(KeyEvent.getKeyText(e.getKeyCode()).equals("Backspace")){
				if(tf.getText().length()>0)
                	tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
			}
			else if(KeyEvent.getKeyText(e.getKeyCode()).equals("Enter"))
				tf.setText(doTheMath(tf.getText()));
			else
				tf.setText(tf.getText()+e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

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
        tf.setFont(new Font("Tahoma", Font.PLAIN, 30));
        p_affichage.add(tf);
        p_affichage.setPreferredSize(new Dimension(400, 70));

		addKeyListener(new MyKeyListener());
		setFocusable(true);

        delete=new JButton("Del");
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if(tf.getText().length()>0)
                	tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
        }});
        delete.setFont(new Font("Tahoma", Font.PLAIN, 30));
        delete.addKeyListener(new MyKeyListener());
		delete.setFocusable(true);

        equal=new JButton("=");
        equal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	tf.setText(doTheMath(tf.getText()));
        }});
        equal.setFont(new Font("Tahoma", Font.PLAIN, 30));
        equal.addKeyListener(new MyKeyListener());
		equal.setFocusable(true);

        p_buttons.add(new CalculatorButton("(",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton(")",tf,new MyKeyListener()));//prevent from adding one if there's no ( ?
        p_buttons.add(new CalculatorButton("^",tf,new MyKeyListener()));
        p_buttons.add(delete);
        p_buttons.add(new CalculatorButton("7",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("8",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("9",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("+",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("4",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("5",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("6",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("-",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("1",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("2",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("3",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("*",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton("0",tf,new MyKeyListener()));
        p_buttons.add(new CalculatorButton(".",tf,new MyKeyListener()));
        p_buttons.add(equal);
        p_buttons.add(new CalculatorButton("/",tf,new MyKeyListener()));
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
	public CalculatorButton(String s,JLabel tf, KeyListener listener){
		this.setText(s);
		setFont(new Font("Tahoma", Font.PLAIN, 30));
		addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf.setText(tf.getText()+s);
        }});
        addKeyListener(listener);
        setFocusable(true);
	}
}
