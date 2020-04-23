package testLucasL;

import java.util.*;

public class TestLucasLehmer {

    public Long Power(Integer number, Integer exp) {
        long sum = (long) number;
        for (int i = 1; i < exp; i++) {
            sum *= (long) number;
        }
        return sum;
    }
    //exp musi byc pierwsza
    public String LucasLehmer(Integer exp) {
        Long s = 4L;
        Long M = (Power(2, exp) - 1L);
        for (int i = 1; i <= exp - 2; i++) {
            s = ((s * s) - 2) % M;
        }
        if (s == 0) return "Pierwsza";
        else return "Złożona";
    }

    public boolean isPrime(Integer number){
        int count=0;
        for (int i = 2; i <number ; i++) {
            if(number%i==0)count++;
            if(count>0)return false;
        }
        return true;
    }
    public static void main(String[] args) {
        TestLucasLehmer mers = new TestLucasLehmer();
        for (int i = 3; i < 32; i++) {
            if(mers.isPrime(i)) System.out.println("2^" + i + "-1="+(mers.Power(2,i)-1) + " "+mers.LucasLehmer(i));
        }
    }
}
