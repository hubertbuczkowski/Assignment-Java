import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reader 
{
	static List<String> TextList = new ArrayList<String>();
	static List<Integer> WordAmount = new ArrayList<Integer>();
	static int pointer;
	
	public Reader(String Address) throws FileNotFoundException
	{
		int i;
		Scanner inputStream = new Scanner (new File("Blood.txt"));
		
		
		while(inputStream.hasNext())
		{
			String word = inputStream.next();

			word = word.replace(" ", "");
			word = word.replace(".", "");
			word = word.replace(",", "");
			word = word.replace("\"", "");
			word = word.replace("-", "");
			word = word.toLowerCase();
			
			
			if((TextList == null ||TextList.contains(word)== false) && Words.WordCheck(word) == false)
			{
				TextList.add(word);
				WordAmount.add(1);
			}
			if(TextList.contains(word) == true)
			{
				WordAmount.set(TextList.indexOf(word), WordAmount.get(TextList.indexOf(word)) + 1);
			}
		}
		
		for(i=0; i < 10; i++)
		{
			pointer = WordAmount.indexOf(Collections.max(WordAmount));
			MenuGUI.log.append(TextList.get(pointer) + " - " + WordAmount.get(pointer) + "\n");
			WordAmount.set(pointer, 0);
		}
		
        inputStream.close();
	}
}
