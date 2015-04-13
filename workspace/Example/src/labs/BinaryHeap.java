package labs;

import java.util.Arrays;
import java.util.NoSuchElementException;
  
// http://en.wikipedia.org/wiki/Binary_heap
//max heap
public class BinaryHeap<T extends Comparable<T>> {
	BinaryTree<T> tree = new BinaryTree<T>();
	// Insert an element into the binary heap
	// Not a one-liner
	private static final int d = 2;
	private int heapSize;
    private int[] heap;
	
    public BinaryHeap(int capacity)
    {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }
    
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }
    
    public boolean isFull( ) {
	   return heapSize == heap.length;
	}

    public void makeEmpty( )
    {
        heapSize = 0;
    }
    
    private int parent(int i) 
    {
        return (i - 1)/d;
    }
    
    private int kthChild(int i, int k) 
    {
        return d * i + k;
    }
	public void insert (int x) {
	
		if (isFull( ) ) throw new NoSuchElementException("Overflow Exception");
		
		 heap[heapSize++] = x;
		heapUp(heapSize - 1);
	}
	
	 public int findMin( )
	    {
	        if (isEmpty() )
	            throw new NoSuchElementException("Underflow Exception");           
	        return heap[0];
	    }
	 public int deleteMin()
	    {
	        int keyItem = heap[0];
	        remove(0);
	        return keyItem;
	    }
	// Only remove the root (not a one-liner)
	public int remove (int ind) {
		if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        int keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapDown(ind);
		return keyItem;
	}
	private void heapUp(int childInd)
    {
        int tmp = heap[childInd];    
        while (childInd > 0 && tmp < heap[parent(childInd)])
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }                   
        heap[childInd] = tmp;
    }
 
    private void heapDown(int ind)
    {
        int child;
        int tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (heap[child] < tmp)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }
    private int minChild(int ind) 
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) 
        {
            if (heap[pos] < heap[bestChild]) 
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
	
}
