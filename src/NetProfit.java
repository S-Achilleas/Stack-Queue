import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class NetProfit {
    public static void main(String[] args) {
        /*
            stock_queue and price_queue have the same amount of objects
            they will be used in parallel
            Assuming both are "double" for simplicity
            (stock queue could be Integer)
         */
        DoubleQueueImpl<Double> stock_queue = new DoubleQueueImpl<>();
        DoubleQueueImpl<Double> price_queue = new DoubleQueueImpl<>();
        double balance = 0.0;

        try {
            String filename = args[0];
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine(); // reading next line
                Scanner lineScanner = new Scanner(line); // to read next words without splitting
                String type = lineScanner.next();

                if (type.equals("buy")) {
                    /*
                        if buy then append transaction to the appropriate queues using "put" method
                    */
                    stock_queue.put(Double.parseDouble(lineScanner.next()));
                    lineScanner.next(); // skipping "price" string
                    price_queue.put(Double.parseDouble(lineScanner.next()));
                }
                else { // type equals "sell"
                    /*
                        if sell then retrieve first transaction and loop
                    */
                    Double sell_amount = Double.parseDouble(lineScanner.next());
                    lineScanner.next(); // skipping "price string"
                    Double sell_price = Double.parseDouble(lineScanner.next());
                    while (true){
                        if (sell_amount<stock_queue.peek()){
                            /*
                                ** throws NoSuchElementException in case stock_queue
                                    is empty **
                                if sell_amount (of stocks) is less than the first
                                item of stock_queue (head value) then:
                                calculate balance and subtract sell_amount from
                                stocks_queue head (price_queue remains unchanged)
                                then terminate loop
                             */
                            balance += sell_price * sell_amount - price_queue.peek() * sell_amount;
                            stock_queue.head.value -= sell_amount;
                            break;
                        }

                        else if (sell_amount.equals(stock_queue.peek())) {
                            /*
                                if sell_amount equals the head of stock_queue then:
                                using "get" method we remove it from the queue and
                                then calculate balance using same method as before,
                                while at the same time we remove the first object
                                of price_queue using "get", then terminate loop
                             */
                            stock_queue.get();
                            balance += sell_price * sell_amount - price_queue.get() * sell_amount;
                            break;
                        }

                        else {
                            /*
                                in this scenario sell_amount > stock_queue.peek()
                                (first item of stock queue):
                                calculate balance via stock_queue first item
                                since it is smaller, using "get" in the process
                                to remove both stock_queue head and price_queue head,
                                then subtract the amount of stocks calculated
                                in this instance from sell_amount of stocks,
                                loop continues
                             */
                            balance += stock_queue.peek() * sell_price - stock_queue.peek() * price_queue.get();
                            sell_amount -= stock_queue.get();
                        }
                    }
                }
            }
            fileScanner.close(); // Close Scanner
            if(balance>=0){
                System.out.println("Profit: " + balance);
            } else {
                System.out.println("Loss: " + balance);
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Please specify a file!");
        }
        catch (NoSuchElementException e) {
            System.err.println("Stock queue is empty, not enough stock to sell!"
                    + "\n" + "Please make sure that you are buying more stock" +
                    " than you are selling." );
        }
    }
}