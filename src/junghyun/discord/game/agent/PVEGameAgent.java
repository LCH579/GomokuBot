package junghyun.discord.game.agent;

import junghyun.ai.Game;
import junghyun.ai.Pos;
import junghyun.ai.engin.AIBase;
import junghyun.discord.GameManager;
import junghyun.discord.game.ChatGame;
import junghyun.discord.ui.MessageManager;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class PVEGameAgent implements GameAgent {

    private ChatGame chatGame;

    public PVEGameAgent(ChatGame chatGame) {
        this.chatGame = chatGame;
    }

    @Override
    public void putStone(IUser user, Pos pos, IChannel channel) {
        Game game = chatGame.onUpdate().getGame();

        if (!game.canSetStone(pos.getX(), pos.getY())) {
            MessageManager.getInstance(channel.getGuild()).sendAlreadyIn(chatGame, user, channel);
            return;
        }

        game.setStone(pos.getX(), pos.getY());
        if (game.isWin(pos.getX(), pos.getY(), game.getPlayerColor())) {
            chatGame.setState(ChatGame.STATE.WIN);
            MessageManager.getInstance(channel.getGuild()).sendPvEWin(chatGame, pos, channel);
            GameManager.endGame(chatGame);
            return;
        }

        if (game.isFull()) {
            chatGame.setState(ChatGame.STATE.FULL);
            MessageManager.getInstance(channel.getGuild()).sendFullCanvas(chatGame, channel);
            GameManager.endGame(chatGame);
            return;
        }

        Pos aiPos = new AIBase(game, AIBase.DIFF.MID).getAiPoint();
        game.setStone(aiPos.getX(), aiPos.getY(), !game.getPlayerColor());
        if (game.isWin(aiPos.getX(), aiPos.getY(), !game.getPlayerColor())) {
            chatGame.setState(ChatGame.STATE.LOSE);
            MessageManager.getInstance(channel.getGuild()).sendPvELose(chatGame, aiPos, channel);
            GameManager.endGame(chatGame);
            return;
        }

        MessageManager.getInstance(channel.getGuild()).sendNextTurn(chatGame, aiPos, user, chatGame.getNameTag(), channel);
    }

    @Override
    public void resignGame(IUser user, IChannel channel) {
        chatGame.setState(ChatGame.STATE.RESIGN);
        MessageManager.getInstance(channel.getGuild()).sendPvEResign(chatGame, channel);
        GameManager.endGame(chatGame);
    }

    @Override
    public void killGame() {
        GameManager.endGame(chatGame);
    }

    @Override
    public ChatGame getChatGame() {
        return this.chatGame;
    }

}
