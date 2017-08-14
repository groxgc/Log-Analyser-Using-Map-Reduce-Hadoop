package Tool.Forensics_Log_Analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test 
{

public static void main(String Args[])
{
    //Flag    
    String FILENAME = "output/part-r-00000";
    String FILENAME2 = "output2/part-r-00000";
    BufferedReader br = null;
	FileReader fr = null;
	BufferedReader br2 = null;
	FileReader fr2 = null;
	int[] array = new int[3];
	int[] array2 = new int[3];
	try 
	{
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);
		fr2 = new FileReader(FILENAME2);
		br2 = new BufferedReader(fr2);
		String sCurrentLine;
		String sCurrentLine2;		
		br = new BufferedReader(new FileReader(FILENAME));
		int j=0;
		while ((sCurrentLine = br.readLine()) != null) 
		{
		String delims = "[	]+";
		String[] tokens = sCurrentLine.split(delims);
		for (int i = 0; i < tokens.length; i++)
			{			
			tokens[i].toString();
			i++;
			array[j]=Integer.parseInt(tokens[i].toString());
			j++;
			}
		}
		br2 = new BufferedReader(new FileReader(FILENAME2));
		j=0;
		while ((sCurrentLine2 = br2.readLine()) != null) 
		{
		String delims2 = "[	]+";
		String[] tokens2 = sCurrentLine2.split(delims2);		
		for (int i = 0; i < tokens2.length; i++)
			{			
			tokens2[i].toString();
			i++;
			array2[j]=Integer.parseInt(tokens2[i].toString());
			j++;
			}
		}
		if(array2[0]>(1.5*array[0]))
		{System.out.println(array2[0]);}
		if(array2[1]>(1.5*array[1]))
		{System.out.println(array2[1]);}
		
		
	}
	 catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

			if (br != null)
				br.close();
			if (fr != null)
				fr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
//Flag
}	
}