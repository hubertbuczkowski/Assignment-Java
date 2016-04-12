import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Simple class to read all stopwords and pass them to list

public class Words
{
	static List<String> StopList;
	
	//this function read CSV file and add words to list
	public Words (String address) throws FileNotFoundException
	{
		Scanner inputStream = new Scanner (new File(address));
		String data = inputStream.nextLine();
		StopList = Arrays.asList(data.split(", "));
		inputStream.close();
	}
	
	//check if passed Word is in the list
	public static boolean WordCheck (String Word)
	{
		return StopList.contains(Word);
	}
}
