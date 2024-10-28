package bookshop;

import java.io.BufferedReader;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.util.Comparator;
import java.io.FileReader;

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
import java.util.Collections;
import javax.swing.JComboBox;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class CustomerBook {
	static JTextArea textArea;
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

    public CustomerBook(String id, String format, String title, String language, String category, String publicationDate, double quantity, double price, double numPages, String condition) {
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

    static List<CustomerBook> books = new ArrayList<CustomerBook>();

    
    // THIS ALLOWS THE USER TO SEE ALL THE BOOKS IN THE STOCK  
    
    public static void viewCustomerBooks(final String user) {
        final JFrame tablePage = new JFrame("Stock");
        tablePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tablePage.setSize(800, 500);
        tablePage.setLayout(new BorderLayout());

        JButton goBackAdmin1 = new JButton("Go Back");

        goBackAdmin1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerPage.customerLobby(user);
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

        Set<String> addedTitles = new HashSet<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Stock.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(", ");
                CustomerBook book = new CustomerBook(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]), fields[9]);
                if (!addedTitles.contains(book.title)) {
                    books.add(book);
                    model.addRow(new Object[]{book.id, book.format, book.title, book.language, book.category, book.publicationDate, book.quantity, book.price, book.numPages, book.condition});
                    addedTitles.add(book.title);
                }
            }
            br.close();
        } catch (IOException e1) {
            System.out.println("An error occurred while reading the file: " + e1.getMessage());
        }

        Collections.sort(books, new Comparator<CustomerBook>() {
            public int compare(CustomerBook b1, CustomerBook b2) {
                return Double.compare(b1.price, b2.price);
            }
        });

        tablePage.getContentPane().removeAll();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePage.add(scrollPane, BorderLayout.CENTER);
        tablePage.add(goBackAdmin1, BorderLayout.NORTH);
        tablePage.revalidate();
        tablePage.repaint();
        tablePage.setVisible(true);
        
        tablePage.add(goBackAdmin1, BorderLayout.SOUTH);
    }
    
    
    //THIS CODE ALLOWS THE USER TO EITHER FILTER THROUH BOOK ID OR FILTER THROUGH AUDIOBOOK LISTENING TIME
    
    public static void viewSearchPage(final String user) {

    	final JFrame searchBooks = new JFrame("Search Books");
    	searchBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	searchBooks.setSize(400, 150);
    	searchBooks.setLayout(null);
    	searchBooks.setVisible(true);
        
        JLabel label = new JLabel("Search Book ID:");
        label.setBounds(10, 10, 150, 20);
        searchBooks.add(label);
        
        textArea = new JTextArea();
        textArea.setForeground(Color.RED); 
        textArea.setBounds(10, 30, 150, 20);
        searchBooks.add(textArea);
        
        JButton enterBookId = new JButton("Go");
        enterBookId.setBounds(60, 60, 50, 50);
        searchBooks.add(enterBookId);
        
        enterBookId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	searchBookIdResult(user);
            	searchBooks.setVisible(false);
            }
        });
        
        JLabel label1 = new JLabel("Audiobooks Listening time:");
        label1.setBounds(180, 10, 210, 20);
        searchBooks.add(label1);
        
        final Integer[] options = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final JComboBox<Integer> comboBox = new JComboBox<Integer>(options);
        comboBox.setBounds(175, 30, 210, 20);
        searchBooks.add(comboBox);

        JButton enterAudio = new JButton("Go");
        enterAudio.setBounds(245, 60, 50, 50);
        searchBooks.add(enterAudio);

        enterAudio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer selectedValue = (Integer) comboBox.getSelectedItem();
                searchAudioResult(user, selectedValue);
                searchBooks.setVisible(false);
            }
        });
       
    }
    
    //THIS GIVES YOU THE OPTION VIEW THE WHOLE BASKET WHICH IS STORED IN THE BASKET.TXT FILE AND IT ALSO GIVES THE USER THREE BUTTONS T EITHE GO BACK, DELETE BASKET OR BUY BASKET
    
    public static void viewBasket(final String user) {
        final JFrame viewBasketFrame = new JFrame("View Basket");
        viewBasketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewBasketFrame.setSize(400, 420);
        viewBasketFrame.setLayout(null);
        viewBasketFrame.setVisible(true);

        JLabel label2 = new JLabel(user + " Basket:");
        label2.setBounds(180, 10, 210, 20);
        viewBasketFrame.add(label2);

        try {
            BufferedReader br = new BufferedReader(new FileReader("Basket.txt"));
            String line;
            int yPosition = 40; // Initial Y position for labels

            while ((line = br.readLine()) != null) {
                JLabel label = new JLabel(line);
                label.setBounds(10, yPosition, 380, 20);
                viewBasketFrame.add(label);
                yPosition += 30; // Increase Y position for the next label
            }

            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
        JButton goBack = new JButton("Go Back");
        goBack.setBounds(300, 300, 100, 100);
        viewBasketFrame.add(goBack);
        
        
        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	customerPage.customerLobby(user);
            	viewBasketFrame.setVisible(false);
            }
        });
        
        JButton cancelBasket = new JButton("Cancel Basket");
        cancelBasket.setBounds(125, 300, 150, 100);
        viewBasketFrame.add(cancelBasket);
        
        
        cancelBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cancelBasket(user);
            	viewBasketFrame.setVisible(false);
            }
        });
        
        JButton buyBasket = new JButton("Buy Basket");
        buyBasket.setBounds(0, 300, 100, 100);
        viewBasketFrame.add(buyBasket);
        
        
        buyBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	buyBasket(user);
            	viewBasketFrame.setVisible(false);
            }
        });
        
        
    }
    
    
    //THIS CODE CREATES A TABLE WHERE IT SHOWS THE RESULT OF THE BOOK ID SEARCH AND ALSO GIVES YOU THE OPTION TO ADD IT TO BASKET
    
    static void searchBookIdResult(final String user) {
        final JFrame BookIdResults = new JFrame("Results");
        BookIdResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BookIdResults.setSize(350, 500);
        BookIdResults.setVisible(true);

        String filter = textArea.getText();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Book ID");
        try {
            BufferedReader br = new BufferedReader(new FileReader("Stock.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(", ");
                if (fields[0].equals(filter)) { 
                    model.addColumn(fields[0]);
                    model.addRow(new Object[]{"Format", fields[1]});
                    model.addRow(new Object[]{"Title", fields[2]});
                    model.addRow(new Object[]{"Language", fields[3]});
                    model.addRow(new Object[]{"Category", fields[4]});
                    model.addRow(new Object[]{"Publication Date", fields[5]});
                    model.addRow(new Object[]{"Quantity", fields[6]});
                    model.addRow(new Object[]{"Price", fields[7]});
                    model.addRow(new Object[]{"Number of Pages", fields[8]});
                    model.addRow(new Object[]{"Condition", fields[9]});
                    
                    String concatenatedFields = fields[0] + ", " + fields[2] + ", " + fields[7];
                    
                    String filePath = "Basket.txt";
                    BufferedWriter writer = null;
                    
                    try {
                        writer = new BufferedWriter(new FileWriter(filePath, true));
                    } catch (IOException e) {
                        System.out.println("An error occurred while opening the file: " + e.getMessage());
                    }
                    
                    try {
                        writer.write(concatenatedFields);
                        writer.newLine();
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                    
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred while closing the file: " + e.getMessage());
                    }
                    
                }
            }
            br.close();
        } catch (IOException e1) {
            System.out.println("An error occurred while reading the file: " + e1.getMessage());
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 350, 170);
        BookIdResults.add(scrollPane, BorderLayout.CENTER);
        
        JLabel label1 = new JLabel("Would you like to add book " + filter + " to the basket");
        label1.setBounds(0, 150, 210, 20);
        BookIdResults.add(label1);
        
        JButton goBack = new JButton("Go Back");
        goBack.setBounds(0, 400, 350, 100);
        BookIdResults.add(goBack);
        
        
        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	customerPage.customerLobby(user);
            	BookIdResults.setVisible(false);
            }
        });
        
        JButton addBasket = new JButton("Add to basket");
        addBasket.setBounds(245, 60, 50, 50);
        BookIdResults.add(addBasket);
        
        addBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	method3(user);
            	BookIdResults.setVisible(false);
            }
        });
    }
    
    //THIS CODE CREATES A FRAME THAT SHOWS A TABLE OF BOOKS IN STOCK THAT ARE BOTH AUDIOBOOKS AND HAVE A LISTENING TIME OF GREATER THAN SELECTEDVALUE
    
    static void searchAudioResult(final String user, Integer selectedValue) {
        System.out.println(selectedValue);
        final JFrame audioResults = new JFrame("Stock");
        audioResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        audioResults.setSize(800, 500);
        audioResults.setLayout(new BorderLayout());

        JButton goBackAdmin1 = new JButton("Go Back");

        goBackAdmin1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerPage.customerLobby(user);
                audioResults.setVisible(false);
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

        Set<String> addedTitles = new HashSet<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Stock.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(", ");
                CustomerBook book = new CustomerBook(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]), fields[9]);
                if (book.format.equals("audiobook") && book.quantity > selectedValue && !addedTitles.contains(book.title)) {
                    books.add(book);
                    model.addRow(new Object[]{book.id, book.format, book.title, book.language, book.category, book.publicationDate, book.quantity, book.price, book.numPages, book.condition});
                    addedTitles.add(book.title);
                }
            }
            br.close();
        } catch (IOException e1) {
            System.out.println("An error occurred while reading the file: " + e1.getMessage());
        }

        Collections.sort(books, new Comparator<CustomerBook>() {
            public int compare(CustomerBook b1, CustomerBook b2) {
                return Double.compare(b1.price, b2.price);
            }
        });

        audioResults.getContentPane().removeAll();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        audioResults.add(scrollPane, BorderLayout.CENTER);
        audioResults.add(goBackAdmin1, BorderLayout.NORTH);
        audioResults.revalidate();
        audioResults.repaint();
        audioResults.setVisible(true);

        audioResults.add(goBackAdmin1, BorderLayout.SOUTH);
    }
    
    //THIS IS JUST A FRAME THAT COMES UP WHEN A BOOK HAS BEEN ADDED TO THE BASKET TO CONFIRM TO THE USER
        
    static void method3(final String user) {
    	String filter = textArea.getText();
    	final JFrame success = new JFrame("Create New Hub");
    	success.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	success.setSize(200, 150);
    	success.setLayout(null);
    	success.setVisible(true);

    	JLabel msg = new JLabel("Book Added:" + filter);
    	msg.setBounds(10, 10, 150, 20);
    	success.add(msg);

    	JButton goBackAdmin = new JButton("Go Back");
    	goBackAdmin.setBounds(10, 50, 150, 40);
    	success.add(goBackAdmin);

    	goBackAdmin.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			customerPage.customerLobby(user);
    			success.setVisible(false);        	
    		}
    	});
        
    }
    
    //THIS OPENS A FRAME WHEN THE USER WANTS TO BUY THE BASKET, IT ALSO REMOVES ALL THE BOOKS INSIDE THE BASKET
    
    static void buyBasket(final String user) {
    	final JFrame purchased = new JFrame("Create New Hub");
    	purchased.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	purchased.setSize(400, 400);
    	purchased.setLayout(null);
    	purchased.setVisible(true);
    	
    	JLabel msg = new JLabel("Basket Purchased:");
    	msg.setBounds(10, 10, 150, 20);
    	purchased.add(msg);
    	
    	clearBasketFile();
    	
    	JButton goBackAdmin = new JButton("Go Back");
    	goBackAdmin.setBounds(10, 50, 150, 40);
    	purchased.add(goBackAdmin);

    	goBackAdmin.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			customerPage.customerLobby(user);
    			purchased.setVisible(false);        	
    		}
    	});
        
    }
    
    //THIS OPENS A FRAME WHEN THE USER WANTS TO CANCEL THE BASKET, IT ALSO REMOVES ALL THE BOOKS INSIDE THE BASKET
    
    static void cancelBasket(final String user) {
    	final JFrame canceled = new JFrame("Create New Hub");
    	canceled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	canceled.setSize(200, 150);
    	canceled.setLayout(null);
    	canceled.setVisible(true);
    	
    	JLabel msg = new JLabel("Basket Canceled:");
    	msg.setBounds(10, 10, 150, 20);
    	canceled.add(msg);
    	
    	clearBasketFile();
    	
    	JButton goBackAdmin = new JButton("Go Back");
    	goBackAdmin.setBounds(10, 50, 150, 40);
    	canceled.add(goBackAdmin);

    	goBackAdmin.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			customerPage.customerLobby(user);
    			canceled.setVisible(false);        	
    		}
    	});
        
    }
    
    //THIS CREATES FUNCTION SO I CAN CLEAR THE BOOKS IN THE BASKET AND I CAN CALL IT WHENEVER
    
    private static void clearBasketFile() {
	    try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter("Basket.txt"));
	        writer.write(""); // Write an empty string to clear the contents
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
}

