package bookshop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class adminPage {
	public static void adminLobby() {
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


        frame.setVisible(true);
    }

    static void method() {
    	Book.parseStockFile();
    }
    static void method2() {
    	Book.createNewBook();
    }
}

