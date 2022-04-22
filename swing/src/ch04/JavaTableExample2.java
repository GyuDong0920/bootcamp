package ch04;

//java program to create a java table with a print option in it
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

public class JavaTableExample2 {
	public static void main(String args[]) {
		// rows of the table
		final Object r[][] = { { "Adam", "13", "Doctor" }, { "Anna", "21", "Engineer" },
				{ "Annamu", "31", "Technician" }, { "Iza", "41", "Physician" }, { "Norah", "11", "Author" },
				{ "Naashy", "12", "Artist" }, { "Poosa", "33", "Actor" }, { "Pichi", "14", "Author" },
				{ "Kunjol", "31", "Police" }, { "Thukidi", "12", "Doctor" }, { "Sam", "13", "Engineer" },
				{ "Kukku", "24", "Technician" }, { "Remya", "31", "Engineer" }, { "Radha", "42", "Lawyer" },
				{ "Harini", "43", "Artist" }, { "Vaibhav", "44", "Engineer" }, };
		// headers of the column
		final Object h[] = { "Name", "Age", "Occupation" };
		// create a frame
		JFrame fr = new JFrame(" Printing table");
		// close operation
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create a table
		final JTable tb = new JTable(r, h);
		// create scroll pane
		JScrollPane sp = new JScrollPane(tb);
		fr.add(sp, BorderLayout.CENTER);
		// create a button
		JButton button = new JButton("click this button to Print");
		// set an action
		ActionListener act = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// exception handling
				try {
					tb.print();
				} catch (PrinterException pe) {
					System.err.println("Error printing: " + pe.getMessage());
				}
			}
		};
		button.addActionListener(act);
		fr.add(button, BorderLayout.SOUTH);
		// set the size
		fr.setSize(300, 150);
		fr.setVisible(true);
	}
}
