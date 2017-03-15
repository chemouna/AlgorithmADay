package com.mounacheikhna.algoday.stringmatching;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by m.cheikhna on 15/03/2017.
 */

public class RabinKarp {
    private String pat;      // the pattern  // needed only for Las Vegas
    private long patHash;    // pattern hash value
    private int m;           // pattern length
    private long q;          // a large prime, small enough to avoid long overflow
    private int radix;           // radix
    private long RM;         // radix^(M-1) % Q

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public RabinKarp(String pat) {
        this.pat = pat;      // save pattern (needed only for Las Vegas)
        radix = 256;
        m = pat.length();
        q = longRandomPrime();

        // precompute radix^(m-1) % q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= m - 1; i++)
            RM = (radix * RM) % q;
        patHash = hash(pat, m);
    }

    // Compute hash for key[0..m-1]. 
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++)
            h = (radix * h + key.charAt(j)) % q;
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++)
            if (pat.charAt(j) != txt.charAt(i + j)) return false;
        return true;
    }

    // Monte Carlo version: always return true
    // private boolean check(int i) {
    //    return true;
    //}

    /**
     * Returns the index of the first occurrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; -1 if no such match
     */
    public int search(String txt) {
        int n = txt.length();
        if (n < m) return -1;
        long currentHash = hash(txt, m);

        // check for match at offset 0
        if ((patHash == currentHash) && check(txt, 0)) return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            currentHash = (currentHash + q - RM * txt.charAt(i - m) % q) % q;
            currentHash = (currentHash * radix + txt.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((patHash == currentHash) && check(txt, offset)) return offset;
        }

        // no match
        return -1;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String txt = "Hello World";
        String pat = "Wo";

        RabinKarp searcher = new RabinKarp(pat);
        int offset = searcher.search(txt);
        System.out.println("Found at : " + offset);
    }
}
