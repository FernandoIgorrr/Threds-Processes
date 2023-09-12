package threads.baseproject;

import java.io.IOException;

import threads.baseproject.model.AbstractFactory;
import threads.baseproject.model.FactoryProvider;
import threads.baseproject.model.IMatrix;

public class Auxiliar {

    public static void main(String[] args){

        int[] dimensions = new int[args.length];
        AbstractFactory abstractFactory;
        
        for (int i = 0; i < args.length; i++) {
            dimensions[i] = Integer.parseInt(args[i]);
        }
        abstractFactory = FactoryProvider.geFactory("matrix");

        IMatrix M1 = (IMatrix) abstractFactory.create(dimensions[0],dimensions[1]);
        IMatrix M2 = (IMatrix) abstractFactory.create(dimensions[2],dimensions[3]);

         System.out.print(M1);
         System.out.println("----------------------------");
         System.out.print(M2);

        try {
            M1.saveFile("M1");
            M2.saveFile("M2");
        } catch (IOException e) {
           System.err.println(e.getMessage());
        }
        
    }
}