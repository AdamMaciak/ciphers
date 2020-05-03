package DiffHellman;


public class DiffHelmann {

    //Right-to-left binary method
    public int ModularExp(Integer number, Integer exponent, Integer mod) {
        int res = 1;
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

    public boolean isPrime(Integer number) {
        int count = 0;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) count++;
            if (count > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DiffHelmann diffHelmann = new DiffHelmann();
        //jawne liczby
        Integer p = 3331, g = 5341;
        //Alicja,Bob
        Integer a = 4235, b = 1123;
        //wysyłane liczby
        Integer A, B;
        System.out.println("p = 3331 , g = 5341" +
                "\n" +
                "p jest pierwsza? " + diffHelmann.isPrime(p) +
                "\n" +
                "Alicja wybrała liczbę a= 4235 , Bob zaś liczbę b= 1123" +
                "\n" +
                "A = g^a % p = " + diffHelmann.ModularExp(g, a, p) +
                "\n" +
                "B = g^b % p = " + diffHelmann.ModularExp(g, b, p)
        );
        A = diffHelmann.ModularExp(g, a, p);
        B = diffHelmann.ModularExp(g, b, p);
        System.out.println("tajna liczba od strony Alicji k_B = B^a % p = " + diffHelmann.ModularExp(B, a, p));
        System.out.println("tajna liczba od strony Boba k_A = B^a % p = " + diffHelmann.ModularExp(A, b, p));
    }
}
