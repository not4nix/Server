package com.java.server.models;

public class Friendship {
	/**
	 * private fields
	 */
	private String _friendName;
	private String _initiatorName;
	private int _friendshipId;
	
	
	public Friendship(String friend, String initiator, int friendshipId){
		_friendName = friend;
		_initiatorName = initiator;
		_friendshipId = friendshipId;
	}
}
