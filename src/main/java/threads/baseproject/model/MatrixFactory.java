package threads.baseproject.model;

import java.io.File;

public class MatrixFactory implements AbstractFactory<IMatrix>{

    @Override
    public IMatrix create(int rows, int columns){
        try {
            return new Matrix(rows, columns);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public IMatrix create(File file) {
         try {
            return new Matrix(file);

        } catch (Exception e) {
            return null;
        }
    }
}