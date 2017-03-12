package com.mounacheikhna.algoday;

/**
 * Created by m.cheikhna on 12/03/2017.
 */
public class InsertionSort {

    void insertionSort(int array[], int offset, int end) {
        /*
         * Outer loop invariant:
         * Elements (A[1] .. A[i-1]) to the left of (i) are sorted
         */
        for (int i = offset; i < end; ++i) {
            for (int j = i; j > offset && array[j - 1] > array[j]; j--) {
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }

    void insertionSort(int[] a) {
        insertionSort(a, 0, a.length);
    }
}
