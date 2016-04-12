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
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;

public class MenuGUI extends JFrame implements ActionListener 
{
	private JPanel contentPane;
	static JButton CSVbutton, txtbutton, Run;
	static JTextArea log;
	static JFileChooser fc1;
	static JFileChooser fc2;
	static int j = 0;

	// In here i add all the buttons, textbox etc. and set bounds and add action listeners.
	public MenuGUI()
	{
		setTitle("Hubert's Assignment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CSVbutton = new JButton("Open CSV file");
		CSVbutton.addActionListener(this);
		CSVbutton.setBounds(10, 10, 120, 25);
		contentPane.add(CSVbutton);
		
		txtbutton = new JButton("Open TXT file");
		txtbutton.addActionListener(this);
		txtbutton.setBounds(250, 10, 120, 25);
		contentPane.add(txtbutton);
		
		Run = new JButton("Run");
		Run.addActionListener(this);
		Run.setBounds(135, 320, 120, 25);
		contentPane.add(Run);
		
		
		log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane scrollPane= new JScrollPane(log);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(10, 45, 360, 260);
		contentPane.add(scrollPane);
		
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
						Words wrds = new Words(fc1.getSelectedFile().getAbsolutePath());
						Reader rdr = new Reader(fc2.getSelectedFile().getAbsolutePath());
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
	}
}
