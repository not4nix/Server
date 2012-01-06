package com.java.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLWrapper {
	
	private Element child;
	private Text textNode;
	private String xml;
	private Document doc;
	
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
	
	
	/**
	 * Create XML document
	 * @param rootElement root element
	 * @param childElement child element
	 * @param rs ResultSet
	 * @return xml
	 */
	public String createXDocument(String rootElement, String childElement, ResultSet rs){
		try {
			OutputStream os = new ByteArrayOutputStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			Element root = doc.createElement(rootElement);
			doc.appendChild(root);
			while(rs.next()){
				child = doc.createElement(childElement);
				root.appendChild(child);
				Element m_childElement = doc.createElement("login");
				textNode = doc.createTextNode(rs.getString("login"));
				Element x_childElement = doc.createElement("password");
				Text x_textNode = doc.createTextNode(rs.getString("password"));
				Element y_childElement = doc.createElement("email");
				Text y_textNode = doc.createTextNode(rs.getString("email"));
				Element z_childElement = doc.createElement("id");
				Text z_textNode = doc.createTextNode(rs.getString("id"));
				child.appendChild(m_childElement);
				m_childElement.appendChild(textNode);
				child.appendChild(x_childElement);
				x_childElement.appendChild(x_textNode);
				child.appendChild(y_childElement);
				y_childElement.appendChild(y_textNode);
				child.appendChild(z_childElement);
				z_childElement.appendChild(z_textNode);
				root.appendChild(child);
			}
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os)));
			xml = os.toString();
		}
		catch(ParserConfigurationException ex){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (TransformerConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (TransformerFactoryConfigurationError e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (TransformerException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (SQLException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		}
		return xml;
	}
	
	/**
	 * Create XML document for chat room
	 * @param u User's resultset
	 * @param m Message's resultset
	 * @return xml
	 */
	public String createXMLDocument(ResultSet u, ResultSet m){
		try {
			OutputStream os = new ByteArrayOutputStream();
			File f = new File("D:\\Server\\messages.xml");
			if(!f.exists()){
				f.createNewFile();
			}
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();
			//create element in xml
			Element root = doc.createElement("chat");
			doc.appendChild(root);
			//create element in xml
			child = doc.createElement("users");
			root.appendChild(child);
			Node u_child = null;
			while(u.next()){
				child = doc.createElement("user");
				u_child.appendChild(child);
				Element idElement = doc.createElement("id");
				textNode = doc.createTextNode(u.getString("id"));
				Element loginElement = doc.createElement("login");
				Text l_textNode = doc.createTextNode(u.getString("login"));
				child.appendChild(u_child);
				u_child.appendChild(textNode);
				child.appendChild(loginElement);
				loginElement.appendChild(l_textNode);
				u_child.appendChild(child);
			}
			Node m_child = doc.createElement("messages");
			root.appendChild(m_child);
			while(m.next()){
				child = doc.createElement("messages");
				m_child.appendChild(child);
				Element userId = doc.createElement("userId");
				textNode = doc.createTextNode(m.getString("userId"));
				Element messageElement = doc.createElement("message");
				Text m_textNode = doc.createTextNode(m.getString("message"));
				child.appendChild(userId);
				userId.appendChild(textNode);
				child.appendChild(messageElement);
				messageElement.appendChild(m_textNode);
				m_child.appendChild(child);
			}
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(f)));
			transform.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os)));
			xml = os.toString();
		}
		catch(IOException ex){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (TransformerConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (TransformerFactoryConfigurationError e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (TransformerException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (SQLException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		}
		return xml;
	}

	/**
	 * Create XML document for room messages
	 * @param roomId id of the room
	 * @param message message text
	 * @param userId user's id
	 * @return xml
	 */
	public String createXMLRDocument(int roomId, String message, int userId){
		try {
			OutputStream os = new ByteArrayOutputStream();
			File f = new File("D:\\Server\\new_room_messages.xml");
			if(!f.exists()){
				f.createNewFile();
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("RoomMessage");
			doc.appendChild(root);
			Element idElement = doc.createElement("roomId");
			textNode = doc.createTextNode(Integer.toString(roomId));
			Element messageElement = doc.createElement("message");
			Text msg_Node = doc.createTextNode(message);
			Element useridElement = doc.createElement("userId");
			Text i_Node = doc.createTextNode(Integer.toString(userId));
			root.appendChild(idElement);
			idElement.appendChild(textNode);
			root.appendChild(messageElement);
			messageElement.appendChild(msg_Node);
			root.appendChild(useridElement);
			useridElement.appendChild(i_Node);
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os)));
			xml = os.toString();
		}
		catch(IOException e){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (TransformerException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		}
		return xml;
	}
	
	
	/**
	 * Create XML document for the new room
	 * @param roomname name of the room
	 * @param roomId id of the room
	 * @return xml
	 */
	public String createXMLNewRoom(String roomname, int roomId){
		try {
			OutputStream os = new ByteArrayOutputStream();
			File f = new File("D:\\Server\\new_room.xml");
			if(!f.exists()){
				f.createNewFile();
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("room");
			doc.appendChild(root);
			Element idElement = doc.createElement("roomId");
			textNode = doc.createTextNode(Integer.toString(roomId));
			Element roomnameElement = doc.createElement("roomname");
			Text rn_Node = doc.createTextNode(roomname);
			root.appendChild(idElement);
			idElement.appendChild(textNode);
			root.appendChild(roomnameElement);
			roomnameElement.appendChild(rn_Node);
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os)));
			xml = os.toString();
		}
		catch(IOException ex){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (TransformerException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		}
		return xml;
	}
	
	/**
	 * Create XML document for user joined
	 * @param users Users resultset
	 * @return xml
	 */
	public String createXMLUserJoined(ResultSet users){
		try {
			OutputStream os = new ByteArrayOutputStream();
			File f = new File("D:\\Server\\user_joined.xml");
			if(!f.exists()){
				f.createNewFile();
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("UserJoined");
			doc.appendChild(root);
			while(users.next()){
				Element idElement = doc.createElement("userId");
				textNode = doc.createTextNode(users.getString("userId"));
				Element loginElement = doc.createElement("login");
				Text log_Node = doc.createTextNode(users.getString("login"));
				root.appendChild(idElement);
				idElement.appendChild(textNode);
				root.appendChild(loginElement);
				loginElement.appendChild(log_Node);
			}
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os)));
			xml = os.toString();
		}
		catch(IOException ex){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (TransformerException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (SQLException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		}
		return xml;
	}
	
	/**
	 * Create XML document for all messages
	 * @param messages
	 * @return
	 */
	public String createXMLRoomMessages(ResultSet messages){
		try {
			OutputStream os = new ByteArrayOutputStream();
			File f = new File("D:\\Server\\all_messages.xml");
			if(!f.exists()){
				f.createNewFile();
			}
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element root = doc.createElement("MessagesInfo");
			doc.appendChild(root);
			Element message= doc.createElement("messages");
			root.appendChild(message);
			while(messages.next()){
				Element msg = doc.createElement("message");
				textNode = doc.createTextNode(messages.getString("message"));
				Element id = doc.createElement("userId");
				Text id_Node = doc.createTextNode(messages.getString("userId"));
				root.appendChild(msg);
				msg.appendChild(textNode);
				root.appendChild(id);
				id.appendChild(id_Node);
			}
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(f)));
			transform.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(os)));
			xml = os.toString();
		}
		catch(IOException ex){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (TransformerException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (DOMException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (SQLException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		} 
		catch (ParserConfigurationException e) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,e);
		}
		return xml;
	}
	
	/**
	 * XML Parser
	 * parse input stream from the client
	 * for detail information @see the package java.xml.parsers
	 * @param map Hashtable
	 * @param src source
	 * @throws UnsupportedEncodingException
	 */
	public void parse(ConcurrentHashMap<String, String> map, String src) throws UnsupportedEncodingException{
		/**
		 * Defines a factory API that enables applications to obtain a parser that produces DOM 
		 * object trees from XML documents.
		 * @see javax.xml.parsers.DocumentBuilderFactory
		 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		/**
		 * Set encoding to UTF-8
		 * Src is source of bytes such as InputStream
		 */
		byte[] bytes = src.getBytes("UTF-8");
		InputStream is = new ByteArrayInputStream(bytes);
		try {
			/**
			 * Defines the API to obtain DOM Document instances from an XML document.
			 * @see javax.xml.parsers.DocumentBuilder for details
			 */
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse input stream
			Document d = db.parse(is);
			//root element
			Element root = d.getDocumentElement();
			NodeList child = root.getChildNodes();
			for(int i = 0; i < child.getLength(); i++){
				Node thisNode = root.getChildNodes().item(i);
				
				/**
				 * For more information
				 * @see DOM Elements, Child Elements, and the Node Interface in JDK doc
				 */
				if(thisNode instanceof Element){
					Element children = (Element)thisNode;
					//get first child
					Text textNode = (Text)children.getFirstChild();
					//get node name
					String node = children.getNodeName();
					if(textNode != null){
						String text = textNode.getData().trim();
						map.put(node, text);
					}
				}
			}
		}
		catch(ParserConfigurationException ex){
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (SAXException ex) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		} 
		catch (IOException ex) {
			Logger.getLogger(XMLWrapper.class.getName()).log(Level.WARNING,null,ex);
		}
	}
}


