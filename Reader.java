import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * This is reader which read .txt files and catch words and put them into list without repetition.
 * 
 * After creating full list, then it display 10 words in text area in program.
 */
public class Reader 
{
	//Create list which works as 2D array and pointer to specific word in list
	List<String> TextList = new ArrayList<String>();
	List<Integer> WordAmount = new ArrayList<Integer>();
	static int pointer;
	
	//This contributor need address to open .txt file and do expected operations
	public Reader(String Address) throws FileNotFoundException
	{
		int i;
		Scanner inputStream = new Scanner (new File(Address));
		
		//This loops go through every word and checks 
		while(inputStream.hasNext())
		{
			String word = inputStream.next();
			
			
			//Removing all dirty characters from word
			word = word.replace(" ", "");
			word = word.replace(".", "");
			word = word.replace(",", "");
			word = word.replace("\"", "");
			word = word.replace("-", "");
			word = word.replace("*", "");
			word = word.replace("\t", "");
			word = word.replace("\n", "");
			word = word.toLowerCase();
			
			
			//Checks if word is stopword or if is in the list or if is empty
			//if conditions are ok, id adds word to list and increase counter to proper list element
			if((TextList == null ||TextList.contains(word)== false) && Words.WordCheck(word) == false && word.isEmpty() == false && 
					(Words.AddedList.contains(word) == false || Words.AddedList == null))
			{
				TextList.add(word);
				WordAmount.add(0);
			}
			if(TextList.contains(word) == true)
			{
				WordAmount.set(TextList.indexOf(word), WordAmount.get(TextList.indexOf(word)) + 1);
			}
		}
		
		//display 10 words in textarea in program
		MenuGUI.log.append("--------------  10 Most Popular Words in Text  --------------\n\n");
		for(i=0; i < 10; i++)
		{
			pointer = WordAmount.indexOf(Collections.max(WordAmount));
			MenuGUI.log.append("|  ->  " + TextList.get(pointer) + "\n");
			WordAmount.set(pointer, 0);
		}
		//close reader
        inputStream.close();
	}
}
