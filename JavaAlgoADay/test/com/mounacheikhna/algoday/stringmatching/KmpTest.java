package com.mounacheikhna.algoday.stringmatching;

import com.mounacheikhna.algoday.sorting.CountingSort;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by m.cheikhna on 14/03/2017.
 */
public class KmpTest {

    private Kmp kmp;

    @Before
    public void setUp() throws Exception {
        kmp = new Kmp();
    }

    @Test
    public void kmpSearchFindsIndex() throws Exception {
        String pat = "AADA";
        String txt = "AABRAACADABRAACAADABRA";
        kmp.buildPatternDfa(pat);
        assertEquals(15, kmp.search(txt));
    }

}
