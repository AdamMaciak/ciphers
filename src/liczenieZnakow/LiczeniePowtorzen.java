package liczenieZnakow;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LiczeniePowtorzen {

    public static Map<Character, Integer> cal(StringBuffer text) {

        Map<Character, Integer> counts = new HashMap<>();

        char []ch= text.toString().toCharArray();

        for (int i = 0; i < ch.length; i++) {
            ch[i] = Character.toUpperCase(ch[i]);
        }

        int x = 48;
        for (int i = 0; i < 10; i++) {
            counts.put((char) (x + i), 0);
        }
        int y = 65;
        for (int i = 0; i < 26; i++) {
            counts.put((char) (y + i), 0);
        }

        for (char c : ch) {
            if (counts.containsKey(c)) {
                counts.put(c, counts.get(c) + 1);
            }
        }

        return sortValues(counts);
    }


    public static Map<Character, Integer> sortValues(Map<Character, Integer> toSort) {
        return toSort.entrySet()
                .stream()
                .sorted(Map.Entry.
                        comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public static void main(String[] args) throws IOException {

        StringBuffer fromFile = new StringBuffer();
        File myFile = new File("src/resources/liczenie/text.txt");
        Scanner myReader = new Scanner(myFile);
        while (myReader.hasNextLine()) {
            fromFile.append(myReader.nextLine());
            fromFile.append('\0');
        }
        myReader.close();


        StringBuffer toFile = new StringBuffer();
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/liczenie/output.txt"));

        Map<Character, Integer> counts = cal(fromFile);
        Integer sumWords = 0;

        for (Map.Entry entry : counts.entrySet()) {
            String strpercent = entry.getValue().toString();

            Integer percent = (Integer.valueOf(strpercent));
            sumWords += percent;
        }
        for (Map.Entry entry : counts.entrySet()) {
            String strpercent = entry.getValue().toString();
            Double percent = ((Double.valueOf(strpercent)) / sumWords) * 100;
            DecimalFormat dec = new DecimalFormat("#0.000");
            writer.write(entry.getKey() + "  " + dec.format(percent) + "%");
            writer.newLine();
        }
        writer.close();

//
        StringBuffer fromFile1 = new StringBuffer();
        File myFile1 = new File("src/resources/liczenie/odkogos.txt");
        Scanner myReader1 = new Scanner(myFile1);
        while (myReader1.hasNextLine()) {
            fromFile1.append(myReader1.nextLine());
        }
        myReader1.close();

        StringBuffer toFile1 = new StringBuffer();
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("src/resources/liczenie/outputodkogos.txt"));

        Map<Character, Integer> counts1 = cal(fromFile1);
        Integer sumWords1 = 0;

        for (Map.Entry entry : counts1.entrySet()) {
            String strpercent = entry.getValue().toString();

            Integer percent = (Integer.valueOf(strpercent));
            sumWords1 += percent;
        }

        for (Map.Entry entry : counts1.entrySet()) {
            String strpercent = entry.getValue().toString();
            Double percent = ((Double.valueOf(strpercent)) / sumWords1) * 100;
            DecimalFormat dec = new DecimalFormat("#0.000");
            writer1.write(entry.getKey() + "  " + dec.format(percent) + "%");
            writer1.newLine();
        }
        writer1.close();

    }
}
