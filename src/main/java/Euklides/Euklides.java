package Euklides;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Euklides {

    static int modInverse(int a, int b) {
        int modulo = b;
        int t1 = 0, s1 = 1, t2 = 1, s2 = 0;
        while (a > 1) {
            if (b == 0) return -1;
            int q = a / b;
            int temp = b;
            b = a % b;//r_i=r_i-2 % r_i-1
            a = temp;// w nastepnej iteracji = r_i-2
            temp = s2;
            s2 = s1 - q * s2;
            s1=temp;
            temp=t2;
            t2 = t1 - q * t2;
            t1 = temp;
        }
        if (s1 < 0) s1 += modulo;
        return s1;
    }

    public static void main(String args[]) {
        System.out.println(modInverse(20, 8));
        System.out.println(modInverse(4321, 1234));
        System.out.println(modInverse(432, 321));
    }

    @Test
    @DisplayName("😱")
    void isInvModuloFunctionCorrect() {
        assertAll("Scores", () -> assertEquals(73, Euklides.modInverse(337, 123)),
                () -> assertEquals(309, Euklides.modInverse(4321, 1234)),
                () -> assertEquals(-1, Euklides.modInverse(432, 321)),
                () -> assertEquals(80, Euklides.modInverse(1554, 131)));
    }
}
