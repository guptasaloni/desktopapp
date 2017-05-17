//Notepad application

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.event.*;

class Note implements  ActionListener,TextListener
{
	
	Frame f;
	MenuBar mb;
	Menu file,edit,help;
	MenuItem nw,open,save,saveas,exit,cut,copy,paste,find,findr,about;
	Dialog d,d1;
	JDialog d2;
	Label l1,find_what,replace_with;
	TextField t1,t2,t3;
	TextArea ta = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	Button b1,b2,findnxt,replace,cancel,replace_all;
	Panel p1,p2,p3,p4;
	String string="";
	String dir,fname,dopen,fopen;
	String application_title="Notepad Editor";
	int flag=0,count=0;
	public Note()
	{
		f=new Frame();
		f.setSize(600,600);
		f.setTitle(application_title);
		f.setLayout(new BorderLayout());
		
		//new demo(f);
		//-----------menubar and its menues
		mb=new MenuBar();
		file=new Menu("File");
		edit=new Menu("Edit");
		edit.addActionListener(this);
		help=new Menu("Help");
		
		//------------menu items-----------
		nw=new MenuItem("New");
		nw.setShortcut(new MenuShortcut(KeyEvent.VK_N, false));
		nw.addActionListener(this);
		
		open=new MenuItem("Open");
		open.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
		open.addActionListener(this);
		
		save=new MenuItem("Save");
		save.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		save.addActionListener(this);
		save.setEnabled(false);
		
		saveas=new MenuItem("Save as");
		saveas.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
		saveas.addActionListener(this);
		saveas.setEnabled(false);
		
		exit=new MenuItem("Exit");
		exit.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		exit.addActionListener(this);
		//exit.setEnabled(false);
		
		cut=new MenuItem("Cut");
		cut.setShortcut(new MenuShortcut(KeyEvent.VK_X, false));
		cut.addActionListener(this);
		cut.setEnabled(false);
		
		copy=new MenuItem("Copy");
		copy.setShortcut(new MenuShortcut(KeyEvent.VK_C, false));
		copy.addActionListener(this);
		copy.setEnabled(false);
		
		paste=new MenuItem("Paste");
		paste.setShortcut(new MenuShortcut(KeyEvent.VK_V, false));
		paste.addActionListener(this);
		
		find=new MenuItem("Find");
		find.addActionListener(this);
		find.setEnabled(false);
		
		findr=new MenuItem("Find & Replace");
		findr.addActionListener(this);
		findr.setEnabled(false);
		
		about=new MenuItem("About");
		about.addActionListener(this);
		
		ta.addTextListener(this);
		
	//layout for finddialog box	
		d=new Dialog(f,"Find");
		d.setSize(400,400);
		p1=new Panel();
		p2=new Panel();
		p1.setLayout(new FlowLayout());
		p2.setLayout(new GridLayout(1,2));
		l1=new Label("Find What");
		t1=new TextField(10);
		
		b1=new Button("FindNext");
		b1.addActionListener(this);
		
		b2=new Button("Cancel");
		b2.addActionListener(this);
	
		
		p1.add(l1); 
		p1.add(t1);
		p2.add(b1);
		p2.add(b2);
		d.add(p1);
		d.add(p2,BorderLayout.SOUTH);
		d.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
	//layout for find and replace dialog box	
		d1=new Dialog(f,"Find & Replace");
		d1.setSize(400,400);
		p3=new Panel();
		p4=new Panel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.setLayout(new GridLayout(1,4));
		t2=new TextField(10);
		t3=new TextField(10);
		find_what=new Label("Find What");
		
		findnxt=new Button("FindNext");
		findnxt.addActionListener(this);
		
		cancel=new Button("Cancel");
		cancel.addActionListener(this);
		
		replace_with=new Label("Replace With");
		
		replace=new Button("Replace");
		replace.addActionListener(this);
		
		replace_all=new Button("Replace All");
		replace_all.addActionListener(this);
		
		p3.add(find_what); p3.add(t2);
		p3.add(replace_with); p3.add(t3);
		p4.add(findnxt); p4.add(replace); p4.add(replace_all);     p4.add(cancel);
		d1.add(p3);
		d1.add(p4,BorderLayout.SOUTH);
		d1.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		
		//---------------adding all the components------
		mb.add(file); mb.add(edit); mb.add(help);
		file.add(nw); file.add(open); file.add(save); file.add(saveas); file.addSeparator(); file.add(exit);
		edit.add(cut); edit.add(copy); edit.add(paste); edit.addSeparator();edit.add(find); edit.add(findr);
		help.add(about);
		//f.add(d); f.add(d1); //f.add(d2);
		f.add(ta,BorderLayout.CENTER);
		f.setMenuBar(mb);
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if(ta.getText().length()==0)
				{
					System.exit(0);
				}
				else
				{
					new exit_class(f,ta,flag,count);
				}
				
				
			}
		});
		f.setVisible(true);
		
	}
	//-------------this is used when user edit in the text area
	public void textValueChanged(TextEvent te)
	{
		if(te.getSource()==ta)
		{
			//count=0;
			flag=1;
			enabled();
		}
	}
	//-------------------from here events are start
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==cut)
		{
			 string=ta.getSelectedText();
            ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
		}
		//////////////////////
		else
			if(e.getSource()==copy)
			{
				 string=ta.getSelectedText();
			}
		//////////////////////////////////
		else
			if(e.getSource()==paste)
			{
				ta.replaceRange(string,ta.getSelectionStart(),ta.getSelectionEnd());
			}
		/////////////////////////////
		else
			if(e.getSource()==find)
		{
			
		}
		/////////////////
		else
			if(e.getSource()==findr)
			{
				
			}
			////////////////////////////
		else
			if(e.getSource()==about)
			{
				new demo1(f);
			}
		/////////////////////////////////////////
		else
			if(e.getSource()==b1)
			{
				
			}
		////////////////////////////////////
		else 
			if(e.getSource()==b2)
			{
				
			}
		/////////////////////////////
			else
				if(e.getSource()==cancel)
				{
					System.exit(0);
				}
			//////////////////////////
			else
				if(e.getSource()==exit)
				{
					new Exit_Window(f);
				}
			//////////////////////////////
			else
				if(e.getSource()==open)
				{
					open_file();
				}
			///////////////////////////////////
			else
				if(e.getSource()==save)
				{
					enabled();
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
			///////////////////////////////////
			else
				if(e.getSource()==saveas)
				{
					//flag=1;
					enabled();
					save_file();
					
				}
			//////////////////////////////////
			else
				if(e.getSource()==nw)
				{
					if(ta.getText().length()==0)
					{
						ta.setText("");
					}
					else
					{
						enabled();
						new demo(f,ta,flag,count);
					}
					
				} 
	}
	

	public void save_file()
	{
					try
					{
						FileDialog fd=new FileDialog(f,"Save File",FileDialog.SAVE);
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
						//bw.close();
						/*FileOutputStream fos=new FileOutputStream(str1);
						DataOutputStream dos=new DataOutputStream(fos);
						dos.writeBytes(txt);
						dos.close();*/
						f.setTitle(str1+"-"+application_title);
						//flag=0;
						if(fopen==null && count==1)
						{
							flag=1;
							String str=(dopen+fopen);
							System.out.println(str);
							f.setTitle(str+"-"+application_title);
						}
						else
						{
							flag=0;
							//f.setTitle(application_title);
						}
						//flag=0;
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
	public void enabled()
	{
					if(ta.getText().length()!=0)
					{
						find.setEnabled(true);
						findr.setEnabled(true); 
						cut.setEnabled(true);  
						copy.setEnabled(true);  
						paste.setEnabled(true);
						save.setEnabled(true);
						saveas.setEnabled(true);
						//exit.setEnabled(true);
					}
						
				
	}
	public void open_file()
	{
					try
					{
						FileDialog fd=new FileDialog(f,"Open File",FileDialog.LOAD);
						fd.setVisible(true);
						String str=" ",msg=" ";
						dopen=fd.getDirectory();
						fopen=fd.getFile();
						String str2=(dopen+fopen);
						//FileInputStream fis=new FileInputStream(str2);
						Scanner scan = new Scanner(new FileReader(str2));
						while (scan.hasNext()) 
							ta.append(scan.nextLine() + "\n"); 
						f.setTitle(str2+"-"+application_title);
						count=1;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
	}
	
	public static void main(String args[])
	{
		new Note();
	}
}

	