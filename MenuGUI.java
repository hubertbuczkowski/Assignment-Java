import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import java.awt.Color;

public class MenuGUI extends JFrame implements ActionListener 
{

	private JPanel contentPane;
	static JButton CSVbutton, txtbutton, Run;
	static JTextArea log;
	static JFileChooser fc1;
	static JFileChooser fc2;

	public MenuGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CSVbutton = new JButton("Open CSV file");
		CSVbutton.addActionListener(this);
		CSVbutton.setBounds(10, 11, 111, 23);
		contentPane.add(CSVbutton);
		
		txtbutton = new JButton("Open TXT file");
		txtbutton.addActionListener(this);
		txtbutton.setBounds(205, 11, 122, 23);
		contentPane.add(txtbutton);
		
		Run = new JButton("Run");
		Run.addActionListener(this);
		Run.setBounds(119, 323, 89, 23);
		contentPane.add(Run);
		
		
		log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane scrollPane= new JScrollPane(log);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setBounds(10, 69, 317, 235);
		contentPane.add(scrollPane);
		
		fc1 = new JFileChooser();
		fc2 = new JFileChooser();
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == CSVbutton) 
		{
            int returnVal = fc1.showOpenDialog(MenuGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc1.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + "\n");
            } 
            else 
            {
                log.append("Open command cancelled by user." + "\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		
		if (e.getSource() == txtbutton) 
		{
            int returnVal = fc2.showOpenDialog(MenuGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc2.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + "\n");
            } 
            else 
            {
                log.append("Open command cancelled by user." + "\n");
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		
		if (e.getSource() == Run) 
		{
			log.append("\n\n");
			
			try {
				Words wrds = new Words(fc1.getSelectedFile().getAbsolutePath());
			
			Reader rdr = new Reader(fc2.getSelectedFile().getAbsolutePath());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	}
}
