package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestNode {

	private Node node;

	@Before
	public void setUp() {
		this.node = new Node("name of the class", ClassFloor.FIRST_FLOOR, 1);
	}

	@Test
	public void testCreateNode() {
		assertNotNull(this.node);
	}

	@Test
	public void testGetEdges() {
		List<Edge> listOfEdges = this.node.getEdges();

		assertNotNull(listOfEdges);
	}

	@Test
	public void testHasEdgesEmpty() {
		boolean test = this.node.hasEdges();

		assertTrue(!test);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testHasEdgesNotEmpty() {
		List<Edge> mockedList = Mockito.mock(List.class);
		Mockito.when(mockedList.isEmpty()).thenReturn(false);

		Node spyNode = Mockito.spy(this.node);

		Mockito.doReturn(mockedList).when(spyNode).getEdges();

		boolean test = spyNode.hasEdges();

		assertTrue(test);
	}
}
