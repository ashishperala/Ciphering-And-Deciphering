package sherlock;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class RailFenceCipher {
	int numRails;

	public RailFenceCipher(int numRails) {
		this.numRails = numRails;
	}

	String getDecryptedData(String data) {
		char[] decrypted = new char[data.length()];
		int n = 0;
		for(int k = 0 ; k < numRails; k ++) {
			int index = k;
			boolean down = true;
			while(index < data.length() ) {
				//System.out.println(k + " " + index+ " "+ n );
				decrypted[index] = data.charAt(n++);
				
				if(k == 0 || k == numRails - 1) {
					index = index + 2 * (numRails - 1);
				}
				else if(down) {
					index = index +  2 * (numRails - k - 1);
					down = !down;
				}
				else {
					index = index + 2 * k;
					down = !down;
				}
			}
		}
		return new String(decrypted);
	}
	
	
	String getEncryptedData(String data) {
		char[] encrypted = new char[data.length()];
		int n = 0;
		
		
		for(int k = 0 ; k < numRails; k ++) {
			int index = k;
			boolean down = true;
			while(index < data.length() ) {
				//System.out.println(k + " " + index+ " "+ n );
				encrypted[n++] = data.charAt(index);
				
				if(k == 0 || k == numRails - 1) {
					index = index + 2 * (numRails - 1);
				}
				else if(down) {
					index = index +  2 * (numRails - k - 1);
					down = !down;
				}
				else {
					index = index + 2 * k;
					down = !down;
				}
			}
		}
		return new String(encrypted);
	}
	
	//alternate way not efficient
	String getEncryptedData2(String data) {

		int len = data.length();
		StringBuffer[] sb = new StringBuffer[numRails];

		for (int i = 0; i < numRails; i++) {
			sb[i] = new StringBuffer();
		}

		int index = 0;
		int direction = 1;

		for (int i = 0; i < data.length(); i++) {

			sb[index].append(data.charAt(i));
			index = index + direction;

			if (index == 0) {
				direction = 1;
			} else if (index == numRails) {
				if(index == 2) {// base case for cipher of length 2
					index = 0;
				}else {
					direction = -1;
					index = numRails -2;					
				}
			}

		}

		for (int i = 1; i < numRails; i++) {
			sb[0].append(sb[i].toString());
		}

		return sb[0].toString();
	}

	public static void RailFenceCipherstart(HashMap<String,User> users,String username) throws Exception
	{
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("1.Cipher\n2.Decipher");
        choice=sc.nextInt();
        Date d1 = new Date();
        if(choice==1)
        {
        	int depth;
            System.out.println("Enter the key:");
            depth=sc.nextInt();
    		System.out.println("Enter the line:");
            String data =sc.next();
    		RailFenceCipher railFenceCipher = new RailFenceCipher(depth);
    		String encrypted =railFenceCipher.getEncryptedData(data);
    		System.out.println("Ciphered As:"+encrypted);
    		users.get(username).cipherHistory.add("RAIL FENCE CIPHER : FROM "+data+" TO "+encrypted+" USING KEY:"+depth+" ON "+d1);
        }
        else if(choice==2)
        {
        	int depth;
            System.out.println("Enter the key:");
            depth=sc.nextInt();
    		System.out.println("Enter the line:");
            String data =sc.next();
    		RailFenceCipher railFenceCipher = new RailFenceCipher(depth);
    		String decrypted = railFenceCipher.getDecryptedData(data);
    		System.out.println("YOUR MESSAGE:"+decrypted);
    		users.get(username).deCipherHistory.add("RAIL FENCE CIPHER : FROM "+data+" TO "+decrypted+" USING KEY:"+depth+" ON "+d1);
        }
        else
        {
        	System.out.println("Put Your Brain Hacker!!");
        }
        sc.close();
	}
}
