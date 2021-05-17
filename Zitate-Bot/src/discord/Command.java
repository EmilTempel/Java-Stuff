package discord;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Command {
	
	String[] cmd;
	String cmd_body;
	
	public Command(String... cmd) {
		this.cmd = cmd;
		cmd_body = "test";
	}
	
	public void execute(GuildMessageReceivedEvent e) {
	}
	
	public void addCommand_Body(String body) {
		cmd_body = body;
	}
}
