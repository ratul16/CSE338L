import java.io.*;
import java.net.*;

class Client {

   
    private static String host = "localhost";
    private static Integer port = 8088;
    private static Double versionNumber = 1.0;
    private static String msgWelcome = "--- Welcome to Paper Scissors Stone V. "
	    + versionNumber + " --- \n";

    private static String msgRules = "\nRule set:\n - (R)ock beats (S)cissors\n - (S)cissors beats (P)aper\n - (P)aper beats (R)ock\n";

    public static void main(String args[]) throws Exception {

	String input = "";
	String response;

	System.out.println(Client.msgWelcome);

	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
		System.in));
	Socket clientSocket = new Socket(Client.host, Client.port);
	DataOutputStream outToServer = new DataOutputStream(
		clientSocket.getOutputStream());
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
		clientSocket.getInputStream()));

	do {

	    if (input.equals("-rules")) {
		System.out.println(Client.msgRules);
	    }

	    System.out
		    .println("Start the game by selecting Rock-1, Paper-2, Scissors-3");
	    System.out.print("or type \"-rules\" in order to see the rules: ");
	    input = inFromUser.readLine();

	} while (!input.equals("1") && !input.equals("2") && !input.equals("3"));

	outToServer.writeBytes(input + "\n");
	System.out
		.println("\nYour input ("
			+ input
			+ ") was successfully transmitted to the server.");

	response = inFromServer.readLine();
	System.out.println("Response from server: " + response);
	clientSocket.close();

    }
}