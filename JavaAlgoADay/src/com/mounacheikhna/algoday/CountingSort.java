package com.mounacheikhna.algoday;

/**
 * Created by m.cheikhna on 10/03/2017.
 */
public class CountingSort {

    void countingSort(int[] a) {
        int[] count = new int[a.length];
        for (int v : a) {
            count[v]++;
        }
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[j] > 0) {
                a[j] = i;
                count[j]--;
            }
            j++;
        }
    }


}
