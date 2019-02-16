package junghyun.discord.game.agent;

import junghyun.ai.Pos;
import junghyun.discord.GameManager;
import junghyun.discord.game.ChatGame;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class PVPGameAgent implements GameAgent {

    private ChatGame chatGame;

    public PVPGameAgent(ChatGame chatGame) {
        this.chatGame = chatGame;
    }

    @Override
    public void putStone(IUser user, Pos pos, IChannel channel) {

    }

    @Override
    public void resignGame(IUser user, IChannel channel) {

    }

    @Override
    public void killGame() {
        GameManager.endGame(chatGame);
        GameManager.delGame(chatGame.getOppPlayer().getLongId());
    }

    @Override
    public ChatGame getChatGame() {
        return this.chatGame;
    }

}
