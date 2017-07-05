import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.sql.*;

public class FileServer1 extends JFrame 
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextArea jTextArea1; 
 	private JScrollPane jScrollPane1; 
	private JPanel contentPane;
	private JComboBox jComboBox1;
	// End of variables declaration
	public ServerSocket ssk;
	public Socket sck;
	public int ch=0;
	public String lg="";
	public String top="";
	public Socket res;
	public String booksAvail="";
	public int port=9100;
	

	public FileServer1()
	{
		super();
		initializeComponent();

		this.setVisible(true);
		try
		{
			FileInputStream fis1=new FileInputStream("LGAddress.txt");
			while((ch=fis1.read())!=-1)
			lg+=(char)ch;
			lg.trim();
			int i=0;
			ssk=new ServerSocket(8000);
			Thread t[]=new Thread[20];
			int buf[]=new int[10];
			while(true)
			{
				sck=ssk.accept();
				try{
					buf[i]=1;
				}
				catch(Exception e){
					System.out.println("Server Buffer Exhausted");
					break;
				}
				Myclass xyz = new Myclass(sck);
      			t[i] = new Thread(xyz);
     		 	t[i].start();
     		 	Thread.sleep(1000);
     		 	i++;
			}	
		}
		catch (Exception WE)
		{
			WE.printStackTrace();
		}
	}
	class Myclass implements Runnable 
	{
    Socket sck;
    public Myclass(Socket s){sck=s;}
    public void run()
    {
    	int i;
    	try{
    			DataInputStream dis=new DataInputStream(sck.getInputStream());
				top=dis.readUTF();
				jTextArea1.setText("\nRequest from Location Guard Accepted");
				jTextArea1.append("\nRequest Processing");
				jTextArea1.append("\nRequested "+top);
				if ((top.equals("Java Basics"))||(top.equals("Oops")))
				{
					File path=new File("Files/"+top+"/");
					
					File files[]=path.listFiles();	
					String filename[]=new String[files.length];
					String name="";
					for(i=0;i<files.length;i++){
						//filename[i]=files[i].getName();
						//System.out.println(filename[i]);
						name+=files[i].getName()+"@";
					}
					jTextArea1.append("\nSearching requested Data");
					System.out.println(name);
					jTextArea1.append("\nFound Requested Data");
					res=new Socket(lg,port);
					DataOutputStream dos=new DataOutputStream(res.getOutputStream());
					dos.writeUTF(name);
					//jTextArea1.append("\nResponse sent to Location Guard");
					//jTextArea1.append("\nReady for next Request");
					
				}
				else if(top.equals("DOS"))
					{
						jTextArea1.append("\nServer is Attacked by Denial of Service.\n Server cannot process further requests from any Client");
						jLabel3.setText ("Server is Attacked by DOS");
						ssk.close();
						sck.close();
						res.close();
					}
				
				else
				{
					String fileSnd="";
					FileInputStream fis=new FileInputStream("Files/"+top);
					while((ch=fis.read())!=-1)
					fileSnd+=(char)ch;
					res=new Socket(lg,port);
					DataOutputStream dos=new DataOutputStream(res.getOutputStream());
					dos.writeUTF(fileSnd);
				}
}
catch(Exception e){}
    	}
	}

	/* This method is called from within the constructor to initialize the form.*/
	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel2.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jLabel3 = new JLabel();
		jLabel3.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jTextArea1 = new JTextArea(); 
 		jScrollPane1 = new JScrollPane(); 
		jTextArea1.setFont(new Font("Book Antiqua",Font.BOLD,14));
		String label1[]={"Normal Client","Location Hiding"};
		jComboBox1 = new JComboBox(label1);
		
		jScrollPane1.setViewportView(jTextArea1); 
		contentPane = (JPanel)this.getContentPane();
		jLabel2.setText("Server Status");

		jComboBox1.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jComboBox1_actionPerformed(e);
			}

		});
		

		contentPane.setLayout(null);
		addComponent(contentPane, jLabel1, 0,2,587,92);
		addComponent(contentPane, jLabel2, 57,115,154,36);
		addComponent(contentPane, jLabel3, 55,141,466,39);
		addComponent(contentPane,jComboBox1, 255,121,150,30);
		addComponent(contentPane, jScrollPane1, 20,170,550,240); 
	
		
		this.setTitle("File Server");
		this.setLocation(new Point(88, 61));
		this.setSize(new Dimension(599, 462));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	private void jComboBox1_actionPerformed(ActionEvent e)
	{

		Object o = jComboBox1.getSelectedItem();
		System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
		String co=o.toString();
		if (co.equals("Normal Client"))
		{
			port=9100;
			
		}
		else
		{
			port=9000;

		}
		//System.out.println(port);
	}


	public static void main(String[] args) throws Exception
	{

		new FileServer1();
	}

}
