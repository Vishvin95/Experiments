package com.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer {
	ArrayList<PrintWriter> clientOutputStreams;
	
	public static void main(String[] args)
	{
		new ChatServer().go();
	}
	
	private void go()
	{
		clientOutputStreams = new ArrayList<>();
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				clientOutputStreams.add(new PrintWriter(clientSocket.getOutputStream()));
				
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println(clientSocket.getInetAddress() + " is connected");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public class ClientHandler implements Runnable
	{
		BufferedReader reader;
		Socket socket;
		
		public ClientHandler(Socket clientSocket) {
			socket = clientSocket;
			try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String message;
			try {
				while((message = reader.readLine())!=null)
				{
					System.out.println("read " + message);
					tellEveryone(message);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void tellEveryone(String message)
	{
		Iterator<PrintWriter> it = clientOutputStreams.iterator();
		while(it.hasNext())
		{
			PrintWriter writer = it.next();
			writer.println(message);
			writer.flush();
		}
	}
}
