package szyfrCezara;

import java.io.*;
import java.util.*;

public class Ceasar {

    public static final List<Character> alfabet;
    static {
        alfabet = new ArrayList<Character>();
        for (int i = 0; i < 10; i++) {
            alfabet.add((char) (48 + i));
        }
        for (int i = 0; i < 26; i++) {
            alfabet.add((char) (65 + i));
        }
    }

    public static void main(String[] args) throws IOException {
        Integer shift = 35;
        StringBuffer textFromFile = new StringBuffer();
        Scanner scanner = new Scanner(new File("src/main/java/resources/szyfrCezara/input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/resources/szyfrCezara/input.txt"));
        while (scanner.hasNextLine()) {
            textFromFile.append(scanner.nextLine());
            textFromFile.append('\n');
        }
        char []text = textFromFile.toString().toUpperCase().toCharArray();
        for (int i = 0; i < text.length; i++) {
            if (alfabet.contains(text[i])) {
                text[i] = alfabet.get((alfabet.indexOf(text[i]) + shift) % 36);
            }
        }
        bufferedWriter.write(text);
        bufferedWriter.close();
    }
}
