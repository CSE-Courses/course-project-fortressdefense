package gui;
import code.room_info;
import code.Socket.FindGame;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.List;

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

public class Join_Game implements ActionListener {
    JFrame frame;
    JPanel panel;
    /**--------------------*/
    JLabel lobby;
    JLabel lobby_status;
    //HashMap<String, List<String>> lobby_data = new HashMap<>();
    DefaultListModel<String> lobby_data_T = new DefaultListModel<>();
    JList game_list;
    JButton refresh_button;
    /**--------------------*/
    JTextField search_room;
    JButton search_Button;
    JLabel feedback = new JLabel();
    /**--------------------*/
    DefaultListModel<String> room_detail_T = new DefaultListModel<>();
    JList room_detail;
    JButton Ready_or_Cancel;
    /**--------------------*/
    JTextArea chat;
    JTextField unsend;
    JButton send;
    String chat_log = "";
    /**Exist*/
    JButton back;

    private HashMap<String, room_info> gl = new HashMap<>();
    private String RoomName = " ";

    //Test
    private String My_Name = "Haohua Feng";

    //for test
    public void room_test() {
        room_info r1 = new room_info();
        r1.create("Eddie", 6, "Test_room_1");
        r1.join("Maria");
        r1.join("Wendy");
        room_info r2 = new room_info();
        r2.create("Micheal", 8, "Test_room_2");
        r2.join("Tommy");
        room_info r3 = new room_info();
        r3.create("Tom", 4, "Test_room_3");
        gl.put(r1.room_name, r1);
        gl.put(r2.room_name, r2);
        gl.put(r3.room_name, r3);

        room_info r4 = new room_info();
        r4.create("some_guy", 2, "Full_Room");
        r4.join("Another_guy");
        gl.put(r4.room_name,r4);

        room_info r5 = new room_info();
        r5.create("p1", 2, "Playing");
        r5.join("p2");
        r5.room_status = "Ongoing";
        gl.put(r5.room_name,r5);
    }

    public void get_room_detail(String room_name){
        room_detail_T.removeAllElements();
        HashMap<String,String> ps = gl.get(room_name).getPlayer_status();
        room_detail_T.addElement("RoomName: "+room_name);
        room_detail_T.addElement(" ");
        for(Map.Entry<String, String> each_player : ps.entrySet()){
            if(gl.get(room_name).host.equals(each_player.getKey())){
                room_detail_T.addElement(each_player.getKey() +"     [" + each_player.getValue()+"] [host]");
            }
            else {
                room_detail_T.addElement(each_player.getKey() +"     [" + each_player.getValue()+"]");
            }

        }
    }

    public Join_Game() throws IOException {
        //room_test();
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("FORTRESS DEFENSE / Join Game");
        frame.setSize(720,720);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(new Color(209  ,116,0));
        panel.setLayout(null);

        /**Game Lobby*/
        lobby = new JLabel("Lobby");
        lobby.setBounds(50, 50, 40,20);
        panel.add(lobby);

        refresh_button = new JButton("Refresh");
        refresh_button.setBounds(270,45,80,30);
        refresh_button.addActionListener(this);
        panel.add(refresh_button);

        lobby_status = new JLabel("             Room                 | Players |        Status");
        lobby_status.setForeground(new Color(255,255,255));
        lobby_status.setBounds(80, 85,300,20);
        frame.add(lobby_status);

        game_list = new JList<String>(lobby_data_T);
        game_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        game_list.setVisibleRowCount(-1);

        game_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String detail = (String) game_list.getSelectedValue();
                String rn = "";
                for(int i = 0; i < 20; i++){
                    if(detail.charAt(i) == ' '){
                        break;
                    }
                    else{
                        rn += detail.charAt(i);}
                }
                if (e.getClickCount() == 1) {
                    get_room_detail(rn);
                }
                else if (e.getClickCount() == 2) {

                    if(!RoomName.equals(" ")) {
                        gl.get(RoomName).left(My_Name);
                    }
                    if (gl.get(rn).limit > gl.get(rn).current_size() && gl.get(rn).room_status.equals("Waiting")){
                        RoomName = rn;
                        gl.get(rn).join(My_Name);
                        feedback.setText("You Entered " + RoomName);
                        gl.get(rn).send_update();
                    }
                    else if(!gl.get(rn).room_status.equals("Waiting")){
                        feedback.setText("Cannot join ongoing game");
                    }
                    else{
                        feedback.setText("Fail to join. Room " + rn +" is full");
                    }
                    get_room_detail(rn);
                }
                refresh();
            }
        });

        JLabel hint = new JLabel("Single click to view room detail, double click to join");
        hint.setForeground(new Color(255,255,255));
        hint.setBounds(50,500,300,20);
        panel.add(hint);

        JScrollPane gls = new JScrollPane(game_list);
        gls.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        game_list.setBounds(50, 100, 300, 400);
        gls.setBounds(50, 100, 300, 400);
        panel.add(gls);

        /**Search field for searching game*/
        JLabel search1 = new JLabel("Cannot Find the game you want to join?");
        JLabel search2 = new JLabel("Type the name of the room in the text field and click.");
        search1.setForeground(new Color(255,255,255));
        search2.setForeground(new Color(255,255,255));
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

        feedback.setText("");
        feedback.setBounds(50,625,300,30);
        feedback.setForeground(new Color(255,255,100));
        panel.add(feedback);

        /**Room Details*/
        room_detail = new JList<String>(room_detail_T);
        room_detail.setBounds(400, 50, 250, 160);
        panel.add(room_detail);

        Ready_or_Cancel = new JButton("Ready");
        Ready_or_Cancel.setBounds(485, 210, 80, 30);
        Ready_or_Cancel.setForeground(new Color(255,255,255));
        Ready_or_Cancel.setBackground(new Color(0,204,0));
        Ready_or_Cancel.addActionListener(this);
        panel.add(Ready_or_Cancel);

        /**chat*/
        chat = new JTextArea();
        chat.setText(chat_log);
        chat.setEditable(false);
        chat.setLineWrap(true);
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
        back = new JButton("Back");
        back.setBackground(new Color(204,0,0));
        back.setForeground(new Color(255,255,255));
        back.setBounds(50,10,80,30);
        back.addActionListener(this);
        panel.add(back);

        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        /*
        character choosing
         */
        JButton ccb = new JButton("Choose character");
        ccb = new JButton("Click here to choose your player character");
        ccb.setBounds(50,650,280,30);
        Color c1 = new Color(246, 245, 244);
        ccb.setBackground(c1);
        panel.add(ccb);

        ccb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                chooseCharacter();
            }
        });

    }

    public void chooseCharacter() {
        JFrame frame = new JFrame("Character Choosing");
        Color c1 = new Color(153, 102, 0);

        JPanel panel = new JPanel();

        JButton playerIcon = new JButton("");
        Image pc = new ImageIcon(this.getClass().getResource("characters/my_character1.png")).getImage();
        playerIcon.setIcon(new ImageIcon(pc));
        playerIcon.setBounds(50, 700, 60, 100);
        panel.add(playerIcon);

        JButton playerIcon1 = new JButton("");
        Image pc1 = new ImageIcon(this.getClass().getResource("characters/my_character2.png")).getImage();
        playerIcon1.setIcon(new ImageIcon(pc1));
        playerIcon1.setBounds(120, 700, 60, 100);
        panel.add(playerIcon1);

        JButton playerIcon2 = new JButton("");
        Image pc2 = new ImageIcon(this.getClass().getResource("characters/my_character3.png")).getImage();
        playerIcon2.setIcon(new ImageIcon(pc2));
        playerIcon2.setBounds(190, 700, 60, 100);
        panel.add(playerIcon2);

        JButton playerIcon3 = new JButton("");
        Image pc3 = new ImageIcon(this.getClass().getResource("characters/my_character4.png")).getImage();
        playerIcon3.setIcon(new ImageIcon(pc3));
        playerIcon3.setBounds(260, 700, 60, 100);
        panel.add(playerIcon3);

        JButton playerIcon4 = new JButton("");
        Image pc4 = new ImageIcon(this.getClass().getResource("characters/my_character5.png")).getImage();
        playerIcon4.setIcon(new ImageIcon(pc4));
        playerIcon4.setBounds(330, 700, 60, 100);
        panel.add(playerIcon4);

        JButton playerIcon5 = new JButton("");
        Image pc5 = new ImageIcon(this.getClass().getResource("characters/my_character6.png")).getImage();
        playerIcon5.setIcon(new ImageIcon(pc5));
        playerIcon5.setBounds(400, 700, 60, 100);
        panel.add(playerIcon5);

        JButton playerIcon6 = new JButton("");
        Image pc6 = new ImageIcon(this.getClass().getResource("characters/my_character7.png")).getImage();
        playerIcon6.setIcon(new ImageIcon(pc6));
        playerIcon6.setBounds(50, 810, 60, 100);
        panel.add(playerIcon6);

        JButton playerIcon8 = new JButton("");
        Image pc8 = new ImageIcon(this.getClass().getResource("characters/my_character9.png")).getImage();
        playerIcon8.setIcon(new ImageIcon(pc8));
        playerIcon8.setBounds(190, 810, 60, 100);
        panel.add(playerIcon8);

        JButton playerIcon9 = new JButton("");
        Image pc9 = new ImageIcon(this.getClass().getResource("characters/my_character10.png")).getImage();
        playerIcon9.setIcon(new ImageIcon(pc9));
        playerIcon9.setBounds(260, 810, 60, 100);
        panel.add(playerIcon9);

        JButton playerIcon10 = new JButton("");
        Image pc10 = new ImageIcon(this.getClass().getResource("characters/my_character8.png")).getImage();
        playerIcon10.setIcon(new ImageIcon(pc10));
        playerIcon10.setBounds(330, 810, 60, 100);
        panel.add(playerIcon10);

        JButton playerIcon11 = new JButton("");
        Image pc11 = new ImageIcon(this.getClass().getResource("characters/my_character.png")).getImage();
        playerIcon11.setIcon(new ImageIcon(pc11));
        playerIcon11.setBounds(400, 810, 60, 100);
        panel.add(playerIcon11);

        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Join_Game();
    }

    private void refresh(){
        //obtain() returns the list of room_info that obtain from server
        lobby_data_T.removeAllElements();
        for (Map.Entry<String, room_info> room : gl.entrySet()){
            lobby_data_T.addElement(room.getValue().room_detail());
        }
        
        
        FindGame client = new FindGame();
        room_info room = new room_info();
        room.parseMessage(client.sendEcho("whoami"));
        lobby_data_T.addElement(room.room_detail());
        client.close();
    }
    /**
    private List<room_info> obtain(){
        List<room_info> result = new ArrayList<>();
        return result;
    }
     */


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(refresh_button)){
            refresh();
            chat_log = "";
            chat.setText("");
            System.out.println("Refresh Button Clicked, reload game list");
        }

        else if (e.getSource().equals(search_Button)) {
            String room_name = search_room.getText();
            Boolean found = false;
            if (!RoomName.equals(" ")){
                gl.get(RoomName).left(My_Name);
            }
            for (Map.Entry<String, room_info> room : gl.entrySet()){
                if (room.getKey().equals(room_name)){
                    if(gl.get(room_name).limit > gl.get(room_name).current_size() && gl.get(room_name).room_status.equals("Waiting")) {
                        room.getValue().join(My_Name);
                        RoomName = room_name;
                        feedback.setText("You Entered " + room_name);
                        found = true;
                        get_room_detail(RoomName);
                        refresh();
                        gl.get(RoomName).send_update();
                        break;
                    }
                    else if(!gl.get(room_name).room_status.equals("Waiting")){
                        feedback.setText("Cannot join ongoing game");
                    }
                    else {
                        feedback.setText("Room " + room_name + " is full");
                    }
                }
            }
            if (!found){
                feedback.setText("Room '"+room_name+ "' not found, try again!");
            }
            System.out.println("You entered: " + room_name + " . You click the Search and Join button");
        }

        else if (e.getSource().equals(Ready_or_Cancel)) {
            if (gl.get(RoomName).getPlayer_status().get(My_Name).equals("Waiting")){
                Ready_or_Cancel.setText("Cancel");
                Ready_or_Cancel.setBackground(new Color(255,0,0));
                gl.get(RoomName).my_status(My_Name, 'r');
            }
            else if(gl.get(RoomName).getPlayer_status().get(My_Name).equals("Ready")){
                Ready_or_Cancel.setText("Ready");
                Ready_or_Cancel.setBackground(new Color(0,255,0));
                gl.get(RoomName).my_status(My_Name, 'c');
            }
            get_room_detail(RoomName);
            System.out.println("Set to " + gl.get(RoomName).getPlayer_status().get(My_Name));
            gl.get(RoomName).send_update();
        }

        else if (e.getSource().equals(send)) {
            String input = unsend.getText();
            if (!input.equals("")) {
                Time time = new Time(System.currentTimeMillis());
                chat_log = chat_log + "" + time + "\n" + My_Name +": " + input + "\n\n";
                chat.setText(chat_log);
                unsend.setText("");
                System.out.println("Send Message");
            }
        }
        else if (e.getSource().equals(back)){
            System.out.println("You clicked on Go Back button, back to main menu");
            if (!RoomName.equals(" ")) {
                gl.get(RoomName).left(My_Name);
                gl.get(RoomName).send_update();
                RoomName = " ";
            }
            frame.dispose();
        }
        frame.repaint();
    }
}
