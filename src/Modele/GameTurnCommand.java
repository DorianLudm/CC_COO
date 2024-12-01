package Modele;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class GameTurnCommand {

    private Map map;
    private Queue<String> logs;
    // on peut potentiellement séparer les logs joueur et npc

    public GameTurnCommand() {
        this.map = null;
        this.logs = new LinkedList<>();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Queue<String> getLogs() {
        return logs;
    }

    public void addLog(String log) {
        this.logs.add(log);
    }



}
