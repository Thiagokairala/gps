package control;

import model.Heap;
import model.HeapNode;

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
			this.insertAtEnd(nodeToInsert);
		} else {
			throw new InvalidNodeException();
		}
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

	private void checkChildrens(final int node) {

		final int leftChildren = node * 2;
		final int rightChildren = leftChildren + 1;

		// checking the left children.
		try {
			this.checkOneChildren(leftChildren, node);
		} catch (ArrayIndexOutOfBoundsException e) {
			// nothing to do.
		}
		// checking the right children.
		try {
			this.checkOneChildren(rightChildren, node);
		} catch (ArrayIndexOutOfBoundsException e) {
			// nothing to do.
		}
	}

	private void checkOneChildren(final int children, final int parent)
			throws ArrayIndexOutOfBoundsException {
		boolean isInHeap = children < this.getHeap().getHeapSize();

		if (isInHeap) {
			HeapNode childrenHeapNode = this.getHeap().getHeap()[children];
			HeapNode parentHeapNode = this.getHeap().getHeap()[parent];

			if (childrenHeapNode.getDistance() < parentHeapNode.getDistance()) {
				this.swap(children, parent);
			} else {
				// nothing to do.
			}
			assert (this.getHeap().getHeap()[children].getDistance() > this
					.getHeap().getHeap()[parent].getDistance());
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}

	}

	private void swap(final int firstElement, final int secondElement) {
		assert (firstElement > 0);
		assert (secondElement > 0);
		assert (secondElement < firstElement);
		assert (secondElement < this.getHeap().getHeapSize());
		assert (firstElement < this.getHeap().getHeapSize());

		HeapNode first = this.getHeap().getHeap()[firstElement];
		HeapNode second = this.getHeap().getHeap()[secondElement];

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
