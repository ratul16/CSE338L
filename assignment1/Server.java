import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

	private static Integer port = 8088;
	private static Double versionNumber = 1.0;
	private static String welcomeMsg = "--- Welcome to CSE338Lab Game Server ---" + versionNumber + " --- \n";
	private static boolean validPort(Integer x) {
		return x >= 1 && x <= 65535 ? true : false;
	}
	private static int getPort() {

		Integer input;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Please select a port by entering an integer value between 1 and 65535 or\n");
			System.out.print("insert \"0\" in order to continue with the default setting (" + Server.port + "): ");
			input = sc.nextInt();

		} while (input != 0 && !Server.validPort(input));

		sc.close();

		return input == 0 ? Server.port : input;
	}

	public static void main(String args[]) throws Exception {

		String resClient_1 = "";
		String resClient_2 = "";
		String inputClient_1;
		String inputClient_2;

		System.out.println(Server.welcomeMsg);
		Server.port = Server.getPort();
		ServerSocket welcomeSocket = new ServerSocket(Server.port);
		System.out.println("\nOk, we're up and running on port " + welcomeSocket.getLocalPort() + " ...");

		while (!welcomeSocket.isClosed()) {

			Socket client_1 = welcomeSocket.accept();
			if (client_1.isConnected()) {
				System.out.println("\nPlayer 1 (" + (client_1.getLocalAddress().toString()).substring(1) + ":"
						+ client_1.getLocalPort() + ") on the floor ... waiting for player 2 ...");
			}
			DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
			BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

			Socket client_2 = welcomeSocket.accept();
			if (client_2.isConnected()) {
				System.out.println("Player 2 (" + (client_2.getLocalAddress().toString()).substring(1) + ":"
						+ client_1.getLocalPort() + ") on the floor ... lets start ...");
			}
			DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
			BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

			inputClient_1 = inClient_1.readLine();
			inputClient_2 = inClient_2.readLine();


			if (inputClient_1.equals(inputClient_2)) {
				resClient_1 = "Draw";
				resClient_2 = "Draw";
				System.out.println("It's a draw.");
			}

			else if (inputClient_1.equals("1") && inputClient_2.equals("3")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");

			}

			else if (inputClient_1.equals("3") && inputClient_2.equals("1")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}

			else if (inputClient_1.equals("1") && inputClient_2.equals("2")) {
				resClient_1 = "You lose";
				resClient_2 = "You win";
				System.out.println("Player two wins.");
			}

			else if (inputClient_1.equals("2") && inputClient_2.equals("1")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");
			}

			else if (inputClient_1.equals("3") && inputClient_2.equals("2")) {
				resClient_1 = "You win";
				resClient_2 = "You lose";
				System.out.println("Player one wins.");
			}

			else if (inputClient_1.equals("2") && inputClient_2.equals("3")) {
				resClient_1 = "You lost the game";
				resClient_2 = "You won the game";
				System.out.println("Both players wins.");
			}

			outClient_1.writeBytes(resClient_1.toUpperCase());
			outClient_2.writeBytes(resClient_2.toUpperCase());
			client_1.close();
			client_2.close();

			System.out.println("\nWaiting for new players ...\n");

		}
	}
}