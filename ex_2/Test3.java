package ex_2;

import java.lang.Math;
import java.util.Random;

public class Test3 {

    public static void integerRandom() {
        int[][][] matrix = new int[4][5][8];
        int lowestNumber = Integer.MAX_VALUE;
        int highestNumber = Integer.MIN_VALUE;
        Random rand = new Random();
        for (int block = 0; block < 4; block++) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 8; col++) {
                    matrix[block][row][col] = rand.nextInt(10000000);
                }
            }
        }

        for (int block = 0; block < 4; block++) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 8; col++) {
                    System.out.print(matrix[block][row][col] + " ");
                    if (matrix[block][row][col] < lowestNumber) {
                        lowestNumber = matrix[block][row][col];
                    } else if (matrix[block][row][col] > highestNumber) {
                        highestNumber = matrix[block][row][col];
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");

        }
        System.out.println("High=" + highestNumber + " Lowest=" + lowestNumber);
    }

    public static void doubleRandom() {
        double[][][] matrix = new double[4][5][8];
        double lowestNumber = Double.MAX_VALUE;
        double highestNumber = Double.MIN_VALUE;
        for (int block = 0; block < 4; block++) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 8; col++) {
                    matrix[block][row][col] = Math.random();
                }
            }
        }

        for (int block = 0; block < 4; block++) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 8; col++) {
                    System.out.print(matrix[block][row][col] + " ");
                    if (matrix[block][row][col] < lowestNumber) {
                        lowestNumber = matrix[block][row][col];
                    } else if (matrix[block][row][col] > highestNumber) {
                        highestNumber = matrix[block][row][col];
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();

        }
        System.out.println("High=" + highestNumber + " Lowest=" + lowestNumber);
    }

    public static void main(String[] args) {
        integerRandom();
        System.out.println();
        doubleRandom();
    }
}
