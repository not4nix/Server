package com.java.server.gateways;

public abstract class TableGateway {
	/**
     * If we change table name in our database we'll need change only one 
     * string in our project
     * e.g if we change in mysql/mssql/postgresql database table name from user_tbl to users
     * we'll need change string in this class. Old string will be user_tbl
     * new string will be users
     * 
     * e.g. new string: protected String userTableName = "users";
     */
	private String _usersTable = "users_tbl";
	private String _friendsTable = "friendship_tbl";
	private String _messageTable = "message_tbl";
	private String _chatTable = "chat_tbl";
	private String _membersTable = "members_tbl";
	private String _chatUserTable = "chatuser_tbl";
	
	protected TableGateway(String users, String friends, String messages, String chat, 
			String membership, String chatUser){
		_usersTable = users;
		_friendsTable = friends;
		_messageTable = messages;
		_chatTable = chat;
		_membersTable = membership;
		_chatUserTable = chatUser;
	}
	
	protected String getUserTable(){
		return _usersTable;
	}
	
	protected String getFriendTable(){
		return _friendsTable;
	}
	
	protected String getMessageTable(){
		return _messageTable;
	}
	
	protected String getChatTable(){
		return _chatTable;
	}
	
	protected String getMembersTable(){
		return _membersTable;
	}
	
	protected String getChatUserTable(){
		return _chatUserTable;
	}
}
