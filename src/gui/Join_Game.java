/**
 *Join Game Screen / GUI
 *
 * Some variables are only used for testing.
 * They should be empty in this file.
 * For example:
 * lobby_data : get games info form server
 * player_status : According to which game you joined, get player status in that room.(ready / waiting)
 *
 * Haohua Feng(Eddie)
 * */

package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.*;


public class Join_Game implements ActionListener {
    JFrame frame;
    JPanel panel;
    /**--------------------*/
    JLabel lobby;
    JLabel lobby_status;
    HashMap<String, List<String>> lobby_data = new HashMap<>();
    DefaultListModel<String> lobby_data_T = new DefaultListModel<>();
    JList<String> game_list;
    JButton refresh_button;
    /**--------------------*/
    JTextField search_room;
    JButton search_Button;
    /**--------------------*/
    JTable room_detail;
    JButton Ready_or_Cancel;
    String My_Status = "waiting";
    String[][] player_Status = {{"me", My_Status},{"player1", "ready"},{"player2", "waiting"}};
    /**--------------------*/
    JTextArea chat;
    JTextField unsend;
    JButton send;
    String chat_log = "";
    /**Exist*/
    JButton back;

    public Join_Game(){
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("Fortress Defense → Join Game");
        frame.setSize(720,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);

        /**Game Lobby*/
        lobby = new JLabel("Lobby");
        lobby.setBounds(50, 50, 40,20);
        panel.add(lobby);

        refresh_button = new JButton("Refresh");
        refresh_button.setBounds(270,45,80,30);
        refresh_button.addActionListener(this);
        panel.add(refresh_button);

        lobby_status = new JLabel("Room         |        Players           |          Status");
        lobby_status.setBounds(80, 83,300,20);
        frame.add(lobby_status);

        game_list = new JList<String>(lobby_data_T);
        game_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        game_list.setVisibleRowCount(-1);
        JScrollPane gls = new JScrollPane(game_list);
        gls.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        game_list.setBounds(50, 100, 300, 400);
        gls.setBounds(50, 100, 300, 400);
        panel.add(gls);

        /**Search field for searching game*/
        JLabel search1 = new JLabel("Cannot Find the game you want to join?");
        JLabel search2 = new JLabel("Type the name of the room in the text field and click.");
        search1.setBounds(90,525,300,20);
        search2.setBounds(50,545,300,20);
        panel.add(search1);panel.add(search2);

        search_room = new JTextField("Name Here");
        search_room.setHorizontalAlignment(JTextField.CENTER);
        search_room.setBounds(50,570,300,25);
        panel.add(search_room);

        search_Button = new JButton("Search and Join");
        search_Button.setBounds(130, 595, 130, 30);
        search_Button.addActionListener(this);
        panel.add(search_Button);

        /**Room Details*/
        String[] columnNames = {"Player Name", "Status"};
        room_detail = new JTable(player_Status, columnNames);
        room_detail.setBounds(400, 50, 250, 160);
        panel.add(room_detail);

        Ready_or_Cancel = new JButton("Ready");
        Ready_or_Cancel.setBounds(485, 210, 80, 30);
        Ready_or_Cancel.addActionListener(this);
        panel.add(Ready_or_Cancel);

        /**chat*/
        chat = new JTextArea();
        chat.setText(chat_log);
        chat.setEditable(false);
        JScrollPane Text_scroll = new JScrollPane(chat);
        Text_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Text_scroll.setBounds(400,270,250,330);
        panel.add(Text_scroll);

        unsend = new JTextField();
        unsend.setBounds(400,600,180,30);
        panel.add(unsend);

        send = new JButton("Send");
        send.setBounds(580,600,70,30);
        send.addActionListener(this);
        panel.add(send);

        /**back to main menu*/
        back = new JButton("← Back");
        back.setBounds(50,10,80,30);
        back.addActionListener(this);
        panel.add(back);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Join_Game();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(refresh_button)){
            for (int i = 0; i<100; i++){
                lobby_data.put("Room "+i, Arrays.asList("3/6", "waiting"));
            }
            for (int i = 0; i < 100; i++){
                String Name = "" +lobby_data.keySet().toArray()[i];
                String value = "  " + lobby_data.values().toArray()[i];
                lobby_data_T.addElement(Name);
            }
            //Get from server
            chat_log = "";
            chat.setText("");
            My_Status = "waiting";
            player_Status = new String[][]{{"me", My_Status}};
            System.out.println("Refresh Button Clicked, reload game list");
        }
        else if (e.getSource().equals(search_Button)) {
            //Search in server
            String room_name = search_room.getText();
            System.out.println("You entered: " + room_name + " . You click the Search and Join button");
        }
        else if (e.getSource().equals(Ready_or_Cancel)) {
            //change status and send to server
            if (My_Status.equals("waiting")){
                Ready_or_Cancel.setText("Cancel");
                My_Status = "ready";
            }
            else{
                Ready_or_Cancel.setText("Ready");
                My_Status = "waiting";
            }
            if (My_Status.equals("ready")){
                room_detail.getModel().setValueAt("ready",0,1);
            }
            else{
                room_detail.getModel().setValueAt("waiting",0,1);
            }
            System.out.println("Set to " + My_Status);
        }
        else if (e.getSource().equals(send)) {
            String input = unsend.getText();
            if (!input.equals("")) {
                Time time = new Time(System.currentTimeMillis());
                chat_log = chat_log + "\n\n" + time + "\nMe : " + input;
                chat.setText(chat_log);
                unsend.setText("");
                System.out.println("Send Message");
            }
        }
        else if (e.getSource().equals(back)){
            System.out.println("You clicked on Go Back button, back to main menu");
        }
        frame.repaint();
    }
}
