import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Reader 
{
	static List<String> TextList;
	
	public Reader(String Address) throws FileNotFoundException
	{
		
		Scanner inputStream = new Scanner (new File("Blood.txt"));
		
		while(inputStream.hasNext())
		{
			String word = inputStream.next();
			if(TextList.contains(word) == false && Words.WordCheck(word) == false)
			{
				TextList.add(word);
			}
		}
		
        inputStream.close();


	}
}
