package algorytmRsa;

import Euklides.Euklides;

import java.util.Random;

public class algorytmRsa {

    static boolean isPrime(Integer number) {
        int count = 0;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) count++;
            if (count > 0) return false;
        }
        return true;
    }

    static long ModularExp(Long number, Long exponent, Long mod) {
        long res = 1;
        number %= mod;
        if (number == 0) return 0;
        while (exponent > 0) {
            //operation bitwise AND
            if ((exponent & 1) == 1) res = (res * number) % mod;
            exponent >>= 1;
            number = (number * number) % mod;
        }
        return res;
    }

    static public long modInverse(long a, long b) {
        long modulo = b;
        long t1 = 0, s1 = 1, t2 = 1, s2 = 0;
        while (a > 1) {
            if (b == 0) return -1;
            long q = a / b;
            long temp = b;
            b = a % b; //r_i=r_i-2 % r_i-1
            a = temp; // w nastepnej iteracji = r_i-2
            temp = s2;
            s2 = s1 - q * s2;
            s1 = temp;
            temp = t2;
            t2 = t1 - q * t2;
            t1 = temp;
        }
        if (s1 < 0) s1 += modulo;
        return s1;
    }

    public static void main(String[] args) {
        char[] messageToEncode = "Adam_Maciak".toCharArray();
        long[] encodeMessage = new long[messageToEncode.length / 3 + 1];
        char[] decodedMessage = new char[messageToEncode.length + 1];
        int position = 0;
        for (int i = 0; i < messageToEncode.length; i = i + 3) {
            encodeMessage[position] = ((long) messageToEncode[i]) << 16;
            if (i + 1 < messageToEncode.length) encodeMessage[position] += ((long) messageToEncode[i + 1]) << 8;
            if (i + 2 < messageToEncode.length) encodeMessage[position] += (long) messageToEncode[i + 2];
            position++;
        }

//        for (long i :
//                encodeMessage) {
//            System.out.println(i);
//        }
        long p = 3221;
        long q = 2701;
        long n = p * q;
        long phi = (p - 1) * (q - 1);
        long e = 3889;
        long d = modInverse(e, phi);
        System.out.println("wynik" + d);
        for (int i = 0; i < encodeMessage.length; i++) {
            encodeMessage[i] = ModularExp(encodeMessage[i], e, n);
        }
        for (long i :
                encodeMessage) {
            System.out.println(i);
        }
        for (int i = 0; i < encodeMessage.length; i++) {
            encodeMessage[i] = ModularExp(encodeMessage[i], d, n);
        }
//        for (long i :
//                encodeMessage) {
//            System.out.println(i);
//        }
        position = 0;
        for (int i = 0; i < decodedMessage.length; i = i + 3) {
            decodedMessage[i] = (char) ((encodeMessage[position] / (int) Math.pow(2, 16)) % Math.pow(2, 8));
            decodedMessage[i + 1] = (char) ((encodeMessage[position] / (int) Math.pow(2, 8)) % Math.pow(2, 8));
            decodedMessage[i + 2] = (char) (encodeMessage[position] % Math.pow(2, 8));
            position++;
        }
        System.out.println(decodedMessage);
    }
}
