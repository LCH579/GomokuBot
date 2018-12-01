package junghyun;

import junghyun.db.Logger;
import junghyun.ui.Message;
import junghyun.unit.Settings;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.obj.IMessage;

public class EventListener {

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        if ((event.getMessage().getType() != IMessage.Type.DEFAULT)
                || (event.getMessage().getContent().length() < 2)
                || (event.getMessage().getContent().toCharArray()[0] != Settings.PREFIX)) return;
        BotManager.processCommand(event);
    }

    @EventSubscriber
    public void onGuildCreateEvent(GuildCreateEvent event) {
        try {
            Message.sendHelp(event.getGuild().getSystemChannel());
            Logger.loggerInfo("Join server : " + event.getGuild().getName());
        } catch (Exception e) {
            Logger.loggerInfo("Load server : " + event.getGuild().getName());
        }
    }

}
