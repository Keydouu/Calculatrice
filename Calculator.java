package GL;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
public class Calculator extends JFrame {
    JButton b_0, b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8, b_9, b_plus, b_moins, b_multiplication, b_div, b_qual, b_reset;
    JPanel p_affichage, p_buttons;
    double current, total;
    int nlastOperation;
    JTextField tf;
    String str;
    public void calc(){
        switch(nlastOperation){
            case 1:
                total+=current;
                current=0;
            break;
            case 2:
                total+=current;
                current=0;
            break;
            case 3:
                total*=current;
                current=0;
            break;
            case 4:
                total+=current;
                current=0;
            break;
        }
    }
    public Calculator(){
        setTitle("Calculatrice");
        p_buttons= new JPanel();
        p_buttons.setLayout(new GridLayout(4, 4, 0, 20));
        p_affichage=new JPanel();
        current=0;
        total=0;
        nlastOperation=1;
        setSize(500,500);

        tf=new JTextField();
        tf.setEditable(false); 
        p_affichage.add(tf);
        tf.setPreferredSize(new Dimension(400, 100));

        b_0 = new JButton("0");
        b_1 = new JButton("1");
        b_2 = new JButton("2");
        b_3 = new JButton("3");
        b_4 = new JButton("4");
        b_5 = new JButton("5");
        b_6 = new JButton("6");
        b_7 = new JButton("7");
        b_8 = new JButton("8");
        b_9 = new JButton("9");
        b_plus = new JButton("+");
        b_moins = new JButton("-");
        b_multiplication = new JButton("*");
        b_div = new JButton("/");
        b_qual = new JButton("ac");
        b_reset = new JButton("=");

        b_0.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current*=10;
                str=tf.getText()+"0";
                tf.setText(str);
        }});
        b_1.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+1;
                str=tf.getText()+"1";
                tf.setText(str);
        }});
        b_2.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+2;
                str=tf.getText()+"2";
                tf.setText(str);
        }});
        b_3.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+3;
                str=tf.getText()+"3";
                tf.setText(str);
        }});
        b_4.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+4;
                str=tf.getText()+"4";
                tf.setText(str);
        }});
        b_5.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+5;
                str=tf.getText()+"5";
                tf.setText(str);
        }});
        b_6.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+6;
                str=tf.getText()+"6";
                tf.setText(str);
        }});
        b_7.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+7;
                str=tf.getText()+"7";
                tf.setText(str);
        }});
        b_8.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+8;
                str=tf.getText()+"8";
                tf.setText(str);
        }});
        b_9.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                current=current*10+9;
                str=tf.getText()+"9";
                tf.setText(str);
        }});


        b_plus.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                calc();
                nlastOperation=1;
                str=tf.getText()+"+";
                tf.setText(str);
        }});
        b_moins.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                calc();
                nlastOperation=2;
                tf.setText(str);str=tf.getText()+"-";
                tf.setText(str);
        }});
        b_multiplication.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                calc();
                nlastOperation=3;
                str=tf.getText()+"*";
                tf.setText(str);
        }});
        b_div.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                calc();
                nlastOperation=4;
                str=tf.getText()+"";
                tf.setText(str);
        }});
        b_qual.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                tf.setText("0");
                current=0;
                total=0;
                nlastOperation=1;
        }});
        b_reset.addActionListener(new ActionListener(){//perfecto
            public void actionPerformed(ActionEvent e){
                calc();
                tf.setText(Double.toString(total));
                current=0;
                total=0;
                nlastOperation=1;
        }});;
        p_buttons.add(b_7);
        p_buttons.add(b_8);
        p_buttons.add(b_9);
        p_buttons.add(b_plus);

        p_buttons.add(b_4);
        p_buttons.add(b_5);
        p_buttons.add(b_6);
        p_buttons.add(b_moins);

        p_buttons.add(b_0);
        p_buttons.add(b_1);
        p_buttons.add(b_2);
        p_buttons.add(b_3);
        p_buttons.add(b_multiplication);

        p_buttons.add(b_0);
        p_buttons.add(b_qual);
        p_buttons.add(b_reset);
        p_buttons.add(b_div);

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
