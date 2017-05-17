///this clas is for when i click on new button and textarea contain some text thn it should ask for save that text or not?

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

class demo implements ActionListener
{
	Dialog d;
	Frame f;
	Label lbl_show_msg;
	Button save,dont_save,cancel;
	TextArea ta;
	Panel p;
	int flag=0,count=0;
	String dir,dopen,fname,fopen;
	
	public demo(Frame f1,TextArea ta1,int flag1,int count1)
	{
		count=count1;
		flag=flag1;
		f=f1;
		ta=ta1;
		d=new Dialog(f1,"Save");
		//per(f1,"save",true);
		d.setSize(200,100);
		d.setLocation(200,200);
		p=new Panel();
		p.setLayout(new GridLayout(1,3));
		
		
		lbl_show_msg=new Label("You want to save or not??");
		d.add(lbl_show_msg);
		
		save=new Button("Save");
		save.addActionListener(this);
		p.add(save);
		
		dont_save=new Button("Dont_Save");
		dont_save.addActionListener(this);
		p.add(dont_save);
		
		cancel=new Button("Cancel");
		cancel.addActionListener(this);
		p.add(cancel);
		
		d.add(p,BorderLayout.SOUTH);
		d.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				d.dispose();
			}
		});
		
		d.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==save)
		{
			d.dispose();
					if(fname==null && count==0)
					{
						save_file();
						System.out.println("if block"+count);
					
					}
					else
					{
						System.out.println("else block"+count);
						FileD_class();	
					}
			
			
		}
		else
			if(e.getSource()==dont_save)
			{
				d.dispose();
				ta.setText("");
				f.setTitle("Notepad Editor");
				//System.exit(1);
			}
		else
		{
			d.dispose();
		}
	}
	public void save_file()
	{
					try
					{
						FileDialog fd=new FileDialog(f,"Saveas File",FileDialog.SAVE);
						fd.setVisible(true);
						String txt=ta.getText();
						dir=fd.getDirectory();
						fname=fd.getFile();
						String str1=(dir+fname);
						if(fname!=null)
						{
							BufferedWriter bw = new BufferedWriter(new FileWriter(str1));
							bw.write(ta.getText()); 
							bw.close();
						}
						f.setTitle(str1+"-"+"Notepad Editor");
						//flag=0;
						if(fname==null && count==1)
						{
							flag=1;
							String str=(dopen+fopen);
							System.out.println(str);
							f.setTitle(str+"-"+"Notepad Editor");
						}
						else
						{
							flag=0;
							f.setTitle("Notepad Editor");
						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
	}
	void FileD_class()
	{
		FileOutputStream fos;
					try
					{
					String str=ta.getText();
					if(count==0)
					{
					 String str1=(dir+fname);
					 fos=new FileOutputStream(str1);
					}
					else
					{
						String str2=(dopen+fopen);
						System.out.println("Hello");
						fos=new FileOutputStream(str2);
					}
					DataOutputStream dos=new DataOutputStream(fos);
					dos.writeBytes(str);
					dos.close();
					flag=0;
					}
					catch(Exception E)
					{
						E.printStackTrace();
					}
	}
		public void open_file()
	{
		try
					{
						FileDialog fd=new FileDialog(f,"Open File",FileDialog.LOAD);
						fd.setVisible(true);
						dopen=fd.getDirectory();
						fopen=fd.getFile();
						String str2=(dopen+fopen);
						Scanner scan = new Scanner(new FileReader(str2));
						while (scan.hasNext()) 
							ta.append(scan.nextLine() + "\n"); 
						/*FileInputStream fis=new FileInputStream(str2);
						DataInputStream dis=new DataInputStream(fis);
						String str=" ",msg=" ";
						while((str=dis.readLine())!=null)
						{
							msg=msg+str;
							msg+="\n";
						}
						ta.setText(msg);
						dis.close();*/
						f.setTitle(str2+"-"+"Notepad Editor");
						count=1;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
	}
}
