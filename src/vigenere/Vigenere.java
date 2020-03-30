package vigenere;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vigenere {

    public static final List<Character> alfabet;

    static {
        alfabet = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            alfabet.add((char) (48 + i));
        }
        for (int i = 0; i < 26; i++) {
            alfabet.add((char) (65 + i));
        }
    }

    public static void main(String[] args) throws IOException {

        StringBuffer fromFile = new StringBuffer();
        Scanner myReader = new Scanner(new File("src/resources/vigenere/text.txt"));
        while (myReader.hasNextLine()) {
            fromFile.append(myReader.nextLine());
            fromFile.append('\n');
        }
        System.out.println(fromFile);
        //cipher to vigenere
        String password = "Z01";
        char[] text = fromFile.toString().toUpperCase().toCharArray();
        int lengthPassword = password.length();
        int j=0;
        for (int i = 0; i < text.length; i++) {
            if (alfabet.contains(text[i])) {
                text[i] = alfabet.get((alfabet.indexOf(text[i]) + alfabet.indexOf(password.charAt(j))) % alfabet.size());
                if(j<lengthPassword-1) j++;
                else j=0;
            }
        }
//        for (char i : text) {
//            System.out.print(i);
//        }

        //decipher
        j=0;
        int temp=0;
        for(int i=0; i<text.length;i++){
            if(alfabet.contains(text[i])){
                temp=(alfabet.indexOf(text[i]) - alfabet.indexOf(password.charAt(j)));
                if(temp<0){
                    text[i]=alfabet.get(alfabet.size()+temp);
                }else{
                    text[i] = alfabet.get((alfabet.indexOf(text[i]) - alfabet.indexOf(password.charAt(j))) % 36);
                }
                if(j<lengthPassword-1) j++;
                else j=0;
            }
        }
//
//        for (char i : text) {
//            System.out.print(i);
//        }
    }
}
