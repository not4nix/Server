package com.java.server.utils;

import java.io.Serializable;

public enum ResponseCodes implements Serializable{
	UserAdded,
    UserDeleted,
    UserUpdated,
    UserExists,
    UserNotFound,
    UserLoggedOut,
    UserOnline,
    UserLoggedOn,
    FriendAdded,
    FriendUpdated,
    FriendDeleted,
    FriendshipAccepted,
    FriendshipRefused,
    FriendshipTerminated,
    FriendBlacklisted,
    FriendNotFound,
    FriendExists,
    RoomAdded,
    RoomUpdated,
    RoomDeleted,
    ChatUserAdded,
    UserJoined,
    UserLeft,
    UserKickedOut,
    MemberAdded,
    MessageSentIntoChat,
    MessageSentToUser,
    MessageAdded,
    MessageUpdated,
    MessageDeleted,
    BadRequest,
	Forbidden;
}