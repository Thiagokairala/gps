package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestGraph {

	private Graph graph;

	@Before
	public void setUp() {
		this.graph = new Graph();
	}

	@Test
	public void testCreateGraph() {
		assertNotNull(this.graph);
	}

	@Test
	public void testIsEmptyTrue() {
		boolean isEmpty = this.graph.isEmpty();

		assertTrue(isEmpty);
	}

	@Test
	public void testIsEmptyFalse() {
		Graph spyGraph = Mockito.spy(this.graph);
		@SuppressWarnings("unchecked")
		List<Node> list = Mockito.mock(List.class);

		Mockito.when(list.isEmpty()).thenReturn(false);

		Mockito.doReturn(list).when(spyGraph).getNodes();

		boolean isEmpty = spyGraph.isEmpty();

		assertTrue(!isEmpty);
	}

	@Test
	public void testInsertOneNode() {
		Node node = Mockito.mock(Node.class);

		this.graph.insertOneNode(node);

		assertTrue(!this.graph.isEmpty());
	}

	@Test
	public void testGetNodesEmpty() {
		List<Node> listOfNodes = this.graph.getNodes();

		assertTrue(listOfNodes.isEmpty());

	}
}
