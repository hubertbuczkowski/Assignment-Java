import java.util.ArrayList;

public class Reader 
{
	File file = new File("prog4.dat");
	
	Scanner scanFile = new Scanner(new FileReader(file));
	3ayList<String> words = new ArrayList<String>();
	
	String theWord;    

	while (scanFile.hasNext())
	{
	    theWord = scanFile.next();
	    words.add(theWord);
	}

}
