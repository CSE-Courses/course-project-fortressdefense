package tests;

import code.room_info;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class room_info_test {
    String host_name = "host";
    String room_name = "room1";
    @Test
    public void room(){
        room_info_test create_room = new room_info_test();
        room_info room = new room_info();
        room.create("host", 4, "room1");
        assertEquals(room.host, host_name);
        assertEquals(room.room_name, room_name);
        assertEquals(room.current_size(), 1);
        assertEquals(room.room_status, "Waiting");

        // join test
        room.join("Tommy");
        assertEquals(room.current_size(), 2);
        room.join("p1");
        room.join("p2");
        room.join("p3");
        room.join("p4");
        //cannot join if full
        assertEquals(room.current_size(), 4);

        //left test
        room.left("Tommy");
        assertEquals(room.current_size(), 3);

        //player
    }
}
