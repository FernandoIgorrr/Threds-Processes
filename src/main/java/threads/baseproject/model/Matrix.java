package threads.baseproject.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Matrix implements IMatrix {

    private int rows, columns;
    private int[][] matrix;

    public Matrix(int[][] matrix){

        this.matrix     = matrix;
        this.rows       = matrix.length;
        this.columns    = matrix[0].length;
    }

    public Matrix(File file) {
        try {
            this.matrix = readerToMatrix(new BufferedReader(new FileReader(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Matrix(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;

        if (this.allRight()) {
            this.matrix = matrixGenerator();
        } else {
            this.rows = 0;
            this.columns = 0;
            this.matrix = null;
        }
    }

    @Override
    public int getNumRows(){
        return this.rows;
    }

    @Override
    public int getNumColumns(){
        return this.columns;
    }

    public boolean allRight() {
        return this.rows >= 1 && this.columns >= 1;
    }

    private int[][] readerToMatrix(BufferedReader bufferedReader) throws NumberFormatException, IOException{
        
        int count = -1;
        int[][] matrix;        
        String line;

        Map<Integer,List<Integer>> matrixMap = new HashMap<>();

        List<Integer> intValues;

        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split("\t");
            intValues = new ArrayList<>();
            for (String value : values) {
               intValues.add(Integer.parseInt(value));
            }
            matrixMap.put(count, intValues);
            count++;
        }


        this.rows       = matrixMap.get(-1).get(0);
        this.columns    = matrixMap.get(-1).get(1);        
        
        if (this.allRight()) {
            matrix = new int[this.rows][this.columns];
            for (int i = 0; i < this.rows; i++) {
               matrix[i] = matrixMap.get(i).stream().mapToInt(Integer::intValue).toArray();
            }
        }
        else{
            this.rows       = 0;
            this.columns    = 0;      
        
            matrix = null;
        }
        return matrix;
    }


    private int[][] matrixGenerator() {
        int low = 1;
        int high = 10;
        int[][] matrix = new int[this.rows][this.columns];
        Random generator = new Random();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = generator.nextInt(high - low) + low;
            }
        }

        return matrix;
    }

    @Override
    public void saveFile(String path) throws IOException {

        FileWriter      fileWriter       = new FileWriter(path + ".mtx");
        BufferedWriter  bufferedWriter   = new BufferedWriter(fileWriter);

        if (this.isNull()) {
            bufferedWriter.write(this.rows + "\t" + this.columns);
            bufferedWriter.newLine();
            bufferedWriter.write("null");
            bufferedWriter.newLine();
            bufferedWriter.close();

        } else {

            bufferedWriter.write(this.rows + "\t" + this.columns);
            bufferedWriter.newLine();

            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < this.matrix[i].length; j++) {

                    bufferedWriter.write(this.matrix[i][j] + "");
                    if (j == this.matrix.length + 1) {
                    } else {
                        bufferedWriter.write("\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
    }

    @Override
    public void saveFile(String path, long time) throws IOException{
        FileWriter      fileWriter       = new FileWriter(path + ".mtxt");
        BufferedWriter  bufferedWriter   = new BufferedWriter(fileWriter);

        if (this.isNull()) {
            bufferedWriter.write(this.rows + "\t" + this.columns);
            bufferedWriter.newLine();
            bufferedWriter.write("null");
            bufferedWriter.newLine();
            bufferedWriter.close();

        } else {
            bufferedWriter.write(time+"");
            bufferedWriter.newLine();
            bufferedWriter.write(this.rows + "\t" + this.columns);
            bufferedWriter.newLine();

            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < this.matrix[i].length; j++) {

                    bufferedWriter.write(this.matrix[i][j] + "");
                    if (j == this.matrix.length + 1) {
                    } else {
                        bufferedWriter.write("\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
    }

    @Override
    public Matrix multiplication(IMatrix A) {
        Matrix Ax = (Matrix) A;

        int[][] result = new int[this.getNumRows()][Ax.getNumColumns()];

        if(this.columns != A.getNumRows()){
            return null;
        }
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < Ax.matrix[i].length; j++) {
                for (int k = 0; k < this.matrix[i].length; k++) {
                    result[i][j] += this.matrix[i][k] * Ax.matrix[k][j];
                }
            }
        }

        return new Matrix(result);
    }

    @Override
    public Matrix multiplication(IMatrix A, IMatrix B) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiplication'");
    }

    @Override
    public boolean isNull() {
        return this.matrix == null;
    }

    @Override
    public String toString() {
        String aux = "";

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                aux += "[" + this.matrix[i][j] + "]\t";
            }
            aux += "\n";
        }
        return aux;
    }
}