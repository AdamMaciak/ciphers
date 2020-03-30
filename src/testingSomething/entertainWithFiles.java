package testingSomething;

import java.io.*;
import java.util.Scanner;

public class entertainWithFiles {

    private static void showFileInputStream() throws IOException {
        File myFile = new File("src/resources/liczenie/text.txt");
        // System.out.println(myFile.isFile());
        FileInputStream fileInputStream=new FileInputStream(myFile);

        byte []tab=fileInputStream.readAllBytes();

        for(byte i:tab){

            System.out.print((char)i);
        }
        fileInputStream.close();
    }

    public static void main(String[] args) throws IOException {
//        Scanner scanner=new Scanner(new File("siema"){
//            public String siemano(){
//                return "twojastara";
//            }
//        });
   // showFileInputStream();

        int x=1232154;

        byte as=(byte)x;
        System.out.print(as);
    }
}
