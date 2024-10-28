package bookshop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class customerPage {
	public static void customerLobby(final String user) {
		final JFrame customerFrame = new JFrame("Customer Hub");
		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerFrame.setSize(500, 400);
		customerFrame.setLayout(null);

        // ADD VIEW BOOKS BUTTON
        JButton viewBooks = new JButton("View Books");
        viewBooks.setBounds(150, 50, 200, 50);
        customerFrame.add(viewBooks);
        
        // ADD VIEW BOOKS ACTION BUTTON
        
        viewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	method(user);
            	customerFrame.setVisible(false);
            }
        });
        
        JButton searchBooks = new JButton("Search Books");
        searchBooks.setBounds(150, 150, 200, 50);
        customerFrame.add(searchBooks);
        
        searchBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	method2(user);
            	customerFrame.setVisible(false);
            }
        });
        
        JButton viewShoppingBasket = new JButton("View Shopping Basket");
        viewShoppingBasket.setBounds(150, 250, 200, 50);
        customerFrame.add(viewShoppingBasket);
        
        viewShoppingBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	method4(user);
            	System.out.println(user);
            	customerFrame.setVisible(false);
            }
        });


        customerFrame.setVisible(true);
    }
    static void method(String user) {
    	CustomerBook.viewCustomerBooks(user);
    }
    static void method2(String user) {
    	CustomerBook.viewSearchPage(user);
    }
    static void method4(String user) {
    	CustomerBook.viewBasket(user);
    }
    
}



