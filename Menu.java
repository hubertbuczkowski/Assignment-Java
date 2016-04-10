
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu 
{

	static public List<String> TextList = new List<String>();
	
	
	
	public static void main(String[] args)throws FileNotFoundException, NullPointerException
	{
		Words wrds = new Words("words.csv");
		Scanner inputStream = new Scanner (new File("Blood.txt"));
		
		String[] Text;
			
		while(inputStream.hasNext())
		{
			String word = inputStream.next();

			word = word.replace(" ", "");
			word = word.replace(".", "");
			word = word.replace(",", "");
			word = word.replace("\"", "");
			
			Text.append("");
			
			if((TextList == null ||TextList.contains(word)== false) && wrds.WordCheck(word) == false)
			{
				TextList.add(word);
				System.out.println(word);
			}
		}
		
        inputStream.close();
	}

}

//&& Words.WordCheck(word) == false
