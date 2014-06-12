package initialization;

import static org.junit.Assert.*;
import model.Graph;

import org.junit.Test;

public class TestInit {

	@Test
	public void testInit() throws Exception {
		Graph graph = Init.init();

		assertTrue(graph.getNodes().size() > 0);
	}

}
