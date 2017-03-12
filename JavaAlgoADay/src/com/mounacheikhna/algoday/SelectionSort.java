package com.mounacheikhna.algoday;

/**
 * Created by m.cheikhna on 12/03/2017.
 */
public class SelectionSort {

    void sort(int[] a, int n) {
        /*
         * Outer loop invariant: a[0...i-1] is sorted
         * all entries in a[i..n-1] are larger than or equal to the entries in a[0..i-1]
         */
        for (int i = 0; i < n - 1; i++) {
            int best = i;
            //Invariant: All entries in a[i..j-1] are greater than or equal to a[best].
            for (int j = i; j < n; j++) {
                if (a[j] < a[best]) {
                    best = j;
                }
            }
            //Invariant: best indexes a minimum element of the unsorted array
            swap(a, i, best);
        }
    }

    private void swap(int[] arr, int pivotIndex, int right) {
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = temp;
    }

    public void sort(int[] a) {
        sort(a, a.length);
    }
}
