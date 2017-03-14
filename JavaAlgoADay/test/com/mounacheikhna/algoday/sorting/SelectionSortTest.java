package com.mounacheikhna.algoday.sorting;

import com.mounacheikhna.algoday.sorting.SelectionSort;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by m.cheikhna on 09/03/2017.
 */
public class SelectionSortTest {

    private SelectionSort selectionSort;

    @Before
    public void setUp() throws Exception {
        selectionSort = new SelectionSort();
    }

    @Test
    public void selectionSortSortsArray() throws Exception {
        int [] a = new int[] {1, 5, 2, 7, 3, 9, 4, 6, 8, 1};
        selectionSort.sort(a);
        System.out.println(Arrays.toString(a));
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, a);
    }

}
