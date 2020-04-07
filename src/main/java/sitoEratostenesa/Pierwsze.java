package sitoEratostenesa;


import java.io.IOException;
import java.util.*;

public class Pierwsze {

    public static void main(String[] args) throws IOException {
        //9000000-10000000
        Map<Integer,Boolean> numbers=new HashMap<>();

        int max=10000000;
        int min=9000000;
        int k=0;
        for(int i=2;i<max;i++){
            numbers.put(i,true);
        }
        for(int i=2;i<Math.sqrt(max);i++){
            if(numbers.get(i)) {
                for (int j = 2; j < max; j=(2 + k) * i) {
                    numbers.replace(j,false);
                    k++;
                }
            }
            k=0;
        }
        int count;
        count = (int) numbers.entrySet().stream().filter(Map.Entry::getValue).filter(x->x.getKey()>min).count();
        System.out.print(count);
    }
}
