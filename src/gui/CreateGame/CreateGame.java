package gui.CreateGame;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.Border;

import code.AccessType;
import code.Game;
import code.GameConstants;
import code.Player;
import code.ServerModel;
import code.Socket.BroadcastGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.border.LineBorder;

/**
 * Panel for Create Game Screen
 * @author Andrew Jank
 *
 */
public class CreateGame extends JPanel {

	/**
	 * Serial ID for Create Game class
	 */
	private static final long serialVersionUID = 8747754299436120824L;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public CreateGame(String hostPlayerName, JPanel mainPanel, JFrame mainFrame) {
		Game game = new Game();
		ServerModel model = new ServerModel(game);
		Executor executor = Executors.newSingleThreadExecutor();
		Executor tcpServer = Executors.newSingleThreadExecutor();
		Player p1 = new Player(hostPlayerName);
		p1.setReady(true);
		game.PlayerList.add(p1);
		
		// main Panel grid for create game screen
		this.setBackground(new Color(153, 102, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 100, 50};
		gridBagLayout.rowHeights = new int[]{100, 200, 0, 50};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		// Title Label
		JLabel lblDefense = new JLabel("<html>Fortress Defense</html>");
		lblDefense.setFont(new Font("Arial", Font.BOLD, 80));
		lblDefense.setForeground(Color.BLACK);
		lblDefense.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDefense = new GridBagConstraints();
		gbc_lblDefense.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDefense.gridwidth = 4;
		gbc_lblDefense.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefense.gridx = 0;
		gbc_lblDefense.gridy = 0;
		add(lblDefense, gbc_lblDefense);
		
		// Settings Panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 102, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 100, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {300, 200};
		gbl_panel.rowHeights = new int[] {50, 50, 50, 50, 50, 50, 50, 50};
		gbl_panel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		// Options Title Panel
		JLabel label = new JLabel("<html>Game Options</html>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 30));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridwidth = 2;
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		// Game Name Label
		JLabel lblgameName = new JLabel("<html>Game Name:</html>");
		lblgameName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblgameName.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblgameName = new GridBagConstraints();
		gbc_lblgameName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblgameName.anchor = GridBagConstraints.EAST;
		gbc_lblgameName.insets = new Insets(0, 0, 5, 5);
		gbc_lblgameName.gridx = 0;
		gbc_lblgameName.gridy = 2;
		panel.add(lblgameName, gbc_lblgameName);
		
		// Game name text field
		JTextField textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.getDocument().addDocumentListener(new BindingListener(model, "HostName"));
		
		// Access Label
		JLabel label_2 = new JLabel("<html>Access:</html>");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		panel.add(label_2, gbc_label_2);
		
		// Access Combo Box
		String[] accesses = new String[] { "Public", "Private" };
		DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<String>(accesses);
		JComboBox<String> choice = new JComboBox<String>(combo);
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 0);
		gbc_choice.gridx = 1;
		gbc_choice.gridy = 3;
		panel.add(choice, gbc_choice);
		
		// Password Label
		JLabel lblPassword = new JLabel("<html>Password:</html>");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 4;
		panel.add(lblPassword, gbc_lblPassword);
		
		// Password TextBox
		JTextField textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		textField_1.setEnabled(model.GetAccessType() == AccessType.Private);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.getDocument().addDocumentListener(new BindingListener(model, "Password"));
		
		choice.addItemListener(new AccessComboHandler(model, textField_1));
		
		// Num of Players Label
		JLabel label_3 = new JLabel("<html>Number of Players:</html>");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 5;
		panel.add(label_3, gbc_label_3);
		
		// Num Players Spinner
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(5, 2, 5, 1));
		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.VERTICAL;
		gbc_spinner.anchor = GridBagConstraints.WEST;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 5;
		
		//Chat Window
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		// Chat box
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		
		// Start Server Button
		JButton btnStartSever = new JButton("Start Server");
		GridBagConstraints gbc_btnStartSever = new GridBagConstraints();
		gbc_btnStartSever.insets = new Insets(0, 0, 0, 5);
		gbc_btnStartSever.gridx = 0;
		gbc_btnStartSever.gridy = 7;
		panel.add(btnStartSever, gbc_btnStartSever);
		
		// End Server Button
		JButton btnEndServer = new JButton("End Server");
		btnEndServer.setEnabled(false);
		GridBagConstraints gbc_btnEndServer = new GridBagConstraints();
		gbc_btnEndServer.gridx = 1;
		gbc_btnEndServer.gridy = 7;
		panel.add(btnEndServer, gbc_btnEndServer);
		StartServerButtonHandler startHandler = new StartServerButtonHandler(model, btnStartSever, btnEndServer, textField, spinner, 
				textField_1, choice, executor, tcpServer, this, textArea, mainFrame, mainPanel);
		btnEndServer.addActionListener(new EndServerButtonHandler(model, btnStartSever, btnEndServer, textField, spinner, textField_1, choice, startHandler));
		btnStartSever.addActionListener(startHandler);
		
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if (startHandler.getTCPServer() != null) {
			    	startHandler.getTCPServer().close(false);
		    	}
		    }
		});
		
		// Player Panel
		Panel panel_1 = new Panel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 100, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.rowHeights = new int[] {30, 50, 50, 50, 50, 50, 50};
		gbl_panel_1.columnWidths = new int[] {500};
		gbl_panel_1.columnWeights = new double[]{0.0};
		gbl_panel_1.rowWeights = new double[]{};
		panel_1.setLayout(gbl_panel_1);
		
		
		ArrayList<JTextField> playerTextFields = new ArrayList<JTextField>();
		
		// Player 1
		JTextField textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 1;
		panel_1.add(textField_2, gbc_textField_2);
		textField_2.setFont(new Font("Arial", Font.PLAIN, 20));
		playerTextFields.add(textField_2);
		
		// Player 2
		JTextField textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 2;
		panel_1.add(textField_3, gbc_textField_3);
		textField_3.setFont(new Font("Arial", Font.PLAIN, 20));
		playerTextFields.add(textField_3);
		
		// Player 3
		JTextField textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 0;
		gbc_textField_4.gridy = 3;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setFont(new Font("Arial", Font.PLAIN, 20));
		playerTextFields.add(textField_4);
		
		// Player 4
		JTextField textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridx = 0;
		gbc_textField_5.gridy = 4;
		panel_1.add(textField_5, gbc_textField_5);
		textField_5.setFont(new Font("Arial", Font.PLAIN, 20));
		playerTextFields.add(textField_5);
		
		// Player 5
		JTextField textField_6 = new JTextField();
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridx = 0;
		gbc_textField_6.gridy = 5;
		panel_1.add(textField_6, gbc_textField_6);
		textField_6.setFont(new Font("Arial", Font.PLAIN, 20));
		playerTextFields.add(textField_6);
		
		model.SetPlayerTextFields(playerTextFields);
		
		panel.add(spinner, gbc_spinner);
		spinner.addChangeListener(new SpinnerHandler(model, panel_1));
		
		// Back to Main Menu Button
		JButton btnBackToMain = new JButton("BACK TO MAIN MENU");
		GridBagConstraints gbc_btnBackToMain = new GridBagConstraints();
		gbc_btnBackToMain.insets = new Insets(0, 0, 5, 5);
		gbc_btnBackToMain.gridx = 0;
		gbc_btnBackToMain.gridy = 2;
		add(btnBackToMain, gbc_btnBackToMain);
		btnBackToMain.addActionListener(new CreateGameBackButtonHandler(this, mainPanel, startHandler));
		
		// Start Game Button
		JButton btnStartGame = new JButton("START GAME");
		GridBagConstraints gbc_btnStartGame = new GridBagConstraints();
		gbc_btnStartGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartGame.gridx = 1;
		gbc_btnStartGame.gridy = 2;
		add(btnStartGame, gbc_btnStartGame);
		btnStartGame.addActionListener(new StartGameButtonHandler(this, mainFrame, startHandler));
		
		// Message field
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.BOTH;
		gbc_textField_8.gridx = 2;
		gbc_textField_8.gridy = 2;
		add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		// Send message
		JButton btnNewButton = new JButton("Send");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;
		add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new SendButtonHandler(textField_8, startHandler));
	}

}
