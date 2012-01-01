package com.java.server.models;

import java.util.Date;

public class Chat {
	/**
	 * private fields
	 */
	private String _info;
	private String _roomname;
	private String _creatorName;
	private Date _creationDate;
	private int _roomId;
	
	public Chat(String inf, String rname, String cname, Date cdate, int roomId){
		_info = inf;
		_roomname = rname;
		_creatorName = cname;
		_creationDate = cdate;
		_roomId = roomId;
	}
}
