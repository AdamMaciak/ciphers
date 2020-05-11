package algorytmRsa;

import Euklides.Euklides;

import java.util.Random;
import java.util.Scanner;

public class algorytmRsa {

    static boolean isPrime(long number) {
        int count = 0;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) count++;
            if (count > 0) return false;
        }
        return true;
    }

    static long ModularExp(long number, long exponent, long mod) {
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

    static long modInverse(long a, long b) {
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

    static long[] changeBlocksCharToLong(char[] messageToEncode) {
        int position = 0;
        int length = messageToEncode.length;
        long[] encodeMessage = new long[length / 3 + 1];
        for (int i = 0; i < length; i = i + 3) {
            encodeMessage[position] = ((long) messageToEncode[i]) << 16;
            if (i + 1 < length) encodeMessage[position] += ((long) messageToEncode[i + 1]) << 8;
            if (i + 2 < length) encodeMessage[position] += (long) messageToEncode[i + 2];
            position++;
        }
        return encodeMessage;
    }

    static long[] encode(long[] messageToEncode, long e, long n) {
        int length = messageToEncode.length;
        long[] encodeMessage = new long[length];
        for (int i = 0; i < length; i++) {
            encodeMessage[i] = ModularExp(messageToEncode[i], e, n);
        }
        return encodeMessage;
    }

    static long[] decode(long[] encodeMessage, long d, long n) {
        int length = encodeMessage.length;
        long[] decodedMessage = new long[length];
        for (int i = 0; i < length; i++) {
            decodedMessage[i] = ModularExp(encodeMessage[i], d, n);
        }
        return decodedMessage;
    }

    static char[] changeLongToCharBlock(long[] encodedMessageLong) {
        int length = encodedMessageLong.length;
        char[] decodedMessage = new char[length * 3];
        int position = 0;
        for (int i = 0; i < decodedMessage.length; i = i + 3) {
            decodedMessage[i] = (char) ((encodedMessageLong[position] / (long) Math.pow(2, 16)) % (long) Math.pow(2,
                    8));
            if (i + 1 <= decodedMessage.length)
                decodedMessage[i + 1] = (char) ((encodedMessageLong[position] / (long) Math.pow(2,
                        8)) % (long) Math.pow(2, 8));
            if (i + 2 <= decodedMessage.length)
                decodedMessage[i + 2] = (char) (encodedMessageLong[position] % (long) Math.pow(2, 8));
            position++;
        }
        return decodedMessage;
    }

    public static void main(String[] args) {
        Scanner textFromConsole = new Scanner(System.in);
       // char[] messageToEncode = "Adam_Maciak".toCharArray();
        String message;
        //p,q MUSZA BYC PROSTE, e też
        //2707 ,2843,10273,5087
        System.out.print("Podaj p = ");
        long p = textFromConsole.nextLong();
        System.out.print("Podaj q = ");
        long q = textFromConsole.nextLong();
        System.out.println("Czy p jest pierwsza? " + isPrime(p));
        System.out.println("Czy q jest pierwsza? " + isPrime(q));
        long n = p * q;
        System.out.println("n = " + n);
        long phi = (p - 1) * (q - 1);
        System.out.println("phi = " + phi);
        Random r = new Random();
        long e = r.longs(1, phi - 1).filter(x -> isPrime(x)).findFirst().getAsLong();
        System.out.println("losowane e jest rowne:" + e);
        long d = modInverse(e, phi);
        System.out.println("d = " + d);
        System.out.print("podaj tekst = ");
        message=textFromConsole.next();
        System.out.println("tekst = " + message);
        long[] a = changeBlocksCharToLong(message.toCharArray());
        System.out.print("bloki = ");
        for (long i :
                a) {
            System.out.print(" " + i);
        }
        System.out.println();
        long[] b = encode(a, e, n);
        System.out.print("szyfr = ");
        for (long i :
                b) {
            System.out.print(" " + i);
        }

        System.out.println();
        long[] c = decode(b, d, n);
        System.out.print("odszyfrowane_bloki = ");
        for (long i :
                c) {
            System.out.print(" " + i);
        }

        System.out.println();
        char[] f = changeLongToCharBlock(c);
        System.out.print("odszyfrowany_tekst = ");
        System.out.print(f);

        //System.out.println(changeLongToCharBlock(decode(encode(changeBlocksCharToLong(messageToEncode), e, n), d, n)));
    }
}
