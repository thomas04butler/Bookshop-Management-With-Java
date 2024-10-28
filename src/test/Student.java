/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bookshop.Book;

/*package bookshop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class adminPage {
    public static void main(String[] args) {;
    	final JFrame frame = new JFrame("Admin Hub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        // ADD VIEW BOOKS BUTTON
        JButton viewBooks = new JButton("View Books");
        viewBooks.setBounds(200, 50, 100, 50);
        frame.add(viewBooks);
        
        // ADD VIEW BOOKS ACTION BUTTON
        
        viewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	method();
            	frame.setVisible(false);
            }
        });

        JButton addBook = new JButton("Add Books");
        addBook.setBounds(200, 150, 100, 50);
        frame.add(addBook);
        
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
            	method2();
            }
        });

        JButton user3 = new JButton("Go back ");
        user3.setBounds(350, 425, 150, 50);
        frame.add(user3);

        frame.setVisible(true);
    }

    static void method() {
    	Book.parseStockFile();
    }
    static void method2() {
    	Book.createNewBook();
    }
}

    public static void parseStockFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Stock.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(", ");
                Book book = new Book(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]), fields[9]);
                books.add(book);
                System.out.println(book.toString());
            }
            br.close();
        } catch (IOException e1) {
            System.out.println("An error occurred while reading the file: " + e1.getMessage());
        }
    }
    public String toString() {
        return "ID:" + this.id + " | Format: " + this.format + " | Title: " + this.title + " | Language: " + this.language + " | Category: " + this.category + " | Publication Date: " + this.publicationDate + " | Quantity: " + this.quantity + " | Price: " + this.price + " | Number of Pages: " + this.numPages + " | Condition: " + this.condition;
    }
    
    
*/
