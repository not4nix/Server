package com.java.mysoft.server.util.xmlutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.text.AsyncBoxView.ChildLocator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.java.mysoft.server.util.logging.Logger;

public class XMLWrapper {
	
	private String xml;
	private static volatile XMLWrapper instance;
	
	private XMLWrapper(){
		
	}
	
	public static XMLWrapper getInstance(){
		if(instance == null){
			synchronized(XMLWrapper.class){
				if(instance == null){
					instance = new XMLWrapper();
				}
			}
		}
		return instance;
	}
	//create xml document
	/**
	 * Example:
	 * <?xml version="1.0" encoding="UTF-8" standalone="no" ?>
	 * <user>
	 *   <userId="1">
	 *      <login>Vasiliy</login>
	 *      <password>qwerty12345</password>
	 *      <email>vasiliy@mail.ru</email>
	 *      <id>1</id>
	 *   </userId>
	 * </user>
	 * @param rootElement rootelement
	 * @param childElement childelement
	 * @param rs resultset
	 * @return xml and may save into temp file
	 */
	public synchronized String createXMLDocument(String rootElement, String childElement, ResultSet rs){
		try {
			OutputStream os = new ByteArrayOutputStream();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//root elements
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("user");
			doc.appendChild(root);
			//child elements
			Element child = doc.createElement("userId");
			root.appendChild(child);
			Element login = doc.createElement("login");
			login.appendChild(doc.createTextNode(rs.getString("login")));
			child.appendChild(login);
			Element password = doc.createElement("password");
			password.appendChild(doc.createTextNode(rs.getString("password")));
			child.appendChild(password);
			Element email = doc.createElement("email");
			email.appendChild(doc.createTextNode(rs.getString("email")));
			child.appendChild(email);
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(rs.getString("id")));
			child.appendChild(id);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File("D:\\Server\\file.xml"));
			transformer.transform(source, result);
			Logger.writeToFile("File created");
			xml = os.toString();
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured while creating XML Document " + ex.toString());
		}
		return xml;
	}
	
	/**
	 * Example:
	 * <?xml version="1.0" encoding="UTF-8" standalone="no" ?>
	 * <chat>
	 *   <chatroomId=1">
	 *       <user>login</user>
	 *       <userId>id</userId>
	 *       <message>message</message>
	 *       <messageId>messageId</messageId>
	 *   </chatRoomId>
	 * </chat>
	 * @param users
	 * @param messages
	 */
	public synchronized void createXMLDocumentForChat(ResultSet users, ResultSet messages){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("chat");
			doc.appendChild(root);
			Element child = doc.createElement("chatRoomId");
			root.appendChild(child);
			Element user = doc.createElement("user");
			user.appendChild(doc.createTextNode(users.getString("login")));
			child.appendChild(user);
			Element userId = doc.createElement("userId");
			userId.appendChild(doc.createTextNode(users.getString("userId")));
			child.appendChild(userId);
			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode(messages.getString("message")));
			child.appendChild(message);
			Element messageId = doc.createElement("messageId");
			messageId.appendChild(doc.createTextNode(messages.getString("messageId")));
			child.appendChild(messageId);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File("D:\\Server\\chat.xml"));
			transformer.transform(source, result);
			Logger.writeToFile("File created");
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	/**
	 * Example:
	 * <?xml version="1.0" encoding="UTF-8" standalone="no" ?>
	 * <chat>
	 *     <chatRoomId>chatRoomId</chatRoomId>
	 *     <message>message</message>
	 *     <userId>userId</userId>
	 * </chat>
	 * @param chatRoomId
	 * @param message
	 * @param userId
	 */
	public synchronized void createXMLForRoomMessages(int chatRoomId, String message, int userId){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("chat");
			doc.appendChild(root);
			Element chatRoom = doc.createElement("chatRoomId");
			chatRoom.appendChild(doc.createTextNode("chatRoomId"));
			root.appendChild(chatRoom);
			Element messages = doc.createElement("message");
			messages.appendChild(doc.createTextNode("message"));
			root.appendChild(messages);
			Element usId = doc.createElement("userId");
			usId.appendChild(doc.createTextNode("userId"));
			root.appendChild(usId);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File("D:\\Server\\roommessages.xml"));
			transformer.transform(source, result);
			Logger.writeToFile("File created");
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	/**
	 * Example
	 * <?xml version="1.0" encoding="UTF-8" standalone="no" ?>
	 * <chat>
	 *    <chatRoomId="?">
	 *       <info>info</info>
	 *       <roomname>roomname</roomname>
	 *       <creatorName>creatorName</creatorName>
	 *       <creationDate>creationDate</creationDate>
	 *    </chatRoomId>
	 * </chat>
	 * 
	 * <chat>
	 *   <chatRoomId="1">
	 *      <info>Development</info>
	 *      <roomname>Java</roomname>
	 *      <creatorName>username</creatorName>
	 *      <creationDate>Wed 11 Jan 17:00:22 EET 2012</creationDate>
	 *  </chatRoomId>
	 * </chat>
	 * @param roomname
	 * @param chatRoomId
	 */
	public synchronized void createXMLNewRoom(String info, String roomname, String creatorName, String creationDate, int chatRoomId){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("chat");
			doc.appendChild(root);
			Element child = doc.createElement("chatRoomId");
			root.appendChild(child);
			Element inf = doc.createElement("info");
			inf.appendChild(doc.createTextNode("info"));
			child.appendChild(inf);
			Element room = doc.createElement("roomname");
			room.appendChild(doc.createTextNode("roomname"));
			child.appendChild(room);
			Element creator = doc.createElement("creatorName");
			creator.appendChild(doc.createTextNode("creatorName"));
			child.appendChild(creator);
			Element cdate = doc.createElement("creationDate");
			cdate.appendChild(doc.createTextNode("creationDate"));
			child.appendChild(cdate);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File("D:\\Server\\newroom.xml"));
			transformer.transform(source, result);
			Logger.writeToFile("File created");
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void CreateXMLUserJoined(ResultSet user){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("newUser");
			doc.appendChild(root);
			while(user.next()){
				Element id = doc.createElement("userId");
				id.appendChild(doc.createTextNode(user.getString("userId")));
				root.appendChild(id);
				Element login = doc.createElement("login");
				login.appendChild(doc.createTextNode(user.getString("login")));
				root.appendChild(login);
			}
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File("D:\\Server\\newuser.xml"));
			transformer.transform(source, result);
			Logger.writeToFile("File created");
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	public synchronized void CreateXMLNewMessage(int chatRoomId, int userId, String message){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement("newMessage");
			doc.appendChild(root);
			Element room = doc.createElement("chatRoomId");
			room.appendChild(doc.createTextNode("chatRoomId"));
			root.appendChild(room);
			Element msg = doc.createElement("message");
			msg.appendChild(doc.createTextNode("message"));
			root.appendChild(msg);
			Element id = doc.createElement("userId");
			id.appendChild(doc.createTextNode("userId"));
			root.appendChild(id);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File("D:\\Server\\newmessage.xml"));
			transformer.transform(source, result);
			Logger.writeToFile("File created");
		}
		catch(Exception ex){
			Logger.writeToFile("Exception occured " + ex.toString());
		}
	}
	
	//parser
	public synchronized void parse(ConcurrentHashMap<String,String> map, String source){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			byte[] bytes = source.getBytes("UTF-8");
			InputStream is = new ByteArrayInputStream(bytes);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList child = root.getChildNodes();
			for(int i = 0; i < child.getLength(); i++){
				Node thisNode = root.getChildNodes().item(i);
				if(thisNode instanceof Element){
					Element children = (Element)thisNode;
					Text textNode = (Text)children.getFirstChild();
					String node = children.getNodeName();
					if(textNode != null){
						String text = textNode.getData().trim();
						map.put(node, text);
					}
				}
			}
		}
		catch(Exception ex){
			Logger.writeToFile("Occured " + ex.toString());
		}
	}
}