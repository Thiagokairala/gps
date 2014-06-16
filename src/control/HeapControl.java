package control;

import model.Heap;
import model.HeapNode;
import exception.EmptyHeapException;
import exception.FoundRootException;
import exception.InvalidNodeException;

public class HeapControl {
    private Heap heap = new Heap();

    /**
     * This method inserts one node to the heap.
     * 
     * @param nodeToInsert
     * @throws InvalidNodeException
     */
    public void insertOnHeap(HeapNode nodeToInsert) throws InvalidNodeException {
	final boolean isValid = nodeToInsert != null
		&& nodeToInsert.getDistance() != 0
		&& nodeToInsert.getEdgeUsed() != null
		&& nodeToInsert.getNode() != null;

	if (isValid) {
	    this.insertOnHeapPrivate(nodeToInsert);
	} else {
	    throw new InvalidNodeException();
	}
    }

    /**
     * This method removes one item from the heap and returns it.
     * 
     * @return the node that was taken out.
     * @throws EmptyHeapException
     */
    public HeapNode removeFromHeap() throws EmptyHeapException {
	if (this.getHeap().isEmpty()) {
	    this.putHeadInLast();
	    HeapNode smaller = this.removeLast();

	    return smaller;
	} else {
	    throw new EmptyHeapException();
	}

    }

    private HeapNode removeLast() {
	final int FIRST_POSITION = 1;
	final int lastPosition = this.getHeap().getHeapSize() - 1;
	HeapNode last = this.getHeap().getHeap()[lastPosition];

	this.shrinkHeap();
	this.heapify(FIRST_POSITION);

	return last;
    }

    private void heapify(final int parent) {
	final int leftChild = parent * 2;
	final int rightChild = parent * 2 + 1;

	if (leftChild < this.getHeap().getHeapSize()) {
	    HeapNode[] heap = this.getHeap().getHeap();

	    final float parentDistance = heap[parent].getDistance();

	    final int smallerChildren = this.findSmallerDistanceChildren(
		    leftChild, rightChild);

	    final float smallerChildrenDistance = heap[smallerChildren]
		    .getDistance();

	    if (smallerChildrenDistance < parentDistance) {
		this.swap(smallerChildren, parent);
		this.heapify(smallerChildren);
	    } else {
		// stops the recursion.
	    }
	} else {
	    // stops the recursion.
	}
    }

    private int findSmallerDistanceChildren(int leftChild, int rightChild) {
	final float leftChildrenDistance = this.getHeap().getHeap()[leftChild]
		.getDistance();
	final float rightChildrenDistance = rightChild < this.getHeap()
		.getHeapSize() ? this.getHeap().getHeap()[rightChild]
		.getDistance() : -1;
	int smallerChildren = 0;

	if (rightChildrenDistance == -1) {
	    smallerChildren = leftChild;
	} else {
	    smallerChildren = leftChildrenDistance < rightChildrenDistance ? leftChild
		    : rightChild;
	}

	return smallerChildren;
    }

    private void shrinkHeap() {
	final int size = this.getHeap().getHeapSize();
	final int newSize = size - 1;

	HeapNode[] newHeap = new HeapNode[newSize];

	System.arraycopy(this.getHeap().getHeap(), 0, newHeap, 0, newSize);

	this.getHeap().setHeap(newHeap);

    }

    private void putHeadInLast() {
	final int head = 1;
	final int lastPosition = this.getHeap().getHeapSize() - 1;

	this.swap(head, lastPosition);

    }

    /**
     * This method is the private part of the insert.
     * 
     * @param nodeToInsert
     */
    private void insertOnHeapPrivate(HeapNode nodeToInsert) {
	final boolean isValid = nodeToInsert != null
		&& nodeToInsert.getDistance() != 0
		&& nodeToInsert.getEdgeUsed() != null
		&& nodeToInsert.getNode() != null;
	assert (isValid);

	this.insertAtEnd(nodeToInsert);
	final int lastPosition = this.getHeap().getHeapSize() - 1;
	this.shiftUp(lastPosition);

	for (int i = 1; i < this.getHeap().getHeapSize() - 2; i++) {
	    assert (this.getHeap().getHeap()[i].getDistance() < this.getHeap()
		    .getHeap()[i + 1].getDistance());
	}
    }

    /**
     * This method puts the element up if needed.
     * 
     * @param node
     */
    private void shiftUp(final int node) {
	assert (node > 0);

	try {
	    final int parent = this.findParent(node);

	    final double distanceChildren = this.getHeap().getHeap()[node]
		    .getDistance();

	    final double distanceParent = this.getHeap().getHeap()[parent]
		    .getDistance();

	    if (distanceChildren < distanceParent) {
		this.swap(node, parent);
		this.shiftUp(parent);
	    } else {
		// nothing to do.
	    }

	} catch (FoundRootException e) {
	    // stop recursion.
	}
    }

    /**
     * This method exchange 2 items on heap.
     * 
     * @param firstElement
     *            must be bigger then the second.
     * @param secondElement
     *            must be smaller then the first.
     */
    private void swap(final int firstElement, final int secondElement) {
	assert (firstElement > 0);
	assert (secondElement > 0);
	assert (secondElement < firstElement);
	assert (secondElement < this.getHeap().getHeapSize());
	assert (firstElement < this.getHeap().getHeapSize());

	HeapNode first = this.getHeap().getHeap()[firstElement];
	HeapNode second = this.getHeap().getHeap()[secondElement];

	first.getNode().setPositionOnHeap(secondElement);
	second.getNode().setPositionOnHeap(firstElement);

	this.getHeap().getHeap()[firstElement] = second;
	this.getHeap().getHeap()[secondElement] = first;

	assert (this.getHeap().getHeap()[firstElement].equals(second));
	assert (this.getHeap().getHeap()[secondElement].equals(first));
    }

    private int findParent(final int position) throws FoundRootException {
	final int father = position / 2;
	if (father > 0) {
	    return father;
	} else {
	    throw new FoundRootException();
	}
    }

    private void insertAtEnd(HeapNode nodeToInsert) {
	final boolean isValid = nodeToInsert != null
		&& nodeToInsert.getDistance() != 0
		&& nodeToInsert.getEdgeUsed() != null
		&& nodeToInsert.getNode() != null;
	assert (isValid);

	this.growHeap();

	final int lastPosition = this.getHeap().getHeapSize() - 1;
	// This var is to include the node on the last position.

	nodeToInsert.getNode().setPositionOnHeap(lastPosition);

	this.getHeap().getHeap()[lastPosition] = nodeToInsert;

    }

    private void growHeap() {
	final int size = this.getHeap().getHeapSize();
	final int newSize = size + 1;

	HeapNode[] newHeap = new HeapNode[newSize];

	System.arraycopy(this.getHeap().getHeap(), 0, newHeap, 0, size);

	this.getHeap().setHeap(newHeap);
    }

    public Heap getHeap() {
	return heap;
    }

    public void setHeap(Heap heap) {
	this.heap = heap;
    }
}
