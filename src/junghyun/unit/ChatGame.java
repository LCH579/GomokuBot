package junghyun.unit;

import junghyun.ai.Game;
import sx.blah.discord.handle.obj.IMessage;

import java.util.LinkedList;
import java.util.List;

public class ChatGame {

    private long longId;
    private String nameTag;

    private Game game;

    public enum STATE {INP, WIN, RESIGN, FULL, LOSE, TIMEOUT}
    private STATE state;

    private List<IMessage> msgList;
    private long updateTime;

    public ChatGame(long longId, Game game, String nameTag) {
        this.longId = longId;
        this.nameTag = nameTag;
        this.game = game;
        this.state = STATE.INP;
        this.msgList = new LinkedList<>();
        this.updateTime = System.currentTimeMillis();
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public long getLongId() {
        return longId;
    }

    public Game getGame() {
        return game;
    }

    public void addMessage(IMessage iMessage) {
        this.msgList.add(iMessage);
    }

    public ChatGame onUpdate() {
        this.updateTime = System.currentTimeMillis();
        return this;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public String getNameTag() {
        return this.nameTag;
    }

    public boolean isWin() {
        return this.state == STATE.WIN;
    }

    public STATE getState() {
        return this.state;
    }

    public List<IMessage> getMessageList() {
        return this.msgList;
    }
}
