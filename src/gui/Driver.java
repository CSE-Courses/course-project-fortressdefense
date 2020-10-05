package gui;

import javax.swing.SwingUtilities;

import gui.MainMenu;

public class Driver {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainMenu();
			}
		});
	}
}
