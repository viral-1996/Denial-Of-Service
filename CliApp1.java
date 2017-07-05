import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class CliApp1 extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel21;
	private JPanel contentPane;
	private JTextArea jTextArea1; 
 	private JScrollPane jScrollPane1; 
	private JLabel jLabel2;
	private JPanel jPanel1;
	private JComboBox jComboBox1;
	private JButton jButton1;

	private JLabel jLabel3;
	private JLabel jLabel4;
	private JRadioButton jRadioButton1;
	private JRadioButton jRadioButton2;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JPanel jPanel2;

	public Socket lgreq;
	public ServerSocket downSer;
	public Socket downRec;
	public String LocGrd="";
	public String downFile="";
	public String jBut="";
	
	public int ch=0;
	private JLabel lblfile[]=new JLabel[50];
	public String comboVal="";
	// End of variables declaration


	public CliApp1()
	{
		super();
		initializeComponent();

		this.setVisible(true);
		try
		{
				jScrollPane1.setVisible(false);
				FileInputStream fis=new FileInputStream("LGAddress.txt");
				while((ch=fis.read())!=-1)
				LocGrd+=(char)ch;
				LocGrd.trim();
		}
		catch (Exception as)
		{
			as.printStackTrace();
		}
	}

	/* This method is called from within the constructor to initialize the form.*/
	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		
		jButton1 = new JButton();

		jTextArea1 = new JTextArea(); 
 		jScrollPane1 = new JScrollPane(); 

		jScrollPane1.setViewportView(jTextArea1); 
		contentPane = (JPanel)this.getContentPane();
		for(int i=0;i<50;i++)
		 lblfile[i]=new JLabel();
		for(int i=0;i<50;i++)
			lblfile[i].setFont(new Font("Franklin Gothic Medium Cond",Font.BOLD,14));


		jLabel2 = new JLabel();
		jLabel21 = new JLabel();
		jPanel1 = new JPanel();
		String[] label1={"Java Basics","Oops"};
		jComboBox1 = new JComboBox(label1);

		jLabel3 = new JLabel();
		jLabel3.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jLabel4 = new JLabel();
		jLabel4.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jRadioButton1 = new JRadioButton("safs",false);
		jRadioButton1.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jRadioButton2 = new JRadioButton();
		jLabel21.setText("Exit");
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(jRadioButton1);
		bg.add(jRadioButton2);
		
		jRadioButton2.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jPanel2 = new JPanel();

		//jLabel1.setIcon(new ImageIcon("Java1.PNG"));
		jButton1.setForeground(new Color(0, 0, 102));
		jButton1.setText("Back");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton1_actionPerformed(e);
			}

		});
		
		
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0));
		addComponent(contentPane, jLabel1, 0,1,691,97);
		addComponent(contentPane, jPanel1, 10,110,174,374);
		addComponent(contentPane, jPanel2, 196,111,487,373);
		addComponent(jPanel1, jLabel21, 50,320,51,20);
		addComponent(jPanel2, jScrollPane1, 5,10,459,311); 

		addComponent(jPanel2, lblfile[0], 5,70,210,30);
		addComponent(jPanel2, lblfile[1], 250,70,210,30);
		addComponent(jPanel2, lblfile[2], 5,120,210,30);
		addComponent(jPanel2, lblfile[3], 250,120,210,30);
		addComponent(jPanel2, lblfile[4], 5,170,210,30);
		addComponent(jPanel2, lblfile[5], 250,170,210,30);
		addComponent(jPanel2, lblfile[6], 5,220,210,30);
		addComponent(jPanel2, lblfile[7], 250,220,210,30);
		addComponent(jPanel2, lblfile[8], 5,270,210,30);
		addComponent(jPanel2, lblfile[9], 250,270,210,30);
		addComponent(jPanel2, lblfile[10], 5,320,210,30);
		addComponent(jPanel2, lblfile[11], 250,320,210,30);
		addComponent(jPanel2,jButton1,270,350,100,20);
		jButton1.setVisible(false);


		jPanel1.setLayout(null);
		jPanel1.setBackground(new Color(255,255,255));
		jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
		addComponent(jPanel1, jLabel2, 12,10,148,351);
		//
		// jLabel3
		 jLabel21.setFont(new Font("Book Antiqua",Font.BOLD,14));
		//
		jLabel3.setText("Choose Topics");
		
		//
		// jLabel4
		//

		jRadioButton1.setSelected(false);
		jRadioButton2.setSelected(false);


		jRadioButton1.setText("Student");
		jRadioButton1.setSelected(false);
		jRadioButton1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jRadioButton1_itemStateChanged(e);
			}

		});
		//
		// jRadioButton2
		//
		jRadioButton2.setText("Programmer");
		jRadioButton2.setSelected(false);
		jRadioButton2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jRadioButton2_itemStateChanged(e);
			}

		});
		//
		// jTextField1
		//
		jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jTextField1_actionPerformed(e);
			}

		});
			lblfile[0].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 JLabel lbl1=(JLabel)evt.getSource();
			 callDown(lbl1.getText());
			 System.out.println(lbl1.getText());
			 
			 //jLabel21MouseClicked(evt);
		 }
		});
			lblfile[1].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl2=(JLabel)evt.getSource();
			 callDown(lbl2.getText());
			 System.out.println(lbl2.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});
			lblfile[2].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl3=(JLabel)evt.getSource();
			 callDown(lbl3.getText());
			 System.out.println(lbl3.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[3].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl4=(JLabel)evt.getSource();
			 callDown(lbl4.getText());
			 System.out.println(lbl4.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[4].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl5=(JLabel)evt.getSource();
			 callDown(lbl5.getText());
			 System.out.println(lbl5.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[5].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl6=(JLabel)evt.getSource();
			 callDown(lbl6.getText());
			 System.out.println(lbl6.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[6].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl7=(JLabel)evt.getSource();
			 callDown(lbl7.getText());
			 System.out.println(lbl7.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});
		lblfile[7].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl8=(JLabel)evt.getSource();
			 callDown(lbl8.getText());
			 System.out.println(lbl8.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[8].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl9=(JLabel)evt.getSource();
			 callDown(lbl9.getText());
			 System.out.println(lbl9.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[9].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl10=(JLabel)evt.getSource();
			 callDown(lbl10.getText());
			 System.out.println(lbl10.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[10].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl11=(JLabel)evt.getSource();
			 callDown(lbl11.getText());
			 System.out.println(lbl11.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		lblfile[11].addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 
			 JLabel lbl12=(JLabel)evt.getSource();
			 callDown(lbl12.getText());
			 System.out.println(lbl12.getText());
			 //jLabel21MouseClicked(evt);
		 }
		});

		jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
		 public void mouseClicked(java.awt.event.MouseEvent evt) {
			 System.exit(0);
			 //jLabel21MouseClicked(evt);
		 }
		});

		jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        jLabel21.setFont(new Font("Book Antiqua",Font.BOLD,18));
		jLabel21.setForeground(new Color(18,200,252));
    }

	public void mouseExited(java.awt.event.MouseEvent evt) {
		jLabel21.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jLabel21.setForeground(new Color(0,0,0));       
    }
});
		//
		// jTextField2
		//
		jTextField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jTextField2_actionPerformed(e);
			}

		});
		//
		// jPanel2
		//
		//
		// jComboBox1
		//
		jComboBox1.setFont(new Font("Book Antiqua",Font.BOLD,14));
		jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jComboBox1_actionPerformed(e);
			}

		});
		jPanel2.setLayout(null);
		jPanel2.setBackground(new Color(255,255,255));
		jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
		addComponent(jPanel2, jComboBox1, 150,30,150,30);
		addComponent(jPanel2, jLabel3, 20,30,132,35);

		jLabel3.setForeground(new Color(18,200,252));
		jLabel4.setForeground(new Color(18,200,252));
		jRadioButton1.setForeground(new Color(18,200,252));
		jRadioButton2.setForeground(new Color(18,200,252));


		this.setTitle("Downloader");
		this.setLocation(new Point(200, 100));
		this.setSize(new Dimension(703, 528));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	private void jComboBox1_actionPerformed(ActionEvent e)
	{
		System.out.println("\nTechnology Selected");
		
		Object o = jComboBox1.getSelectedItem();
		System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
		sendRequest(o.toString());
		comboVal=o.toString();


	}

	public void callDown(String we)
	{

		try
		{
			    lgreq= new Socket(LocGrd,8000);
				DataOutputStream dos56 = new DataOutputStream(lgreq.getOutputStream());
				dos56.writeUTF(comboVal+"/"+we);

				lgreq.close();
				
		}
		catch(Exception sd)
		{
				sd.printStackTrace();
		}

	}


	private void jLabel21MouseClicked(ActionEvent e)
	{
		System.exit(0);
	}

	private void jRadioButton1_itemStateChanged(ItemEvent e)
	{
		System.out.println("\njRadioButton1_itemStateChanged(ItemEvent e) called.");
		System.out.println(">>" + ((e.getStateChange() == ItemEvent.SELECTED) ? jBut="rb1":"unselected"));
		System.out.println(" jBut== "+jBut);
		
	}

	private void jRadioButton2_itemStateChanged(ItemEvent e)
	{
		System.out.println("\njRadioButton2_itemStateChanged(ItemEvent e) called.");
		System.out.println(">>" + ((e.getStateChange() == ItemEvent.SELECTED) ? jBut="rb2":"unselected"));
		System.out.println(" jBut== "+jBut);
		
	}
	private void jButton1_actionPerformed(ActionEvent e)
	{
									jComboBox1.setVisible(true);
									 jScrollPane1.setVisible(false);
									 for(int q=0;q<12;q++)
										{
											lblfile[q].setVisible(true);
										}


	}

	private void jTextField1_actionPerformed(ActionEvent e)
	{
		System.out.println("\njTextField1_actionPerformed(ActionEvent e) called.");

	}

	private void jTextField2_actionPerformed(ActionEvent e)
	{
		System.out.println("\njTextField2_actionPerformed(ActionEvent e) called.");

	}

	private void sendRequest(String top)
	{
		try
		{
				lgreq= new Socket(LocGrd,8000);
				DataOutputStream dos = new DataOutputStream(lgreq.getOutputStream());
				dos.writeUTF(top);
				
		}
		catch(Exception sd)
		{
				sd.printStackTrace();
		}
	}

	private void jButton2_actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}
	public void listen()
	{
		
		try
		{

				downSer=new ServerSocket(9100);
					while(true)
					{
						downRec=downSer.accept();
						DataInputStream dis = new DataInputStream(downRec.getInputStream());
						downFile=dis.readUTF();
						System.out.println("Total"+downFile);
						downSer.close();
						downRec.close();
						
						String filename[]=downFile.split("@");
						String len=""+filename.length;

						if(filename.length<2)
						{
							for(int t=0;t<12;t++)
								{
									lblfile[t].setVisible(false);
									jComboBox1.setVisible(false);
									 jScrollPane1.setVisible(true);
									 jTextArea1.setText(downFile);
									 jButton1.setVisible(true);

								}
								
						}
						else
						{
							for(int i=0;i<50;i++){
							lblfile[i].setText(" ");

							}
							for(int i=0;i<filename.length;i++){
							lblfile[i].setText(filename[i]);

						}
						}
					}


				
			
		}
		catch ( Exception e)
		{
		}
	}

	public static void main(String[] args)
	{

		try
		{
			CliApp1 obj=new CliApp1();
			while(true)
			{

				obj.listen();
			}
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new CliApp1();
	}


}
