package sherlock;
import java.util.*; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
public class CaesarCipher {
	StringBuffer cipheredData=new StringBuffer();
	StringBuffer deCipheredData=new StringBuffer();
	StringBuffer cipher(String data,short shift ) {
		for(byte a:data.getBytes()) {
			if(a<=90&&a>=65) {
				cipheredData.append((char)((a-65+shift)%26+65));
			}
			else {
				cipheredData.append((char)((a-97+shift)%26+97));
			}
		}
		return cipheredData;
	}
	StringBuffer deCipher(String data,short shift) {
		for(byte a:data.getBytes()) {
			if(a<=90&&a>=65) {
				deCipheredData.append((char)((a-65-shift)%26+65));
			}
			else {
				deCipheredData.append((char)((a-97-shift)%26+97));
			}
		}	
		return deCipheredData;
	}
	public static void CaesarCipherstart(HashMap<String,User> users,String username) throws Exception
    { 
		short s;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("TO WRITE A CIPHER PRESS 1 TO DECIPHER A TEXT PRESS 2");
        int choice=Integer.parseInt(br.readLine());
        Date d1 = new Date();
        switch(choice) {
        case 1:
        	System.out.println("ENTER TEXT");
        	String text=br.readLine();
        	System.out.println("ENTER SHIFT(SHIFT IS NUMBER OF POSITIONS BY WHICH YOU WANT TO REPLACE THE LETTER)");
        	s=(short) Integer.parseInt(br.readLine());
        	CaesarCipher temp=new CaesarCipher();
        	System.out.println("Encryption: "+temp.cipher(text, s)); 
        	users.get(username).cipherHistory.add("CAESAR CIPHER : FROM "+text+" TO "+temp.cipheredData.toString()+" ON "+d1);
        	break;
        case 2:
        	System.out.println("ENTER TEXT");
        	text=br.readLine();
        	System.out.println("ENTER SHIFT");
        	s=(short) Integer.parseInt(br.readLine());
        	temp=new CaesarCipher();
        	System.out.println("Decryption: "+temp.deCipher(text, s));
        	users.get(username).deCipherHistory.add("CAESAR CIPHER : FROM "+text+" TO "+temp.deCipheredData.toString()+" ON "+d1);
        	break;
        default:
        		System.out.println("\nWRONG CHOICE YOU WERE SUPPOSED TO ENTER 1 OR 2");
        }
    } 
}
