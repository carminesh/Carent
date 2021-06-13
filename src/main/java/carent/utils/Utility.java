package carent.utils;

public class Utility {
	//in questa classe useremo metodi utili statici, funzioni di supporto
	
	public static void print (String... messages) {
		String message = "";
		for (String s: messages) {
			message+=s+"\n";
		}
		System.out.printf("%s", message);
	}
	
	public static void print (Exception exception) {
		Utility.print("EXCEPTION: "+exception.getMessage());
		exception.printStackTrace();
	}
}
