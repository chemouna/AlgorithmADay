package com.mounacheikhna.algoday;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by m.cheikhna on 09/03/2017.
 *
 * TODO: add invariants here
 */
public class RadixSort {

    /**
     * Radix sort
     */
    public static void sort(int[] array, int offset, int end, int shift) {
        int[] last = new int[256];
        int[] pointer = new int[256];

        for (int x = offset; x < end; ++x) {
            last[(array[x] >> shift) & 0xFF]++;
        }

        last[0] += offset;
        pointer[0] = offset;
        for (int x = 1; x < 256; x++) {
            pointer[x] = last[x - 1];
            last[x] += last[x - 1];
        }

        for (int x = 0; x < 256; x++) {
            while (pointer[x] != last[x]) {
                int value = array[pointer[x]];
                int y = (value >> shift) & 0xff;
                while (x != y) {
                    int temp = array[pointer[y]];
                    array[pointer[y]++] = value;
                    value = temp;
                    y = (value >> shift) & 0xff;
                }
                array[pointer[x]++] = value;
            }
        }

        if (shift > 0) {
            shift -= 8;
            for (int x = 0; x < 256; ++x) {
                int size = x > 0 ? pointer[x] - pointer[x - 1] : pointer[0] - offset;
                if (size > 64) {
                    sort(array, pointer[x] - size, pointer[x], shift);
                } else if (size > 1) {
                    insertionSort(array, pointer[x] - size, pointer[x]);
                }
            }
        }
    }

    private static void insertionSort(int array[], int offset, int end) {
        for (int x = offset; x < end; ++x) {
            for (int y = x; y > offset && array[y - 1] > array[y]; y--) {
                int temp = array[y];
                array[y] = array[y - 1];
                array[y - 1] = temp;
            }
        }
    }

    public static void benchmark(int algorithm, int x) {
        int y = (int) (Math.ceil(100000000d / x) + 0.5001d);
        int[] array = new int[x * y];

        Random r = new Random(1);
        for (int i = 0; i < array.length; ++i) {
            array[i] = r.nextInt() & Integer.MAX_VALUE;
        }

        long a = System.nanoTime();
        for (int i = 0; i < y; ++i) {
            if (algorithm == 0) {
                sort(array, i * x, (i + 1) * x, 24);
            } else {
                Arrays.sort(array, i * x, (i + 1) * x);
            }
        }
        long b = System.nanoTime();

        for (int i = 0; i < y; ++i) {
            int n = (i + 1) * x - 1;
            while (n-- > i * x) {
                if (array[n] > array[n + 1]) {
                    throw new RuntimeException("oops : Not sorted");
                }
            }
        }

        long micro = ((b - a) / y) / 1000L;

        System.out.println((algorithm == 0 ? "Radix.sort : " : "Arrays.sort : ") + micro + " ms");
    }

    public static void main(String[] args) {
        //benchmark(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        benchmark(0, 10000);
        benchmark(1, 10000);
    }
}
