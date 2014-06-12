package initialization;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.ClassFloor;
import model.Graph;
import model.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Init {

	public static Graph init() throws ParserConfigurationException,
			SAXException, IOException {
		Graph graph = new Graph();

		Document document = Init.getDocument();

		graph = Init.populateNodes(graph, document);

		return graph;
	}

	private static Graph populateNodes(Graph graph, Document document) {
		document.getDocumentElement().normalize();

		NodeList graphNodesXML = document.getElementsByTagName("node");

		for (int i = 0; i < graphNodesXML.getLength(); i++) {
			Element node = (Element) graphNodesXML.item(i);

			final String className = node.getElementsByTagName("className")
					.item(0).getTextContent();
			final int floor = Integer.parseInt(node
					.getElementsByTagName("classFloor").item(0)
					.getTextContent());
			final int id = Integer.parseInt(node.getElementsByTagName("idNode")
					.item(0).getTextContent());

			Node nodeToInclude = new Node(className, Init.getFloor(floor), id);
			graph.getNodes().add(nodeToInclude);
		}

		return graph;
	}

	private static ClassFloor getFloor(final int floor) {
		switch (floor) {
		case 1:
			return ClassFloor.FIRST_FLOOR;
		case 2:
			return ClassFloor.SECOND_FLOOR;
		case 3:
			return ClassFloor.FIRST_SECOND_FLOOR;
		default:
			return null;
		}

	}

	private static Document getDocument() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(false);

		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

		Document document = documentBuilder.parse(new File("graph.xml"));

		return document;

	}
}
