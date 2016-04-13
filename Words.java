import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Simple class to read all stopwords and pass them to list

public class Words
{
	static List<String> StopList;
	static List<String> AddedList = new ArrayList<String>();
	
	//this function read CSV file and add words to list
	public Words (String address) throws FileNotFoundException
	{
		Scanner inputStream = new Scanner (new File(address));
		String data = inputStream.nextLine();
		StopList = new ArrayList<String>(Arrays.asList(data.split(", ")));
		inputStream.close();
	}
	
	//check if passed Word is in the list
	public static boolean WordCheck (String Word)
	{
		return StopList.contains(Word);
	}
	
	//Adding Stop Word
	public static void AddWord(String word)
	{
		word = word.toLowerCase();
		
		if(AddedList.contains(word) == false && StopList.contains(word) == false)
		{
				AddedList.add(word);
				MenuGUI.log.append("Word " + word + " has been added\n");
			
		}
		else
		{
			MenuGUI.log.append("Stopword already exists\n");
		}
	}
	
	//Deleting Stop Word
	public static void DelWord(String word)
	{
		word = word.toLowerCase();
		
		if(StopList.contains(word) == false && AddedList.contains(word) == true)
		{
				AddedList.remove(word);
				MenuGUI.log.append("Word " + word + " has been removed\n");
			
		} 
		else if(StopList.contains(word) == true)
		{
			MenuGUI.log.append("Cant be deleted\n");
		}
		else
		{
			MenuGUI.log.append("Stopword doesn't exists\n");
		}
		
	}
	
}
