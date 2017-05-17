import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

class Exit_Window implements ActionListener
{
	Dialog d;
	Label lbl_show_msg;
    Button btn_yes,btn_no;
	Frame f;
    public Exit_Window(Frame f1)
    {
        //super(,"Exit",true);
		//setSize(300,300);
		d=new Dialog(f1,"Exit");
        d.setLayout(new FlowLayout());       
       
        lbl_show_msg=new Label("Are you want to exit ??");
        d.add(lbl_show_msg);
       
        btn_yes=new Button("Yes");
        btn_yes.addActionListener(this);
        d.add(btn_yes);
       
        btn_no=new Button("No");
        btn_no.addActionListener(this);
        d.add(btn_no);
       
        d.setTitle("Exit Window");
        d.setSize(150,100);
        d.setLocation(200,200);
		d.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn_yes)
        {
            System.exit(0);
        }
        else
		{
			d.dispose();
		}
               
    }
}