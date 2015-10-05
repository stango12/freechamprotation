package freechamp;

import java.lang.reflect.Array;
import java.net.*;
import java.io.*;

public class FreeWeekChampRotation {
	//reads the text from a URL
    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    public static void main(String[] args) throws Exception {
    	//free champ rotation
        String content = FreeWeekChampRotation.getText("https://na.api.pvp.net/api/lol/na/v1.2/champion?freeToPlay=true&api_key=<API KEY>");
        //System.out.println(content);
        int[] id = new int[11];
        int n = 0;
        for(int i = 0; i < content.length(); i++)
        {
        	String temp = "";
        	while(content.charAt(i) >= 48 && content.charAt(i) <= 57)
        	{
        		temp += content.charAt(i);
        		i++;
        	}
        	if(!temp.equals("") && Integer.parseInt(temp) != 0)
        	{
	        	id[n] = Integer.parseInt(temp);
	        	n++;
        	}
        }
        
        System.out.println("Free Champions this week");
        System.out.println("------------------------");
    	for(int i = 0; i < id.length; i++)
    	{
    		if(id[i] != 0)
    		System.out.println(IDtoChamp(printID(id[i])));
    	}
    }
    
    public static String printID(int a) throws Exception //finds the champ ID
    {
            return FreeWeekChampRotation.getText("https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion/"+ a + "?api_key=<API KEY>");
    }
    
    public static String IDtoChamp(String id) //finds the name of the champion with the ID
    {
    	int index = id.indexOf("\"name\"") + 8;
    	String champName = "";
    	while(id.charAt(index) != '\"')
    	{
    		champName += id.charAt(index);
    		index++;
    	}
    	return champName;
    }
}
