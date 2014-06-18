package model;

public class Heap {

    private HeapNode[] heap = new HeapNode[1];
    private final HeapNode sentinel = new HeapNode();

    public Heap() {
	heap[0] = sentinel;
    }

    public HeapNode[] getHeap() {
	return heap;
    }

    public void setHeap(HeapNode[] heap) {
	this.heap = heap;
    }

    public int getHeapSize() {
	return this.heap.length;
    }

    public boolean isEmpty() {
	boolean isEmpty = this.heap.length > 1 ? false : true;

	return isEmpty;
    }
}
