package bookshop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.io.FileWriter;

public class Book {
	private static String newBook;
    private String id;
    private String format;
    private String title;
    private String language;
    private String category;
    private String publicationDate;
    private double numPages;
    private String condition;
    private double quantity;
    private double price;

    public Book(String id, String format, String title, String language, String category, String publicationDate, double quantity, double price, double numPages, String condition) {
        this.id = id;
        this.format = format;
        this.title = title;
        this.language = language;
        this.category = category;
        this.publicationDate = publicationDate;
        this.quantity = quantity;
        this.price = price;
        this.numPages = numPages;
        this.condition = condition;
    }
    
    static List<Book> books = new ArrayList<Book>();

    public static void parseStockFile() {
        final JFrame tablePage = new JFrame("Stock");
        tablePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tablePage.setSize(800, 500);
        tablePage.setLayout(new BorderLayout());
        
        JButton goBackAdmin1 = new JButton("Go Back");
        goBackAdmin1.setBounds(350, 350, 100, 50);
        tablePage.add(goBackAdmin1);
        
        goBackAdmin1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	adminPage.adminLobby();
            	tablePage.setVisible(false);        	
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Format");
        model.addColumn("Title");
        model.addColumn("Language");
        model.addColumn("Category");
        model.addColumn("Publication Date");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Number of Pages");
        model.addColumn("Condition");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Stock.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(", ");
                Book book = new Book(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]), fields[9]);
                books.add(book);
                model.addRow(new Object[]{book.id, book.format, book.title, book.language, book.category, book.publicationDate, book.quantity, book.price, book.numPages, book.condition});
            }
            br.close();
        } catch (IOException e1) {
            System.out.println("An error occurred while reading the file: " + e1.getMessage());
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePage.add(scrollPane, BorderLayout.CENTER);

        tablePage.setVisible(true);
        
    }  
    
    public static void createNewBook() {
    	
    	final JFrame CNB = new JFrame("Create New Hub");
    	CNB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	CNB.setSize(170, 500);
    	CNB.setLayout(null);
        CNB.setVisible(true);
        
        JLabel label = new JLabel("Enter Book ID:");
        label.setBounds(10, 10, 150, 20);
        CNB.add(label);
        
        final JTextArea textArea = new JTextArea();
        textArea.setForeground(Color.RED); 
        textArea.setBounds(10, 30, 150, 20);
        CNB.add(textArea);
        
        JLabel label1 = new JLabel("Enter Book Format:");
        label1.setBounds(10, 50, 150, 20);
        CNB.add(label1);
        
        final JTextArea textArea1 = new JTextArea();
        textArea1.setForeground(Color.RED); 
        textArea1.setBounds(10, 70, 150, 20);
        CNB.add(textArea1);
        
        JLabel label2 = new JLabel("Enter Book Title:");
        label2.setBounds(10, 90, 150, 20);
        CNB.add(label2);
        
        final JTextArea textArea2 = new JTextArea();
        textArea2.setForeground(Color.RED); 
        textArea2.setBounds(10, 110, 150, 20);
        CNB.add(textArea2);
        
        JLabel label3 = new JLabel("Enter Book Language:");
        label3.setBounds(10, 130, 150, 20);
        CNB.add(label3);
        
        final JTextArea textArea3 = new JTextArea();
        textArea3.setForeground(Color.RED); 
        textArea3.setBounds(10, 150, 150, 20);
        CNB.add(textArea3);
        
        JLabel label4 = new JLabel("Enter Book Category:");
        label4.setBounds(10, 170, 150, 20);
        CNB.add(label4);
        
        final JTextArea textArea4 = new JTextArea();
        textArea4.setForeground(Color.RED); 
        textArea4.setBounds(10, 190, 150, 20);
        CNB.add(textArea4);
        
        JLabel label5 = new JLabel("Enter Book Publication Date: ");
        label5.setBounds(10, 210, 150, 20);
        CNB.add(label5);
        
        final JTextArea textArea5 = new JTextArea();
        textArea5.setForeground(Color.RED); 
        textArea5.setBounds(10, 230, 150, 20);
        CNB.add(textArea5);
        
        JLabel label6 = new JLabel("Enter Book Quantity:");
        label6.setBounds(10, 250, 150, 20);
        CNB.add(label6);
        
        final JTextArea textArea6 = new JTextArea();
        textArea6.setForeground(Color.RED); 
        textArea6.setBounds(10, 270, 150, 20);
        CNB.add(textArea6);
        
        JLabel label7 = new JLabel("Enter Book Price:");
        label7.setBounds(10, 290, 150, 20);
        CNB.add(label7);
        
        final JTextArea textArea7 = new JTextArea();
        textArea7.setForeground(Color.RED); 
        textArea7.setBounds(10, 310, 150, 20);
        CNB.add(textArea7);
        
        JLabel label8 = new JLabel("Enter Book Pages: ");
        label8.setBounds(10, 330, 150, 20);
        CNB.add(label8);
        
        final JTextArea textArea8 = new JTextArea();
        textArea8.setForeground(Color.RED); 
        textArea8.setBounds(10, 350, 150, 20);
        CNB.add(textArea8);
        
        JLabel label9 = new JLabel("Enter Book Condition:");
        label9.setBounds(10, 370, 150, 20);
        CNB.add(label9);
        
        final JTextArea textArea9 = new JTextArea();
        textArea9.setForeground(Color.RED); 
        textArea9.setBounds(10, 390, 150, 20);
        CNB.add(textArea9);
        
        JButton enterDetails = new JButton("Add Books");
        enterDetails.setBounds(10, 420, 150, 40);
        CNB.add(enterDetails);
        
        enterDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookID = textArea.getText();
                String bookFormat = textArea1.getText();
                String bookTitle = textArea2.getText();
                String bookLanguage = textArea3.getText();
                String bookCategory = textArea4.getText();
                String bookPubDate = textArea5.getText();
                int bookQuantity = Integer.parseInt(textArea6.getText());
                double bookPrice = Double.parseDouble(textArea7.getText());
                int bookPages = Integer.parseInt(textArea8.getText());
                String bookCondition = textArea9.getText();
            	
            	newBook = bookID + ", " + bookFormat + ", " + bookTitle + ", " + bookLanguage + ", " + bookCategory + ", " + bookPubDate + ", " + bookQuantity + ", " + bookPrice + ", " + bookPages + ", " + bookCondition;
            	method3();
            	CNB.setVisible(false);
            }
        });
    }
	public static void addNewBook(){
    	try {
            FileWriter writer = new FileWriter("Stock.txt", true);
            
            writer.write("\n" + newBook);

            writer.close();
            
            System.out.println(newBook + " : has been added to Stock");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    static void method3() {
    	Book.addNewBook();
     	final JFrame success = new JFrame("Create New Hub");
     	success.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	success.setSize(200, 150);
     	success.setLayout(null);
     	success.setVisible(true);
     	
        JLabel msg = new JLabel("Book Added");
        msg.setBounds(10, 10, 150, 20);
        success.add(msg);
        
        JButton goBackAdmin = new JButton("Go Back");
        goBackAdmin.setBounds(10, 50, 150, 40);
        success.add(goBackAdmin);
        
        goBackAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	adminPage.adminLobby();
            	success.setVisible(false);        	
            }
        });
    	
    }

}
