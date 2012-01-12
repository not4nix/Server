package com.java.mysoft.server.util;

import java.io.Serializable;

public enum ResponseCodes implements Serializable{
	    //user response codes
		UserAdded,
		UserCreated,
		UserDeleted,
		UserUpdated,
		UserExists,
		UserFound,
		UserJoined,
		UserLoggedOn,
		UserLoggedOff,
		UserOnline,
		UserOffline,
		UserPasswordChanged,
		UserLoginChanged,
		//friendship response codes
		FriendshipCreated,
		FriendshipRefused,
		FriendshipTerminated,
		FriendFound,
		//messages response codes
		MessageFound,
		MessageSent,
		MessageReceived,
		MessageDeleted,
		//chat response codes
		UsersOnlineFound,
		RoomCreatorFound,
		RoomCreated,
		RoomDeleted,
		UserAddedToRoom,
		UserKicked,
		//others response codes
		BadMethod,
		BadRequest,
		NotFound,
		Forbidden;
}
