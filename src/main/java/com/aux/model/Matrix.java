package com.aux.model;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class Matrix implements MatrixI{

    private int row, column;

    private int [][] matrix;
    
    public Matrix(File file){

    }

    public Matrix(int row, int column){
        this.row    = row;
        this.column = column;
        
        this.matrix = matrixGenerator();
    }
    
    public boolean allRight(){

        if(this.row < 1 || this.column < 1){
            return false;
        }
        else if(this.row == 1 && this.column == 1){
            return false;
        }
        
        return true;
    }

    private int[][] matrixGenerator(){
        int low = 1;
        int high = 25;
        int[][] matrix      = new int[this.row][this.column];
        Random  generator   = new Random();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = generator.nextInt(high-low) + low;
            }
        }

        return matrix;
    }
    public void saveFile(String path,String name){
        String totalPath = path+name;
    }

    @Override
    public MatrixI multiplication(MatrixI A) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiplication'");
    }

    @Override
    public MatrixI multiplication(MatrixI A, MatrixI B) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiplication'");
    }

    @Override
    public String toString() {
        return "Matrix [matrix=" + Arrays.toString(matrix) + "]";
    }

}
