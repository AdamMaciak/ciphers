package hash;

import java.awt.*;
import java.sql.Array;
import java.util.*;
import java.util.List;

public class Hashowanie {

    public static int hash(String str) {
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + ((hash << 5) + hash);
        }
        return hash;
    }

    public static int adler32(String word) {
        int A = 1, B = 0;
        int P = 65521;
        for (int i = 0; i < word.length(); i++) {
            A = (A + word.charAt(i)) % P;
            B = (B + A) % P;
        }
        return (B << 16) | A;
    }

    public static String[] createRandomCharSequences(int length, int lengthSequence) {
        Random r = new Random();
        StringBuilder[] stringBuilders = new StringBuilder[length];
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            stringBuilders[i] = new StringBuilder();
            for (int j = 0; j < lengthSequence; j++) {
                stringBuilders[i].append((char) r.ints(65, 122)
                        .filter(x -> (x >= 65 && x <= 90) || (x >= 97 && x <= 122))
                        .findFirst()
                        .getAsInt());
            }
            strings[i] = stringBuilders[i].toString();
        }
        return strings;
    }

    public static void main(String[] args) {
        final int N = 100000;
        int lengthSequence8 = 8;
        int lengthSequence100 = 100;
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Podaj dlugosc ciagu: ");
        //lengthSequence = scanner.nextInt();
        //System.out.println();
        String[] strings = createRandomCharSequences(N, lengthSequence8);
        String[] strings100 = createRandomCharSequences(N, lengthSequence100);
        int[] sumDJB = new int[N];
        int[] sumAdler32 = new int[N];
        int[] sumDJB_100 = new int[N];
        int[] sumAdler32_100 = new int[N];
        for (int i = 0; i < N; i++) {
            sumAdler32[i] = adler32(strings[i]);
            sumDJB[i] = hash(strings[i]);
            sumDJB_100[i] = hash(strings100[i]);
            sumAdler32_100[i] = adler32(strings100[i]);
        }
        int collisionDJB = 0;
        int collisionAdler32 = 0;
        int collisionDJB_100 = 0;
        int collisionAdler32_100 = 0;
        boolean firstWord_DJB = true;
        boolean firstWord_Adler32 = true;
        boolean firstWord_DJB_100 = true;
        boolean firstWord_Adler32_100 = true;
        Map<Integer, String[]> firstCollisions = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i != j) {
                    if (sumDJB[i] == sumDJB[j]) {
                        collisionDJB++;
                        if (firstWord_DJB) {
                            firstCollisions.put(1,
                                    new String[]{"DJB", "D=8", "N=100000", "\n", "X", strings[i], strings[j], Integer.toString(
                                            sumDJB[i] + sumDJB[j])}
                            );
                            firstWord_DJB = false;
                        }
                    }
                    if (sumAdler32[i] == sumAdler32[j]) {
                        collisionAdler32++;
                        if (firstWord_Adler32) {
                            firstCollisions.put(2,
                                    new String[]{"Adler32", "D=8", "N=100000", "\n", "X", strings[i], strings[j], Integer.toString(
                                            sumAdler32[i] + sumAdler32[j])}
                            );
                            firstWord_Adler32 = false;
                        }
                    }
                    if (sumDJB_100[i] == sumDJB_100[j]) {
                        collisionDJB_100++;
                        if (firstWord_DJB_100) {
                            firstCollisions.put(3,
                                    new String[]{"DJB", "D=100", "N=100000", "\n", "X", strings100[i], strings100[j], Integer.toString(
                                            sumDJB_100[i] + sumDJB_100[j])}
                            );
                            firstWord_DJB_100 = false;
                        }
                    }
                    if (sumAdler32_100[i] == sumAdler32_100[j]) {
                        collisionAdler32_100++;
                        if (firstWord_Adler32_100) {
                            firstCollisions.put(4,
                                    new String[]{"Adler32", "D=100", "N=100000", "\n", "X", strings100[i], strings100[j], Integer.toString(
                                            sumAdler32_100[i] + sumAdler32_100[j])}
                            );
                            firstWord_Adler32_100 = false;
                        }
                    }
                }
            }
        }
        for (Map.Entry<Integer, String[]> i :
                firstCollisions.entrySet()) {
            for (String j :
                    i.getValue()) {
                if (j.equals("X") && i.getKey() == 1) {
                    System.out.print("kolizje= " + collisionDJB + "\n");
                } else if (j.equals("X") && i.getKey() == 2) {
                    System.out.print("kolizje= " + collisionAdler32 + "\n");
                } else if (j.equals("X") && i.getKey() == 3) {
                    System.out.print("kolizje= " + collisionDJB_100 + "\n");
                } else if (j.equals("X") && i.getKey() == 4) {
                    System.out.print("kolizje= " + collisionAdler32_100 + "\n ");
                } else {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}
