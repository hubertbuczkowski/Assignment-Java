
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Words 
{
	 CSVReader reader = new CSVReader(new FileReader("yourfile.csv"));
     List myEntries = reader.readAll();
	
	
	public boolean WordSet(String word)
	{
		while(worditer.hasNext())
		{
			System.out.println(worditer.next());
		}
		
		return true;
	}
	
	public void AddWord(String word)
	{
		WordSet.add(word);
	}
}
