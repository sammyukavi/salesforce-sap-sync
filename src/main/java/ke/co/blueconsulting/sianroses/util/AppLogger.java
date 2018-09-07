package ke.co.blueconsulting.sianroses.util;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.APP_LOG_FILE_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.*;

public class AppLogger {
	
	
	private static String logFile;
	private static boolean xmlFileIsFine = false;
	private static Document document = null;
	private static Node appLog, infoMessagesNode, warningMessagesNode, errorMessagesNode;
	private static AppLogger instance;
	
	static {
		logFile = getDirectoryName() + APP_LOG_FILE_NAME;
		if (logFileExists()) {
			parseLogFile();
		}
	}
	
	static synchronized AppLogger getInstance() {
		if (instance == null)
			instance = new AppLogger();
		return instance;
	}
	
	static Node getAppLogNode() {
		return appLog;
	}
	
	private static void parseLogFile() {
		try {
			DOMParser parser = new DOMParser();
			parser.parse(logFile);
			document = parser.getDocument();
			NodeList xmlRoot = document.getChildNodes();
			appLog = getNodeByName(ROOT_ELEMENT_NODE_NAME, xmlRoot);
			infoMessagesNode = getNodeByName(INFO_MESSAGES_NODE_NAME, appLog.getChildNodes());
			warningMessagesNode = getNodeByName(WARNING_MESSAGES_NODE_NAME, appLog.getChildNodes());
			errorMessagesNode = getNodeByName(ERROR_MESSAGES_NODE_NAME, appLog.getChildNodes());
			xmlFileIsFine = true;
		} catch (SAXException | IOException e) {
			//If a parse error occurred delete the error log and create a new one
			if (e instanceof SAXParseException) {
				File file = new File(logFile);
				file.delete();
			} else {
				e.printStackTrace();
			}
		}
	}
	
	private static boolean logFileExists() {
		File directory = new File(getDirectoryName());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File file = new File(logFile);
		if (!file.exists()) {
			return createErrorLogFile();
		}
		return true;
	}
	
	public static String getDirectoryName() {
		String separator = File.separator;
		return System.getProperty("user.home") + separator + APP_DIR_NAME + separator;
	}
	
	private static boolean createErrorLogFile() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();
			Element rootElement = document.createElement(ROOT_ELEMENT_NODE_NAME);
			document.appendChild(rootElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(logFile));
			transformer.transform(source, result);
			return true;
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static Node getNodeByName(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
		}
		return null;
	}
	
	public static void logInfo(String message) {
		if (xmlFileIsFine) {
			if (infoMessagesNode == null) {
				appLog.appendChild(document.createElement(INFO_MESSAGES_NODE_NAME));
			}
			createElement(message, INFO_MESSAGES_NODE_NAME, appLog.getChildNodes());
		}
	}
	
	public static void logWarning(String message) {
		if (xmlFileIsFine) {
			if (warningMessagesNode == null) {
				appLog.appendChild(document.createElement(WARNING_MESSAGES_NODE_NAME));
			}
			createElement(message, WARNING_MESSAGES_NODE_NAME, appLog.getChildNodes());
		}
	}
	
	public static void logError(String message) {
		if (xmlFileIsFine) {
			if (errorMessagesNode == null) {
				appLog.appendChild(document.createElement(ERROR_MESSAGES_NODE_NAME));
			}
			createElement(message, ERROR_MESSAGES_NODE_NAME, appLog.getChildNodes());
		}
	}
	
	private static void createElement(String message, String nodeName, NodeList nodesList) {
		Element element = document.createElement(MESSAGE_TAG_NAME);
		element.appendChild(document.createTextNode(message));
		Node node = getNodeByName(nodeName, nodesList);
		if (node != null) {
			node.appendChild(element);
		}
		writeNode();
	}
	
	private static void writeNode() {
		try {
			DOMSource source = new DOMSource(document);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			StreamResult result = new StreamResult(logFile);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	static HashMap<String, ArrayList<String>> getMessages() {
		HashMap<String, ArrayList<String>> messages = new HashMap<>();
		messages.put(INFO_MESSAGES_NODE_NAME, getMessages(infoMessagesNode));
		messages.put(WARNING_MESSAGES_NODE_NAME, getMessages(warningMessagesNode));
		messages.put(ERROR_MESSAGES_NODE_NAME, getMessages(errorMessagesNode));
		return messages;
	}
	
	private static ArrayList<String> getMessages(Node messagesNode) {
		NodeList nodes = messagesNode.getChildNodes();
		ArrayList<String> messages = new ArrayList<>();
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(MESSAGE_TAG_NAME)) {
				messages.add(node.getTextContent());
			}
		}
		return messages;
	}
}
