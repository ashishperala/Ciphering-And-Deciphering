package sherlock;
import java.io.*;
import java.util.HashMap;
import java.util.Stack;
public class Sherlock {
	static User CurrentUser;
	static void login(HashMap<String,User> users) throws Exception {
		System.out.println("ENTER YOUR USER NAME");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String userName;
		while(true) {
			userName=br.readLine();
			if(!users.containsKey(userName)) {
				System.out.println("USERNAME DOES NOT EXISTS REENTER USERNAME");
			}
			else {
				CurrentUser.userName=userName;
				break;
			}
		}
		System.out.println("ENTER YOUR PASSWORD");
		String password;
		while(true) {
			password=br.readLine();
			if(password.compareTo((users.get(CurrentUser.userName)).password)!=0) {
				System.out.println("WRONG PASSWORD PLEASE REENTER PASSWORD");
			}
			else {
				System.out.println("LOGIN SUCCESSFUL...WELCOME BACK");
				CurrentUser.password=password;
				break;
			}
		}
	}
	static void signUp(HashMap<String,User> users) throws Exception {
		Stack<String> h=new Stack<>();
		Stack<String> h1=new Stack<>();
		System.out.println("ENTER USER NAME");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String userName;
		while(true) {
			userName=br.readLine();
			if(users.containsKey(userName)) {
				System.out.println("USERNAME ALREADY EXISTS PLEASE TRY ANOTHER ONE");
			}
			else {
				CurrentUser.userName=userName;
				break;
			}
		}
		System.out.println("ENTER YOUR PASSWORD");
		String password=br.readLine();
		CurrentUser.password=password;
		User CurrentUserObj=new User(userName,password, h,h1);
		users.put(CurrentUserObj.userName, CurrentUserObj);
		try
        {    
            FileOutputStream f = new FileOutputStream("C:\\Users\\ashup\\eclipse-workspace\\classwork\\src\\classwork\\userinfo.txt");
            ObjectOutputStream out = new ObjectOutputStream(f);
            // Method for serialization of object 
            out.writeObject(users); 
            out.close(); 
            f.close(); 
            //System.out.println("Object has been serialized");
        }
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        }	
		//CurrentUser.history=new Stack<String>();
	}
	static void changePassword(HashMap<String,User> users) throws Exception{
		System.out.println("ENTER YOUR CURRENT PASSWORD");
		String password;
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			password=br.readLine();
			if(password.compareTo((users.get(CurrentUser.userName)).password)!=0) {
				System.out.println("WRONG PASSWORD PLEASE REENTER PASSWORD");
			}
			else {
				System.out.println("ENTER YOUR NEW PASSWORD");
				password=br.readLine();
				users.get(CurrentUser.userName).password=password;
				CurrentUser.password=password;
				System.out.println("PASSWORD CHANGED ");
				break;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		CurrentUser=new User();
		System.out.println("TO LOGIN PRESS 1 TO SIGNUP PRESS 2");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int choice=Integer.parseInt(br.readLine());
		HashMap<String,User> users=new HashMap<>();
		try
        {    
            FileInputStream file = new FileInputStream("C:\\Users\\ashup\\eclipse-workspace\\classwork\\src\\classwork\\userinfo.txt"); 
            ObjectInputStream in = new ObjectInputStream(file); 
            users= (HashMap<String,User>) in.readObject(); 
            in.close(); 
            file.close();
        }
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught..........."); 
        }
		if(choice==1) {
			login(users);
		}
		else if(choice==2) {
			signUp(users);
			//System.out.println(users);
			//System.out.println(CurrentUser.userName+CurrentUser.password);
		}
		else {
			System.out.println("ENTER CORRECT CHOICE");
			main(null);
		}		
		do {
			System.out.println("CHOOSE YOUR MODE OF ACTION AND PRESS RESPECTIVE NUMBERS:\n1.ENTER ENCRYPTION AND DECRTPTION MODE\n2.CHECK YOUR PREVIOUS HISTORY\n3.CLEAR YOUR HISTORY\n4.CHANGE YOUR PASSWORD\n5.LOG OUT");
			choice=Integer.parseInt(br.readLine());
			switch(choice) {
			case 1:
				System.out.println("WHICH METHOD DO YOU LIKE TO USE\n1.CAESAR CIPHER\n2.HILL CIPHER\n3.PLAY FAIR CIPHER\n4.RAIL FENCE CIPHER\n5.VIGENERE CIPHER");
				
				int choice2=Integer.parseInt(br.readLine());
				switch(choice2) {
				case 1:
					CaesarCipher.CaesarCipherstart(users,CurrentUser.userName);
					break;
				case 2:
					HillCipher.HillCipherStart(users,CurrentUser.userName);
					break;
				case 3:
					PlayFairCipher.playFairCipherstart(users,CurrentUser.userName);
					break;
				case 4:
					RailFenceCipher.RailFenceCipherstart(users,CurrentUser.userName);
					break;
				case 5:
					VigenereCipher.VigenereCipherstart(users,CurrentUser.userName);
					break;
				default:
					System.out.println("\nWRONG CHOICE YOU WERE SUPPOSED TO ENTER A NUMBER BELOW 5");
				}
				break;
			case 2:
				System.out.println("WHAT DO YOU LIKE TO SEE:\n1.CIPHER MODE HISTORY\n2.DECIPHER MODE HISTORY");
				choice2=Integer.parseInt(br.readLine());
				if(choice2==1) {
					users.get(CurrentUser.userName).showCipherHistory();
				}
				else if(choice2==2) {
					users.get(CurrentUser.userName).showDeCipherHistory();
				}
				else {
					System.out.println("WRONG CHOICE YOU WERE SUPPOSED TO ENTER 1 OR 2");
				}
				break;
			case 3:
				System.out.println("WHAT HISTORY DO YOU LIKE TO CLEAR:\n1.CIPHER MODE HISTORY\n2.DECIPHER MODE HISTORY");
				choice2=Integer.parseInt(br.readLine());
				if(choice2==1) {
					users.get(CurrentUser.userName).clearCipherHistory();
				}
				else if(choice2==2) {
					users.get(CurrentUser.userName).clearDeCipherHistory();
				}
				else {
					System.out.println("WRONG CHOICE YOU WERE SUPPOSED TO ENTER 1 OR 2 ");
				}
				break;
			case 4:
				System.out.println(users);
				changePassword(users);
				break;
			case 5:
				System.out.println("LOG OUT SUCCESSFUL");
				break;
			default:
				System.out.println("\nENTER CORRECT NUMBER");
			}
		} while(choice!=5);
		try
	    {    
	            FileOutputStream f = new FileOutputStream("C:\\Users\\ashup\\eclipse-workspace\\classwork\\src\\classwork\\userinfo.txt");
	            ObjectOutputStream out = new ObjectOutputStream(f);
	            // Method for serialization of object 
	            out.writeObject(users); 
	            out.close(); 
	            f.close(); 
	            //System.out.println("Object has been serialized");
	     }
	     catch(IOException ex) 
	     { 
	            System.out.println("IOException is caught"); 
	     }
	}
	
}