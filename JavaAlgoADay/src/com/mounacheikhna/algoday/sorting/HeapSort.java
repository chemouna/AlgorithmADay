package com.mounacheikhna.algoday.sorting;

/**
 * Created by m.cheikhna on 09/03/2017.
 */
public class HeapSort {

    public void heapSort(int[] a) {
        maxHeapify(a);
        for (int end = a.length - 1; end >= 0; end--) {
            //swap the root with last element of the heap
            swap(a, end, 0);
            //put the heap back into its order
            siftDown(a, 0, end - 1);
        }
    }

    private void siftDown(int[] a, int start, int end) {
        int root = start;
        //while root has at least a child
        while((root * 2 + 1) <= end) {
            int child = root * 2 + 1; // left child
            if(child + 1 <= end && a[child] < a[child + 1]) { //there's a right child and its less than its right sibling (for min heap we will check for >)
                child = child + 1;
            }
            if(a[root] < a[child]) { //max-heap order not satisfied -> let's fix it
                swap(a, root, child);
                root = child; //continue sifting down
            }
            else {
                return;
            }
        }
    }

    private void swap(int[] a, int e1, int e2) {
        int temp = a[e1];
        a[e1] = a[e2];
        a[e2] = temp;
    }

    private void maxHeapify(int[] a) {
        for (int start = (a.length - 1) / 2; start >= 0; start--) {
            //sift down the node at index start to the proper place so that all nodes below start
            // are in heap order
            siftDown(a, start, a.length - 1);
        }
    }

}
