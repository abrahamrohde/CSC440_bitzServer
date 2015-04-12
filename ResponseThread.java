import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;


public class ResponseThread extends Thread
{
	ConnectedClient theClient;
	LinkedList<ConnectedClient> allTheClients;
	private static String theFileName = "";
	private static int theSize = -1;
	
	public ResponseThread(ConnectedClient theClient)
	{
		this.theClient = theClient;
		this.allTheClients = Driver.theClients;
	}
	
	public void run()
	{
		//is the client sharing or getting?
		String clientMode = this.theClient.getMessage();
		
		if(clientMode.equals("share"))
		{
			//Wait for the client to tell us the name of the file he is about to send
			ResponseThread.theFileName = this.theClient.getMessage();
			ResponseThread.theSize = Integer.parseInt(this.theClient.getMessage());
			this.theClient.initBytes(theSize, true);
			System.out.println("Sharing Mode");
			
			//we need to request bytes from our connectClient that
			//other connected clients need
			//*****Write Code HERE****
		}
		else
		{
			//do get stuff
			System.out.println("Getting Mode");
			
			//send the client the name of the file
			this.theClient.sendMessage(ResponseThread.theFileName);
			this.theClient.sendMessage("" + ResponseThread.theSize);
			this.theClient.initBytes(theSize, false);
			
			//wait for client requests for receiving a byte
			String whichByte = this.theClient.getMessage();
			for(ConnectedClient cc : Driver.theNotBusyClients)
			{
				if(cc != this.theClient && cc.hasByte(Integer.parseInt(whichByte)))
				{
					//share the byte with this guy
				}
			}
			
		}
	}
}
