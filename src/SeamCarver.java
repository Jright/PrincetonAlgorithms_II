import edu.princeton.cs.algs4.Picture;

import java.awt.Color;


public class SeamCarver {


    private static final int R = 16;
    private static final int G = 8;
    private static final int B = 0;
    private final int W,H;
    private double[][] energy;
    private int[][] colorRGB;

    private int[] vSeam, hSeam;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if(picture == null){
            throw new IllegalArgumentException();
        }
        W = picture.width();
        H = picture.height();
        energy = new double[H][W];
        colorRGB = new int[H][W];
        for(int j = 0; j < H; j++){
            for(int i = 0; i < W; i++){
                colorRGB[j][i] = picture.get(i, j).getRGB();
            }
        }

        for (int j = 0; j < H; j++){
            for (int i = 0; i < W; i++){
                if (i == 0 || i == W - 1 || j == 0 || j == H - 1)
                    energy[j][i] = 1000;
                else
                    calculateEnergy(j, i);
            }
        }
    }

    private void calculateEnergy(int row, int col){
        if (row <= 0 || row >= H - 1 || col <= 0 || col >= W - 1){
            return;
        }
        int rx, gx, bx, ry, gy, by;
        rx = getColor(row, col + 1, R) - getColor(row, col - 1, R);
        gx = getColor(row, col + 1, G) - getColor(row, col - 1, G);
        bx = getColor(row, col + 1, B) - getColor(row, col - 1, B);
        ry = getColor((row + 1), col, R) - getColor((row - 1), col, R);
        gy = getColor((row + 1), col, G) - getColor((row - 1), col, G);
        by = getColor((row + 1), col, B) - getColor((row - 1), col, B);
        energy[row][col] = Math.sqrt(rx * rx + gx * gx + bx * bx
                + ry * ry + gy * gy + by * by);
    }

    private int getColor(int row, int col, int channel){
        return colorRGB[row][col] >> channel & 255;
    }

    // current picture
    public Picture picture() {
        Picture pic = new Picture(W, H);
        for (int j = 0; j < H; j++){
            for (int i = 0; i < W; i++){
                pic.set(i, j, new Color(colorRGB[j][i], true));
            }
        }
        return pic;
    }

    // width of current picture
    public int width() {
        return W;
    }

    // height of current picture
    public int height() {
        return H;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if(x < 0 || x >= W || y < 0 || y >= H){
            throw new IllegalArgumentException();
        }
        return energy[y][x];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        if(hSeam != null){
            return hSeam.clone();
        }
        int[] seam = new int[W];

        if(W < 3){
            for (int i = 1; i < W; i++){
                seam[i] = 0;
            }
            hSeam = seam;
            return seam.clone();
        }

    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {

    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}