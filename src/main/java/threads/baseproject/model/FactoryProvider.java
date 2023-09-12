package threads.baseproject.model;

public class FactoryProvider {
    public static AbstractFactory geFactory(String factoryType){
        if("matrix".equalsIgnoreCase(factoryType)){
            return new MatrixFactory();
        }
        return null;
    }
}
