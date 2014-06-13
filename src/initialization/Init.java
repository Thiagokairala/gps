package initialization;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.ClassFloor;
import model.Edge;
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
		document.getDocumentElement().normalize();

		graph = Init.populateNodes(graph, document);
		graph = Init.populateEdges(graph, document);

		return graph;
	}

	private static Graph populateEdges(Graph graph, final Document document) {
		assert (graph != null);
		assert (graph != document);

		NodeList graphEdgesXML = document.getElementsByTagName("edge");

		for (int i = 0; i < graphEdgesXML.getLength(); i++) {
			Element node = (Element) graphEdgesXML.item(i);

			final int whoFrom = Integer.parseInt(node
					.getElementsByTagName("whoFrom").item(0).getTextContent());
			final int whoTo = Integer.parseInt(node
					.getElementsByTagName("whoTo").item(0).getTextContent());

			final float distance = Float.parseFloat(node
					.getElementsByTagName("distance").item(0).getTextContent());

			final int idEdge = Integer.parseInt(node
					.getElementsByTagName("idEdge").item(0).getTextContent());

			int positionOfNodeFrom = Init.findPosition(graph, whoFrom);
			int positionOfNodeTo = Init.findPosition(graph, whoTo);

			Node nodeFrom = graph.getNodes().get(positionOfNodeFrom);
			Node nodeTo = graph.getNodes().get(positionOfNodeTo);

			Edge edge = new Edge(nodeFrom, nodeTo, distance, idEdge);

			graph.getNodes().get(positionOfNodeFrom).getEdges().add(edge);
		}

		return graph;
	}

	private static int findPosition(final Graph graph, final int whoFrom) {
		assert (graph != null);
		assert (whoFrom > 0);

		int i = 0;

		while (i < graph.getNodes().size()
				&& graph.getNodes().get(i).getIdNode() != whoFrom) {
			i++;
		}

		assert (i < graph.getNodes().size());
		return i;
	}

	private static Graph populateNodes(Graph graph, final Document document) {

		assert (graph != null);
		assert (document != null);

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
		assert (floor > 0);
		assert (floor <= 2);
		switch (floor) {
		case 1:
			return ClassFloor.FIRST_FLOOR;
		case 2:
			return ClassFloor.SECOND_FLOOR;
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

		assert (document != null);
		return document;

	}
}
