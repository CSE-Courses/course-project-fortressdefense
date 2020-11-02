package gui;
import code.*;
import code.Deck.*;
import code.card_class.AttackCard;
import code.card_class.CardType;
import code.card_class.DefenseCard;
import code.card_class.SpecialCard;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import javax.swing.JPanel;

/*
 * Creates the Draw Phase GUI window during another player's turn
 * @author Alec Willette
 * 11/1/2020
 * */

public class drawPhaseOtherPlayer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	Timer tm;
	int i = 30;
	int numDots = 0;//used for waiting message
	
	public JPanel GetPanel() {
		return (JPanel) frame.getContentPane();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					drawPhaseOtherPlayer window = new drawPhaseOtherPlayer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public drawPhaseOtherPlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1129, 765);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("EXIT GAME");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBounds(0, 0, 152, 68);
		frame.getContentPane().add(btnExit);
		
		JLabel lblDrawPhase = new JLabel("DRAW PHASE");
		lblDrawPhase.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawPhase.setFont(new Font("Algerian", Font.BOLD, 40));
		lblDrawPhase.setBackground(new Color(255, 140, 0));
		lblDrawPhase.setBounds(388, 0, 296, 90);
		frame.getContentPane().add(lblDrawPhase);
		
		JLabel lblTimer = new JLabel("30");
		lblTimer.setFont(new Font("Sitka Subheading", Font.PLAIN, 36));
		lblTimer.setBounds(433, 71, 49, 46);
		frame.getContentPane().add(lblTimer);
		
		JLabel lblDots = new JLabel("");
		lblDots.setFont(new Font("Lucida Bright", Font.BOLD, 45));
		lblDots.setBounds(734, 277, 82, 68);
		frame.getContentPane().add(lblDots);
		
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{//end turn and exit window when timer reaches 0
				if(i == -1)
				{
					tm.stop();
					System.exit(0);
				}
				lblTimer.setText(Integer.toString(i));
				i--;
				if(numDots == 0)//subroutine for waiting dots
				{
					lblDots.setText(".");
					numDots++;
				}
				else if(numDots == 1)
				{
					lblDots.setText("..");
					numDots++;
				}
				else if(numDots == 2)
				{
					lblDots.setText("...");
					numDots++;
				}
				else
				{
					lblDots.setText("");
					numDots = 0;
				}
			}
		});
		tm.start();
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
		lblSeconds.setBounds(488, 75, 152, 41);
		frame.getContentPane().add(lblSeconds);
		
		JLabel lblBar = new JLabel("HEALTHPOINTS: 0");
		lblBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBar.setBounds(388, 128, 254, 46);
		frame.getContentPane().add(lblBar);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setMaximum(50);
		healthBar.setForeground(new Color(50, 205, 50));
		healthBar.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		healthBar.setBackground(Color.DARK_GRAY);
		healthBar.setBounds(273, 185, 508, 46);
		frame.getContentPane().add(healthBar);
		
		JLabel lblMsgBox = new JLabel("");
		lblMsgBox.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		lblMsgBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblMsgBox.setBorder(UIManager.getBorder("ProgressBar.border"));
		lblMsgBox.setBounds(10, 105, 216, 139);
		frame.getContentPane().add(lblMsgBox);
		
		JLabel lblRoundNum = new JLabel("ROUND 5/8");
		lblRoundNum.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblRoundNum.setBounds(20, 255, 195, 90);
		frame.getContentPane().add(lblRoundNum);
		
		JButton playerIcon = new JButton("");
		playerIcon.setBounds(710, 0, 100, 100);
		frame.getContentPane().add(playerIcon);
		
		JLabel lblName = new JLabel("<dynamic>'s Turn");
		lblName.setForeground(Color.RED);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBorder(UIManager.getBorder("InternalFrame.border"));
		lblName.setBackground(Color.BLACK);
		lblName.setBounds(820, 0, 500, 100);
		frame.getContentPane().add(lblName);
		
		JPanel Cardpanel = new JPanel();
		Cardpanel.setBackground(Color.YELLOW);
		Cardpanel.setBounds(10, 453, 1093, 262);
		frame.getContentPane().add(Cardpanel);
		
		JLabel lblWaiting = new JLabel("Waiting for other player");
		lblWaiting.setFont(new Font("Lucida Bright", Font.BOLD, 35));
		lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting.setBounds(273, 271, 468, 100);
		frame.getContentPane().add(lblWaiting);
	}
}
