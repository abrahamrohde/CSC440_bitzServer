import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ConnectedClient 
{
	private Socket theSocket;
	private PrintWriter output;
	private Scanner input;
	
	public ConnectedClient(Socket theSocket)
	{
		try
		{
			this.theSocket = theSocket;
			this.output = new PrintWriter(this.theSocket.getOutputStream(), true);
			this.input = new Scanner(this.theSocket.getInputStream());
		}
		catch(Exception e)
		{
			 //yea right
		}
	}
	
	public void sendMessage(String msg)
	{
		this.output.println(msg);
	}
	
	public String getMessage()
	{
		return this.input.nextLine();
	}
	public Socket getSocket()
	{
		return this.theSocket;
	}
}
