import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;


public class Driver 
{
	static LinkedList<ConnectedClient> theClients = new LinkedList<ConnectedClient>();
	static LinkedList<ConnectedClient> theBusyClients = new LinkedList<ConnectedClient>();
	static LinkedList<ConnectedClient> theNotBusyClients = new LinkedList<ConnectedClient>();
	
	public static void main(String[] args) throws Exception
	{
		//******Server Code******
		ServerSocket ss = new ServerSocket(1234);
		while(true)
		{
			System.out.println("Waiting...");
			Socket connection = ss.accept();
			ConnectedClient cc = new ConnectedClient(connection);
			Driver.theClients.add(cc);
			Driver.theNotBusyClients.add(cc);
			ResponseThread rt = new ResponseThread(cc);
			rt.start();
			System.out.println("Connected!");
			
		}
	}
}
