package threads.baseproject.model;

import java.io.File;

public interface AbstractFactory<T>{
    T create(int rows, int columns);

    T create(File file);
}
