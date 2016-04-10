import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Words
{
	static List<String> StopList;
	
	public Words (String address) throws FileNotFoundException
	{
		Scanner inputStream = new Scanner (new File(address));
		String data = inputStream.nextLine();
		StopList = Arrays.asList(data.split(", "));
		inputStream.close();
	}
	
	public static boolean WordCheck (String Word)
	{
		return StopList.contains(Word);
	}
}
