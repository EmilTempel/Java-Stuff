package discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

	char prefix;
	Command[] commands;

	public Listener(char prefix, Command... commands) {
		this.prefix = prefix;
		this.commands = commands;
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String msg = e.getMessage().getContentRaw();

		if (msg.charAt(0) == prefix) {
			System.out.println(msg);

			msg = msg.replace(String.valueOf(prefix), "");

			for (int i = 0; i < commands.length; i++) {
				Command c = commands[i];
				for (int j = 0; j < c.cmd.length; j++) {
					String[] cmd = msg.split(" ");
					if (c.cmd[j].equalsIgnoreCase(cmd[0])) {
						c.addCommand_Body((cmd.length == 2) ? cmd[1] : null);
						commands[i].execute(e);
						break;
					}
				}
			}
		}

	}

}
