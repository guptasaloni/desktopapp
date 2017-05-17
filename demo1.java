import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

class demo1 
{
	JDialog d2;
	JLabel l,l1;
	Button b;
	
	public demo1(Frame f1)
	{
		d2=new JDialog(f1,"About");
		d2.setSize(400,400);
		l1=new JLabel("This Notepad Application Made By Saloni Gupta");
		
		JPanel p5=new JPanel();
		p5.setSize(50,50);
		
		ImageIcon i=new ImageIcon("your.jpg");
		l=new JLabel(i);
		p5.add(l);
		d2.add(p5,BorderLayout.WEST);
		d2.add(l1,BorderLayout.SOUTH);
		
		d2.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				d2.dispose();
			}
		});
		d2.setVisible(true);
	}
}