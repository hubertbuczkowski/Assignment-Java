import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuGUI extends JFrame implements ActionListener 
{
	private JPanel contentPane;
	static JButton CSVbutton, txtbutton, Run, DelButton, AddButton;
	static JTextArea log;
	static JFileChooser fc1;
	static JFileChooser fc2;
	static int j = 0;
	private JTextField Stopword;
	Words wrds;
	Reader rdr;
	String textbox;

	// In here i add all the buttons, textbox etc. and set bounds and add action listeners.
	public MenuGUI()
	{
		setTitle("Hubert's Assignment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CSVbutton = new JButton("Open CSV file");
		CSVbutton.addActionListener(this);
		CSVbutton.setBounds(10, 21, 150, 40);
		contentPane.add(CSVbutton);
		
		txtbutton = new JButton("Open TXT file");
		txtbutton.addActionListener(this);
		txtbutton.setBounds(220, 21, 150, 40);
		contentPane.add(txtbutton);
		
		Run = new JButton("Run");
		Run.addActionListener(this);
		Run.setBounds(10, 415, 360, 40);
		contentPane.add(Run);
        JScrollPane scrollPane= new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(10, 144, 360, 260);
		contentPane.add(scrollPane);
		
		
		log = new JTextArea(5,20);
		scrollPane.setViewportView(log);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		
		Stopword = new JTextField();
		Stopword.setHorizontalAlignment(SwingConstants.CENTER);
		Stopword.setText("Type Stopword");
		Stopword.addFocusListener(new FocusListener() 
		{
			public void focusGained(FocusEvent e) 
			{
				Stopword.setText("");
			}
			public void focusLost(FocusEvent arg0)
			{
				textbox = Stopword.getText();
			}
		});
		Stopword.setBounds(20, 72, 337, 25);
		contentPane.add(Stopword);
		Stopword.setColumns(10);
		
		AddButton = new JButton("Add Stopword");
		AddButton.addActionListener(this);
		AddButton.setBounds(35, 108, 150, 25);
		contentPane.add(AddButton);
		
		DelButton = new JButton("Remove Stopword");
		DelButton.addActionListener(this);
		DelButton.setBounds(195, 108, 150, 25);
		contentPane.add(DelButton);
		
		//It allows me to let user to browse files
		fc1 = new JFileChooser();
		fc2 = new JFileChooser();
		
		//This filters prevent user from choosing wrong type of file
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("CSV", "csv", "csv");
		fc1.setFileFilter(filter1);
		
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc2.setFileFilter(filter2);
	}
	
	//Here are all actions which can be made
	public void actionPerformed(ActionEvent e) 
	{
		
		//Actions for CSV button
		if (e.getSource() == CSVbutton) 
		{
            int returnVal = fc1.showOpenDialog(MenuGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File file = fc1.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + "\n");
                if(j == 0 || j == 4)
                {
                	j=1;
                }
                else if(j == 2)
                {
                	j=3;
                }
                
                try {
					wrds = new Words(fc1.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
                
            } 
            else 
            {
                log.append("Open command cancelled by user." + "\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		
		//Actions for txtbutton
		if (e.getSource() == txtbutton) 
		{
            int returnVal = fc2.showOpenDialog(MenuGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File file = fc2.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + "\n");
                
                if(j == 0)
                {
                	j=2;
                }
                else if(j == 1  || j == 4)
                {
                	j=3;
                }
            } 
            else 
            {
            	
                log.append("Open command cancelled by user." + "\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		
		//Options for Run button. Display all messages 
		//about choosing files and run if all files are chosen
		if (e.getSource() == Run) 
		{
			switch (j)
			{
			case 0: log.append("Choose CSV and Text file\n");
					break;
			case 1: log.append("Choose Text file\n");
					break;
			case 2: log.append("Choose CSV\n");
					break;
				
			case 3: try 
					{
						log.append("\n\n");
						//wrds = new Words(fc1.getSelectedFile().getAbsolutePath());
						rdr = new Reader(fc2.getSelectedFile().getAbsolutePath());
						log.append("\n\n");
					} 
					catch (FileNotFoundException e1) 
					{
						e1.printStackTrace();
					}
					
					j = 4;
					break;
					
			case 4: log.append("I'm Sorry but you have to choose again text file\n");
					j = 1;
					break;
			}
		}
		
		//button responsible for adding stop word
		if(e.getSource() == AddButton)
		{
			
			textbox = Stopword.getText();
			
			if(textbox.compareTo("Type Stopword") == 0)
			{
				log.append("First enter word into text box\n");
			}
			else
			{
				if (j == 1 || j == 3 || j == 4)
				{
					Words.AddWord(textbox);
				}
				else
				{
					log.append("Choose CSV\n");
				}
			}
		}
		
		//responsible for deleting  stop word
		if(e.getSource() == DelButton)
		{
			
			textbox = Stopword.getText();
			
			if(textbox.compareTo("Type Stopword") == 0)
			{
				log.append("First enter word into text box\n");
			}
			else
			{
				if (j == 1 || j == 3 || j == 4)
				{
					Words.DelWord(textbox);
				}
				else
				{
					log.append("Choose CSV\n");
				}
			}
		}
	}
}
