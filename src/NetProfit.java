import java.io.*;
import java.util.Scanner;

public class NetProfit {
    public static void main(String[] args) {
        String filename = args[0];
        //DoubleQueueImpl<Double> queue = new DoubleQueueImpl<>();

        try {
            Scanner fileScanner = new Scanner(new File(filename)); // reading off "filename"

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) { // for all words in each line
                    System.out.println(lineScanner.next()); //test
                }
            }

            fileScanner.close(); // Close Scanner
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }
}