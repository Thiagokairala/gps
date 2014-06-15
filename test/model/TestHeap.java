package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHeap {

	@Test
	public void testIsEmpty() {
		Heap heap = new Heap();

		assertTrue(heap.isEmpty());
	}

	@Test
	public void testIsNotEmpty() {
		Heap heap = new Heap();

		HeapNode[] heapNode = { null, null };
		heap.setHeap(heapNode);

		assertTrue(!heap.isEmpty());
	}
}
