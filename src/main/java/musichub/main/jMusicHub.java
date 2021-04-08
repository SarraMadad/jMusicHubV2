package musichub.main;

import java.util.Scanner;

/**
 * jMusicHub contains the main method for the program.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see StartServer
 * @see StartClient
 */
public class jMusicHub {
	/**
	 * Default constructor. The user decides to launch or not the server
	 */
	public jMusicHub() {
		System.out.println("\nBienvenue dans jMusicHub !");
		System.out.println("\nSouhaitez-vous démarrer le serveur ? [O/N]");

		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine().toUpperCase();
		if(userInput.equals("O") || userInput.equals("Y") || userInput.equals("OUI") || userInput.equals("YES")) {
			System.out.println("\nDémarrage du serveur...");
			System.out.println("Pour démarrer le client, ouvrez un nouveau terminal et entrez la commande :");
			System.out.println("java -cp jMusicHubV2-1.0-SNAPSHOT.jar musichub.main.StartClient");
			System.out.println("\n\n");

			new StartServer();
		} else {
			System.out.println("Si vous souhaitez démarrer le serveur dans un nouveau terminal, entrez la commande suivante :");
			System.out.println("java -cp jMusicHubV2-1.0-SNAPSHOT.jar musichub.main.StartServer");
			System.out.println("Pour allumer le client, lancez le serveur puis ouvrez un nouveau terminal et entrez la commande :");
			System.out.println("java -cp jMusicHubV2-1.0-SNAPSHOT.jar musichub.main.StartClient");
			System.out.println("A bientôt dans jMusicHub !");
		}
	}

	/**
	 * Main entry of the program.
	 * @param args command arguments
	 */
	public static void main(String[] args) {
		new jMusicHub();
	}
}
