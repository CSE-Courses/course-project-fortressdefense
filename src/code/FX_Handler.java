package code;

import com.sun.javafx.tools.packager.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class FX_Handler {

    private static Map<String, String> selected = Map.ofEntries(
            entry("Battle_Axe", "src\\gui\\FX\\Attack FX\\Axe.wav"),
            entry("Axe", "src\\gui\\FX\\Attack FX\\Axe.wav"),
            entry("Sword", "src\\gui\\FX\\Attack FX\\Sword.wav"),
            entry("Stick", "src\\gui\\FX\\Attack FX\\Stick.wav"),
            entry("Mace", "src\\gui\\FX\\Attack FX\\Mace.wav"),
            entry("Crossbow", "src\\gui\\FX\\Attack FX\\Crossbow.wav"),
            entry("Stone_Wall", "src\\gui\\FX\\Defense FX\\Stone_Wall.wav"),
            entry("Wooden_Wall", "src\\gui\\FX\\Defense FX\\Wooden_Wall.wav"),
            entry("Barbed_Wire", "src\\gui\\FX\\Defense FX\\Barbed_Wire.wav"),
            entry("Reinforced_Gate", "src\\gui\\FX\\Defense FX\\Reinforced_Gate.wav"),
            entry("Iron_Door", "src\\gui\\FX\\Defense FX\\Iron_Door.wav"),
            entry("Steel_Chains", "src\\gui\\FX\\Defense FX\\Steel_Chains.wav"),
            entry("Thunderstorm", "src\\gui\\FX\\Defense FX\\Thunderstorm.wav"),
            entry("Tornado", "src\\gui\\FX\\Defense FX\\Tornado.wav"),
            entry("Flood", "src\\gui\\FX\\Defense FX\\Flood.wav"),
            entry("Earthquake", "src\\gui\\FX\\Defense FX\\Earthquake.wav"),
            entry("Archer_Tower", "src\\gui\\FX\\Special FX\\Archer_Tower.wav"),
            entry("Scout", "src\\gui\\FX\\Special FX\\Scout.wav"),
            entry("Trade", "src\\gui\\FX\\Special FX\\Trade.wav")
    );

    private static Map<String, String> use = Map.ofEntries(
            entry("Battle_Axe", "src\\gui\\FX\\Attack FX\\Axe_attack.wav"),
            entry("Axe", "src\\gui\\FX\\Attack FX\\Axe_attack.wav"),
            entry("Sword", "src\\gui\\FX\\Attack FX\\Sword_attack.wav"),
            entry("Stick", "src\\gui\\FX\\Attack FX\\Stick_attack.wav"),
            entry("Mace", "src\\gui\\FX\\Attack FX\\Mace_attack.wav"),
            entry("Crossbow", "src\\gui\\FX\\Attack FX\\Crossbow_attack.wav"),
            entry("Archer_Tower", "src\\gui\\FX\\Special FX\\Archer_Tower.wav"),
            entry("Scout", "src\\gui\\FX\\Special FX\\Scout.wav"),
            entry("Trade", "src\\gui\\FX\\Special FX\\Trade.wav"),
            entry("healing", "src\\gui\\FX\\healing.wav"),
            entry("get_hit", "src\\gui\\FX\\get_hit.wav")
    );

    private static Map<String, String> misc = Map.ofEntries(
            entry("button", "src\\gui\\FX\\button.wav"),
            entry("discard_card", "src\\gui\\FX\\discard_card.wav"),
            entry("healing", "src\\gui\\FX\\healing.wav"),
            entry("get_hit", "src\\gui\\FX\\get_hit.wav"),
            entry("loss", "src\\gui\\FX\\loss.wav"),
            entry("win", "src\\gui\\FX\\win.wav"),
            entry("turn", "src\\gui\\FX\\turn.wav")
    );

    private void play(int type, String card_name) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = null;
        if(type == 0){
            audioInputStream = AudioSystem.getAudioInputStream(new File(selected.get(card_name)).getAbsoluteFile());
        }
        else if(type == 1){
            if(!use.containsKey(card_name)){
                audioInputStream = AudioSystem.getAudioInputStream(new File(use.get("healing")).getAbsoluteFile());
            }
            else {
                audioInputStream = AudioSystem.getAudioInputStream(new File(use.get(card_name)).getAbsoluteFile());
            }
        }
        else if(type == 2){
            audioInputStream = AudioSystem.getAudioInputStream(new File(misc.get(card_name)).getAbsoluteFile());
        }
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public void selected_card_fx(String card_name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        play(0, card_name);
    }

    public void use_card_fx(String card_name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        play(1, card_name);
    }

    public void misc_fx(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        play(2, filename);
    }
}
