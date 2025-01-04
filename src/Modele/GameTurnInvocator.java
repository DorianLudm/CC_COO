package Modele;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameTurnInvocator {

    private static GameTurnInvocator instance;
    private Deque<GameTurnCommand> gameTurnCommands;

    private GameTurnInvocator() {
        this.gameTurnCommands = new ArrayDeque<>();
    }

    public static GameTurnInvocator getInstance() {
        if (instance == null) {
            instance = new GameTurnInvocator();
        }
        return instance;
    }

    /** Add a GameTurnCommand to your GameTurn History
     * @param gtc GameTurnCommand with non-null Map attribut
     * */
    public void push(GameTurnCommand gtc) {
        if (gtc.getMap() != null) {              // Logs non-vide aussi ?
            this.gameTurnCommands.push(gtc);
        }
        else{
            throw new IllegalArgumentException("Command need a Map object");
        }
    }

    /** Return the top's stack element
     * @return GameTurnCommand or null if the deque is empty
     * */
    public GameTurnCommand pop() {
        if (!this.gameTurnCommands.isEmpty()) {
            return this.gameTurnCommands.pop();
        }
        else {
            return null;
        }
    }

    /** Check if the deque element is empty
     * @return boolean true if deque is empty, false otherwise
     * */
    public boolean isEmpty() {
        return this.gameTurnCommands.isEmpty();
    }
}
