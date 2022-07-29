package sherlock;
import java.util.*;
public class User implements java.io.Serializable{
	String userName;
	String password;
	Stack<String> cipherHistory;
	Stack<String> deCipherHistory;
	User(String userName,String password,Stack<String> cipherHistory,Stack<String> deCipherHistory){
		this.userName=userName;
		this.password=password;
		this.cipherHistory=cipherHistory;
		this.deCipherHistory=deCipherHistory;
	}
	User(){
	
	}
	void showCipherHistory() {
		Iterator<String> itr=this.cipherHistory.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	void showDeCipherHistory() {
		Iterator<String> itr=this.deCipherHistory.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	void clearCipherHistory() {
		Iterator<String> itr=this.cipherHistory.iterator();
		while(itr.hasNext()) {
			this.cipherHistory.pop();
		}
	}
	void clearDeCipherHistory() {
		Iterator<String> itr=this.deCipherHistory.iterator();
		while(itr.hasNext()) {
			this.deCipherHistory.pop();
		}
	}
	public String toString() {
		return " UserName-"+this.userName+" Password-"+this.password;
	}
}