package bookshop;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mainClass {
    public static void main(String[] args) {
    	clearBasketFile();
        final JFrame frame = new JFrame("Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 500);
        frame.setLayout(null);

        // USER 1 BUTTON
        JButton user1 = new JButton("user 1");
        user1.setBounds(50, 50, 150, 50);
        frame.add(user1);
        
        
        user1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	adminPage.adminLobby();
            	frame.setVisible(false);
            	
        }
        });

        JButton user2 = new JButton("user 2");
        user2.setBounds(50, 150, 150, 50);
        frame.add(user2);
        
        user2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String user = "User 2";
            	System.out.println(user);
            	customerPage.customerLobby(user);
            	frame.setVisible(false);
            	
        }
        });

        JButton user3 = new JButton("user 3");
        user3.setBounds(50, 250, 150, 50);
        frame.add(user3);
        
        user3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String user = "User 3";
            	System.out.println(user);
            	customerPage.customerLobby(user);
            	frame.setVisible(false);
            	
        }
        });

        JButton user4 = new JButton("user 4");
        user4.setBounds(50, 350, 150, 50);
        frame.add(user4);
        
        user4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String user = "User 4";
            	System.out.println(user);
            	customerPage.customerLobby(user);
            	frame.setVisible(false);
            	
        }
        });

        frame.setVisible(true);
    }

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

