package com.mounacheikhna.algoday.stringmatching;

/**
 * Created by m.cheikhna on 14/03/2017.
 */
public class Kmp {

    private String pattern;
    private int[][] dfa;

    void buildPatternDfa(String pattern) {
        this.pattern = pattern;
        final int m = pattern.length();
        final int alphabetSize = 256;
        dfa = new int[alphabetSize][m];
        dfa[pattern.charAt(0)][0] = 1;
        int x = 0;
        for (int j = 0; j < m; j++) {
            for (int c = 0; c < alphabetSize; c++) {
                dfa[c][j] = dfa[c][x]; //value for the mismatch case
            }
            dfa[pattern.charAt(j)][j] = j + 1;//value for the match case
            x = dfa[pattern.charAt(j)][x]; //restart
        }
    }

    /**
     * finds index of pattern in txt.
     */
    public int search(String txt) {
        int j = 0;//pointer on the pattern
        int i;//i pointer on txt
        final int m = pattern.length();
        final int n = txt.length();
        for (i = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) {
            return i - m;
        } else {
            return -1; //not found
        }
    }

    public static void main(String[] args) {
        String pat = "AADA";
        String txt = "AABRAACADABRAACAADABRA";
        Kmp kmp = new Kmp();
        kmp.buildPatternDfa(pat);
        int offset = kmp.search(txt);
        System.out.println("index found : "+ offset);
    }
}
