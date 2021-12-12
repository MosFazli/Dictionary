/*

This app created By Danial Bayati, Ali Toosi and Mostafa Fazli
Shahroud University of Technology
1400/03/31

*/

public class Optimal_Binary_Search_Tree {

    public final int NMAX = 15000;
    public int numberOfKeys; //number of keys in the tree
    public double[][] C = new double[NMAX][NMAX]; //cost matrix
    public double[][] W = new double[NMAX][NMAX]; //weight matrix
    public int[][] R = new int[NMAX][NMAX]; //root matrix
    public double[] q = new double[NMAX]; //unsuccesful searches
    public double[] p = new double[NMAX];//frequencies 7
    public String[] mean = new String[NMAX];

    public int[] KEYS = new int[NMAX];
    public OBST ROOT;

    public void MAKE_W_C_R() {
        double x;
        double min;
        int i;
        int j;
        int k;
        int h;
        int m;


        sort();

        for (i = 0; i <= numberOfKeys; i++) {
            W[i][i] = q[i];
            for (j = i + 1; j <= numberOfKeys; j++) {
                W[i][j] = W[i][j - 1] + p[j] + q[j];
            }
        }

        for (i = 0; i <= numberOfKeys; i++) {
            C[i][i] = W[i][i];
        }
        for (i = 0; i <= numberOfKeys - 1; i++) {
            j = i + 1;
            C[i][j] = C[i][i] + C[j][j] + W[i][j];
            R[i][j] = j;
        }
        for (h = 2; h <= numberOfKeys; h++) {
            for (i = 0; i <= numberOfKeys - h; i++) {
                j = i + h;
                m = R[i][j - 1];
                min = C[i][m - 1] + C[m][j];
                for (k = m + 1; k <= R[i + 1][j]; k++) {
                    x = C[i][k - 1] + C[k][j];
                    if (x < min) {
                        m = k;
                        min = x;
                    }
                }
                C[i][j] = W[i][j] + min;
                R[i][j] = m;
            }
        }

        //Display weight matrix W
//        System.out.print("\nThe weight matrix W:\n");
//        for (i = 0; i <= numberOfKeys; i++) {
//            for (j = i; j <= numberOfKeys; j++) {
//                System.out.printf("%f ", W[i][j]);
//            }
//            System.out.print("\n");
//        }
//
//        //Display Cost matrix C
//        System.out.print("\nThe cost matrix C:\n");
//        for (i = 0; i <= numberOfKeys; i++) {
//            for (j = i; j <= numberOfKeys; j++) {
//                System.out.printf("%f ", C[i][j]);
//            }
//            System.out.print("\n");
//        }
//
//        //Display root matrix R 8
//
//        System.out.print("\nThe root matrix R:\n");
//        for (i = 0; i <= numberOfKeys; i++) {
//            for (j = i; j <= numberOfKeys; j++) {
//                System.out.printf("%d ", R[i][j]);
//            }
//            System.out.print("\n");
//        }
    }

    //Construct the optimal binary search tree
    public OBST CONSTRUCT_OBST(int i, int j) {
        OBST p;
        if (i == j)
            p = null;
        else {
            p = new OBST();
            p.KEY = KEYS[R[i][j] - 1];
            p.meaning = mean[R[i][j] - 1];
            p.left = CONSTRUCT_OBST(i, R[i][j] - 1); //left subtree
            p.right = CONSTRUCT_OBST(R[i][j], j); //right subtree
        }
        return p;
    }

    public void OPTIMAL_BINARY_SEARCH_TREE() {
        float average_cost_per_weight;

        MAKE_W_C_R();
        System.out.printf("C[0] = %f W[0] = %f\n", C[0][numberOfKeys], W[0][numberOfKeys]);
        average_cost_per_weight = (float) (C[0][numberOfKeys] / (float) W[0][numberOfKeys]);
        System.out.printf("The cost per weight ratio is: %f\n", average_cost_per_weight);
        ROOT = CONSTRUCT_OBST(0, numberOfKeys);
    }

    private void sort() {
        int n = numberOfKeys;
        int temp = 0;
        double temp1 = 0;
        String temp2 = "";
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (KEYS[j - 1] > KEYS[j]) {
                    //swap elements
                    temp = KEYS[j - 1];
                    KEYS[j - 1] = KEYS[j];
                    KEYS[j] = temp;

                    temp1 = p[j - 1];
                    p[j - 1] = p[j];
                    p[j] = temp1;

                    temp2 = mean[j - 1];
                    mean[j - 1] = mean[j];
                    mean[j] = temp2;
                }
            }
        }
    }
}