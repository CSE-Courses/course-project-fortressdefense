package code;

import com.sun.javafx.tools.packager.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class FX_Handler {

    private static Map<String, String> selected = Map.ofEntries(
            entry("Battle_Axe", "gui/FX/Attack FX/Axe.wav"),
            entry("Axe", "gui/FX/Attack FX/Axe.wav"),
            entry("Sword", "gui/FX/Attack FX/Sword.wav"),
            entry("Stick", "gui/FX/Attack FX/Stick.wav"),
            entry("Mace", "gui/FX/Attack FX/Mace.wav"),
            entry("Crossbow", "gui/FX/Attack FX/Crossbow.wav"),
            entry("Stone_Wall", "gui/FX/Defense FX/Stone_Wall.wav"),
            entry("Wooden_Wall", "gui/FX/Defense FX/Wooden_Wall.wav"),
            entry("Barbed_Wire", "gui/FX/Defense FX/Barbed_Wire.wav"),
            entry("Reinforced_Gate", "gui/FX/Defense FX/Reinforced_Gate.wav"),
            entry("Iron_Door", "gui/FX/Defense FX/Iron_Door.wav"),
            entry("Steel_Chains", "gui/FX/Defense FX/Steel_Chains.wav"),
            entry("Thunderstorm", "gui/FX/Defense FX/Thunderstorm.wav"),
            entry("Tornado", "gui/FX/Defense FX/Tornado.wav"),
            entry("Flood", "gui/FX/Defense FX/Flood.wav"),
            entry("Earthquake", "gui/FX/Defense FX/Earthquake.wav"),
            entry("Archer_Tower", "gui/FX/Special FX/Archer_Tower.wav"),
            entry("Scout", "gui/FX/Special FX/Scout.wav"),
            entry("Trade", "gui/FX/Special FX/Trade.wav")
    );

    private static Map<String, String> use = Map.ofEntries(
            entry("Battle_Axe", "gui/FX/Attack FX/Axe_attack.wav"),
            entry("Axe", "gui/FX/Attack FX/Axe_attack.wav"),
            entry("Sword", "gui/FX/Attack FX/Sword_attack.wav"),
            entry("Stick", "gui/FX/Attack FX/Stick_attack.wav"),
            entry("Mace", "gui/FX/Attack FX/Mace_attack.wav"),
            entry("Crossbow", "gui/FX/Attack FX/Crossbow_attack.wav"),
            entry("Archer_Tower", "gui/FX/Special FX/Archer_Tower.wav"),
            entry("Scout", "gui/FX/Special FX/Scout.wav"),
            entry("Trade", "gui/FX/Special FX/Trade.wav"),
            entry("healing", "gui/FX/healing.wav"),
            entry("get_hit", "gui/FX/get_hit.wav")
    );

    private static Map<String, String> misc = Map.ofEntries(
            entry("button", "gui/FX/button.wav"),
            entry("discard_card", "gui/FX/discard_card.wav"),
            entry("healing", "gui/FX/healing.wav"),
            entry("draw_card", "gui/FX/draw_card.wav"),
            entry("get_hit", "gui/FX/get_hit.wav"),
            entry("loss", "gui/FX/loss.wav"),
            entry("win", "gui/FX/win.wav"),
            entry("turn", "gui/FX/turn.wav")
    );

    private void play(int type, String card_name) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = null;
        if(type == 0){
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(selected.get(card_name)));
        }
        else if(type == 1){
            if(!use.containsKey(card_name)){
                audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(use.get("healing")));
            }
            else {
				audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(use.get(card_name)));
            }
        }
        else if(type == 2){
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(misc.get(card_name)));
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
