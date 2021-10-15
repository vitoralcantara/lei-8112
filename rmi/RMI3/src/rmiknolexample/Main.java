package rmiknolexample;

/**
 * 
 * @author Andre
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		if (args[0].equals("server"))
			ForumService.main(args);
		else if (args[0].equals("client"))
			ForumClient.main(args);
		else
			System.err.println("Usage: ");
	}
}