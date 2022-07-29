package sherlock;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
	public class VigenereCipher
	{
		public static String cipher(String data, final String keyword)
	    {
	        String resultant_string = "";
	        data = data.toUpperCase();//converting given cipher to UpperCase for feasibility//
	        for (int i = 0, j = 0; i < data.length(); i++)
	        {
	            char c = data.charAt(i);
	            if (c < 'A' || c > 'Z')
	                continue;
	            resultant_string = resultant_string +(char) ((c + keyword.charAt(j) - 2 * 'A') % 26 + 'A');
	            j = ++j % keyword.length();
	        }
	        return resultant_string;
	    }
	 
	    public static String decipher(String data, final String keyword)
	    {
	        String resultant_string = "";
	        data = data.toUpperCase();
	        for (int i = 0, j = 0; i < data.length(); i++)
	        {
	            char c = data.charAt(i);
	            if (c < 'A' || c > 'Z')
	                continue;
	            resultant_string = resultant_string+(char) ((c - keyword.charAt(j) + 26) % 26 + 'A');
	            j = ++j % keyword.length();
	        }
	        return resultant_string;
	    }
	 
	   public static void VigenereCipherstart(HashMap<String,User> users,String username) throws Exception
	    {
		    Scanner sc=new Scanner(System.in);
		    int choice;
		    System.out.println("1.Cipher\n2.Decipher");
		    choice=sc.nextInt();
		    Date d1 = new Date();
		    if(choice==1)
		    {
		        BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Enter the Keyword:");
		        String key1=read.readLine();
		        System.out.println("Enter the message");
		        String msg=read.readLine();
		        String cipheTxt=cipher(msg,key1);
		        System.out.println("Ciphered As:" + cipheTxt);
		        users.get(username).cipherHistory.add("VIGENERE CIPHER : FROM "+msg+" TO "+cipheTxt+" USING KEY:"+key1+" ON "+d1);
		    }
		    else if(choice==2)
		    {
		        BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Enter the Keyword:");
		        String key1=read.readLine();
		        System.out.println("Enter the message");
		        String msg=read.readLine();
		        String cipheTxt=decipher(msg,key1);
		        System.out.println("Your message:" + cipheTxt);
		        users.get(username).deCipherHistory.add("VIGENERE CIPHER : FROM "+msg+" TO "+cipheTxt+" USING KEY:"+key1+" ON "+d1);
		    }
	    }
	}

