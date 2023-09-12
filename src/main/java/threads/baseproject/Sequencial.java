package threads.baseproject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import threads.baseproject.model.AbstractFactory;
import threads.baseproject.model.FactoryProvider;
import threads.baseproject.model.IMatrix;

public class Sequencial {
    public static void main(String[] args) throws IOException {
        
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);

        AbstractFactory abstractFactory = FactoryProvider.geFactory("matrix");

        IMatrix M1 = (IMatrix) abstractFactory.create(file1);
        IMatrix M2 = (IMatrix) abstractFactory.create(file2);

        System.out.println(M1);
        System.out.println("-------------");   
        System.out.println(M2);

        long startTime  = System.currentTimeMillis();
        IMatrix result  = M1.multiplication(M2);
        long endTime    = System.currentTimeMillis();
        long execTime   = endTime - startTime;

        System.out.println("-------------");   
        System.out.println("MATRIZ RESULTADO: ");
        System.out.println("-------------");   
        
        System.out.println(result);
        System.out.println("Tempo de execução:");
        System.out.println(execTime + " Milissegundos");

        result.saveFile("result", execTime);

    } 
}