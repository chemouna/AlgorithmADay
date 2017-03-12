package com.mounacheikhna.algoday;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by m.cheikhna on 09/03/2017.
 */
public class InsertionSortTest {

    private InsertionSort is;

    @Before
    public void setUp() throws Exception {
        is = new InsertionSort();
    }

    @Test
    public void insertionSortSortsArray() throws Exception {
        int [] a = new int[] {1, 5, 2, 7, 3, 9, 4, 6, 8, 1};
        is.insertionSort(a);
        System.out.println(Arrays.toString(a));
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, a);
    }

}
