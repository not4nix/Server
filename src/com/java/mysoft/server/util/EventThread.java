package com.java.mysoft.server.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.java.mysoft.server.util.httputils.HTTPResponse;
import com.java.mysoft.server.util.logging.Logger;

public class EventThread implements Runnable{
	
	private static PrintWriter pw;
	static Queue<HTTPResponse> events = new LinkedList<HTTPResponse>();
	static ArrayList<EventTable> online_list = new ArrayList<EventTable>();
	@Override
	public synchronized void run() 
	{
		Iterator iter = events.iterator();
		while(true)
		{
			//If iterator has events
			if(iter.hasNext())
			{
				try
				{
					int idArray = getIdArray(events.peek().getMailto());
					OutputStream output = online_list.get(idArray).s.getOutputStream();
					pw = new PrintWriter(output,true);
					events.poll();
				}
				catch(Exception e)
				{
                                  Logger.writeToFile("Exception occured " + ex.toString());
                                }      
			}
			try 
			{
				Thread.currentThread();
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				Logger.writeToFile(e.toString());
			}
		}
		
	}
	private synchronized int getIdArray(int TableId)
	{
		for(int i = 0;i < online_list.size();i++)
		{
			if(online_list.get(i).idTable == TableId)
			{
				return online_list.get(i).idArray;
			}
		}
		return 0;
	}
	public synchronized static void addEvent(HTTPResponse httpEvent,int mailto)
	{
		httpEvent.setMailto(mailto);
		
		events.add(httpEvent);
	}
	public synchronized static void addUserToOnLine(int id,Socket s) throws IOException
	{
		online_list.add(new EventTable(id,s));
		online_list.get(online_list.size()-1).setIdArray(online_list.size()-1);
		OutputStream output = s.getOutputStream();
		pw = new PrintWriter(output,true);
		Logger.writeToFile("User added to online");
	}
}

class EventTable {
	public int idTable;
	public int idArray;
	public Socket s;
	public EventTable(int idTable,int idArray,Socket s)
	{
		this.idArray = idArray;
		this.idTable = idTable;
		this.s = s;
	}
	public EventTable(int idTable,Socket s)
	{
		this.idArray = -111;
		this.idTable = idTable;
		this.s = s;
	}
	public EventTable(int idTable)
	{
		this.idArray = -111;
		this.idTable = idTable;
	}
	public int getIdArray()
	{
		return idArray;
	}
	public int getIdTable()
	{
		return idArray;
	}
	
	public void setIdArray(int idArray)
	{
		this.idArray = idArray;
	}
	public void setIdTable(int idTable)
	{
		this.idTable = idTable;
	}
}

class EventID {
	public int idTable;
	public int idArray;
	public EventID(int idTable,int idArray)
	{
		this.idArray = idArray;
		this.idTable = idTable;
	}
	public EventID(int idTable)
	{
		this.idArray = -111;
		this.idTable = idTable;
	}
	public int getIdArray()
	{
		return idArray;
	}
	public int getIdTable()
	{
		return idArray;
	}
	
	public void setIdArray(int idArray)
	{
		this.idArray = idArray;
	}
	public void setIdTable(int idTable)
	{
		this.idTable = idTable;
	}
}
