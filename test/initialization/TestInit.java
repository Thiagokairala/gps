package initialization;

import static org.junit.Assert.*;
import model.Graph;
import model.Node;

import org.junit.Test;

public class TestInit {

	@Test
	public void testInit() throws Exception {
		Graph graph = Init.init();

		for (int i = 0; i < graph.getNodes().size(); i++) {
			for (int j = 0; j < graph.getNodes().get(i).getEdges().size(); j++) {
				Node nodeFrom = graph.getNodes().get(i);
				Node nodeFromOfEdge = nodeFrom.getEdges().get(j).getWhoFrom();
				boolean test = nodeFrom.equals(nodeFromOfEdge);

				assertTrue(test);
			}
		}
	}
}
