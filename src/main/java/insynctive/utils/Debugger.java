package insynctive.utils;

public class Debugger {

	public static void log(Object message){
		markof(message,'#');
		System.out.println("#"+message+"#");
		markof(message,'#');
	}

	public static void subLog(Object message){
		markof(message, '_');
		System.out.println("|"+message+"|");
		markof(message, '¯');
	}
	
	private static void markof(Object message, char character) {
		System.out.print(character);
		for(int i = 0 ; i < message.toString().length(); i++){
			System.out.print(character);
		}
		System.out.println(character);
	}
	
}
