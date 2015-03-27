import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class ResponseThread extends Thread
{
	ConnectedClient theClient;
	LinkedList<ConnectedClient> allTheClients;

	public ResponseThread(ConnectedClient theClient)
	{
		this.theClient = theClient;
		this.allTheClients = Driver.theClients;
	}
	
	public void run()
	{
		this.theClient.sendMessage("Do you want to share or download?");
		String theAnswer = this.theClient.getMessage();
		this.theClient.sendMessage(theAnswer);
		try
		{
			DataInputStream dIn = new DataInputStream(this.theClient.getSocket().getInputStream());

			int length = dIn.readInt();                    // read length of incoming message
			byte[] message = null;
			if(length>0) 
			{
				message = new byte[length];
				dIn.readFully(message, 0, message.length); // read the message
			}
			/*
			String extension = "";
			for(int i = 0; i < 200; i++)
			{
				extension += message[i];
			}
			*/
			Random r = new Random(); //Just for now in case you want to send two files
			File theClone = new File("C:/Users/Abe/Documents/GitHub/CSC440_bitzServer/myFiles/clone" + r.nextInt());
			FileOutputStream fos = new FileOutputStream(theClone);
			fos.write(message);
			fos.close();
		}
		catch(Exception ex)
		{
			
		}
	}
}
