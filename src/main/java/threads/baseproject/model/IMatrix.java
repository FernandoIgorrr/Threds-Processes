package threads.baseproject.model;

import java.io.BufferedReader;
import java.io.IOException;

public interface IMatrix {

    public int getNumRows();

    public int getNumColumns();
    
    public IMatrix multiplication(IMatrix A);

    public IMatrix multiplication(IMatrix A, IMatrix B);

    public void saveFile(String path) throws IOException;

    public void saveFile(String path,long time) throws IOException;

    public boolean isNull();

}